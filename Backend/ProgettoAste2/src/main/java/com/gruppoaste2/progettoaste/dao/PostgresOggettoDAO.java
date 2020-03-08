package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-oggetto") // serve per identificare il tipo di database da usare (per dipendency injection)
public class PostgresOggettoDAO implements OggettoDAO {

    private final JdbcTemplate jdbcTemplate;

    private static final String selezioneOggetto = "SELECT o.id, o.nome, o.descrizione, o.url_immagine ";
    private static final String daOgetto =  "FROM oggetto AS o, asta AS a ";

    @Autowired
    public PostgresOggettoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int inserisciOggetto(UUID id, UUID idAsta, OggettoModel oggetto) {
        final String sql = "INSERT INTO oggetto(id, id_asta, nome, descrizione, url_immagine) " +
                "VALUES(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                id, idAsta, oggetto.getNome(), oggetto.getDescrizione(), oggetto.getUrlImmagine());
    }

    @Override
    public int eliminaOggetto(UUID id) {
        final String sql = "DELETE FROM oggetto WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<OggettoModel> trovaOggetto(UUID idOggetto) {
        final String sql = "SELECT * FROM oggetto WHERE id = ?";
        List<OggettoModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idOggetto);
        OggettoModel returnable = (results.isEmpty())? null : results.get(0);
        return  Optional.ofNullable(returnable);
    }

    @Override
    public List<OggettoModel> trovaOggetti() {
        final String sql = "SELECT * FROM oggetto";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet));
    }

    @Override
    public List<OggettoModel> trovaOggetti(UUID idAsta) {
        final String sql = "SELECT * FROM oggetto WHERE id_asta = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idAsta);
    }

    @Override
    public int aggiornaOggetto(UUID idOggetto, OggettoModel oggettoAggiornato) {
        final String sql = "UPDATE oggetto SET nome = ?, descrizione = ?, url_immagine = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                oggettoAggiornato.getNome(), oggettoAggiornato.getDescrizione(), oggettoAggiornato.getUrlImmagine(),
                idOggetto);
    }

    @Override
    public List<OggettoModel> oggettiRegistratiDaUtente(UUID idUtente) {
        final String sql = selezioneOggetto +
                daOgetto +
                "WHERE a.id_asta_manager = ? AND a.id = o.id_asta";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idUtente);
    }

    @Override
    public List<OggettoModel> oggettiInCorsoAstaDaUtente(UUID idUtente) {
        final String sql = selezioneOggetto +
                daOgetto +
                "WHERE a.id_asta_manager = ? AND a.id = o.id_asta AND a.data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idUtente);
    }

    @Override
    public List<OggettoModel> oggettiVintiDaUtente(UUID idUtente) {
        final String sql = selezioneOggetto +
                daOgetto +
                "WHERE a.id_asta_manager = ? AND a.id = o.id_asta AND a.data_fine IS NOT NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idUtente);
    }

    private OggettoModel makeOggettoFromResultSet(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String nome = resultSet.getString("nome");
        String descrizione = resultSet.getString("descrizione");
        String urlImmagine = resultSet.getString("url_immagine");
        return new OggettoModel(id, nome, descrizione, urlImmagine);
    }
}
