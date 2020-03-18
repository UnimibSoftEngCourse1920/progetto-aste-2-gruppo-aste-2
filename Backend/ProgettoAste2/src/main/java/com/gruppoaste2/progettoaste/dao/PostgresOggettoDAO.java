package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AttributoModel;
import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

@Repository("postgres-oggetto") // serve per identificare il tipo di database da usare (per dependency injection)
public class PostgresOggettoDAO implements OggettoDAO {

    private final JdbcTemplate jdbcTemplate;

    private final CategoriaDAO categoriaDAO;

    private final String SELECT_FROM_OGGETTO_JOIN_ASTA =
            "SELECT o.id, o.nome, o.descrizione, o.url_immagine " +
            "FROM oggetto AS o " +
            "JOIN asta AS a ON o.id_asta = a.id ";
    private final String WHERE_ID_ASTA_MANAGER = "WHERE a.id_asta_manager = ?";

    @Autowired
    public PostgresOggettoDAO(JdbcTemplate jdbcTemplate, CategoriaDAO categoriaDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoriaDAO = categoriaDAO;
    }


    public UUID inserisciOggetto(UUID idOggetto, UUID idAsta, OggettoModel oggetto) {
        final String sql = "INSERT INTO oggetto(id, id_asta, nome, descrizione, url_immagine) " +
                "VALUES(?, ?, ?, ?, ?)";
        if(jdbcTemplate.update(sql,
                idOggetto, idAsta, oggetto.getNome(), oggetto.getDescrizione(), oggetto.getUrlImmagine())
                == 0)
            return null;

        List<CategoriaModel> categorie = oggetto.getCategorie();
        if(categorie != null)
            for(CategoriaModel categoria : categorie) {
                if(!categoriaDAO.controllaCategoriaEsiste(categoria))
                    if(categoriaDAO.aggiungiCategoria(categoria) == null)
                        return null;
                if(categoriaDAO.assegnaCategoriaAdOggetto(idOggetto, categoria.getId()) == 0)
                    return null;
            }
        return idOggetto;
    }

    @Override
    public int eliminaOggetto(UUID idOggetto) {
        Optional<OggettoModel> oggetto = trovaOggetto(idOggetto);
        if(oggetto.isEmpty())
            return 0;

        List<CategoriaModel> categorie = categoriaDAO.trovaCategorieOggetto(idOggetto);
        if(categorie != null)
            for(CategoriaModel categoria : categorie)
                if(categoriaDAO.rimuoviCategoriaDaOggetto(idOggetto, categoria.getId()) == 0)
                    return 0;

        final String sql = "DELETE FROM oggetto WHERE id = ?";
        return jdbcTemplate.update(sql, idOggetto);
    }

    @Override
    public Optional<OggettoModel> trovaOggetto(UUID idOggetto) {
        final String sql = "SELECT * FROM oggetto WHERE id = ?";
        List<OggettoModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idOggetto);
        OggettoModel returnable = (results.isEmpty()) ? null : results.get(0);
        return Optional.ofNullable(returnable);
    }

    @Override
    public List<OggettoModel> trovaOggetti() {
        final String sql = "SELECT * FROM oggetto";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet));
    }

    @Override
    public List<OggettoModel> trovaOggettiAsta(UUID idAsta) {
        final String sql = "SELECT * FROM oggetto WHERE id_asta = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idAsta);
    }

    @Override
    public List<OggettoModel> trovaOggettiRegistratiDaUtente(UUID idAstaManager) {
        final String sql = SELECT_FROM_OGGETTO_JOIN_ASTA +
                WHERE_ID_ASTA_MANAGER;
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idAstaManager);
    }

    @Override
    public List<OggettoModel> trovaOggettiInCorsoAstaUtente(UUID idAstaManager) {
        final String sql = SELECT_FROM_OGGETTO_JOIN_ASTA +
                WHERE_ID_ASTA_MANAGER + " AND a.data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idAstaManager);
    }

    @Override
    public List<OggettoModel> trovaOggettiVendutiDaUtente(UUID idAstaManager) {
        final String sql = SELECT_FROM_OGGETTO_JOIN_ASTA +
                WHERE_ID_ASTA_MANAGER + " AND a.data_fine IS NOT NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idAstaManager);
    }

    @Override
    public List<OggettoModel> trovaOggettiRifiutatiUtente(UUID idAstaManager) {
        final String sql = SELECT_FROM_OGGETTO_JOIN_ASTA +
                WHERE_ID_ASTA_MANAGER + " AND a.data_fine IS NOT NULL AND a.rifiutata = true";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idAstaManager);
    }

    @Override
    public List<OggettoModel> trovaOggettiVintiDaUtente(UUID idUtente) {
        final String sql = SELECT_FROM_OGGETTO_JOIN_ASTA +
                "JOIN offerta as off ON a.id = off.id_asta " +
                "WHERE off.id_offerente = ? AND a.data_fine IS NOT NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idUtente);
    }

    @Override
    public int aggiornaOggetto(UUID idOggetto, OggettoModel oggettoAggiornato) {
        final String sql = "UPDATE oggetto SET nome = ?, descrizione = ?, url_immagine = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                oggettoAggiornato.getNome(), oggettoAggiornato.getDescrizione(), oggettoAggiornato.getUrlImmagine(),
                idOggetto);
    }

    private OggettoModel makeOggettoFromResultSet(ResultSet resultSet) throws SQLException {
        UUID idOggetto = UUID.fromString(resultSet.getString("id"));
        String nome = resultSet.getString("nome");
        String descrizione = resultSet.getString("descrizione");
        String urlImmagine = resultSet.getString("url_immagine");
        List<CategoriaModel> categorie = categoriaDAO.trovaCategorieOggetto(idOggetto);
        return new OggettoModel(idOggetto, nome, descrizione, urlImmagine, categorie);
    }
}
