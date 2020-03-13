package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-oggetto") // serve per identificare il tipo di database da usare (per dependency injection)
public class PostgresOggettoDAO implements OggettoDAO {

    private final JdbcTemplate jdbcTemplate;

    private final String SELECT_FROM_OGGETTO_JOIN_ASTA =
            "SELECT o.id, o.nome, o.descrizione, o.url_immagine " +
            "FROM oggetto AS o " +
            "JOIN asta AS a ON o.id_asta = a.id ";
    private final String WHERE_ID_ASTA_MANAGER = "WHERE a.id_asta_manager = ?";

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
    public Optional<OggettoModel> trovaOggetto(UUID id) {
        final String sql = "SELECT * FROM oggetto WHERE id = ?";
        List<OggettoModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                id);
        OggettoModel returnable = (results.isEmpty())? null : results.get(0);
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
    public List<OggettoModel> trovaOggettiRegistratiDaUtente(UUID idUtente) {
        final String sql = SELECT_FROM_OGGETTO_JOIN_ASTA + WHERE_ID_ASTA_MANAGER;
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idUtente);
    }

    @Override
    public List<OggettoModel> trovaOggettiInCorsoAstaUtente(UUID idUtente) {
        final String sql = SELECT_FROM_OGGETTO_JOIN_ASTA + WHERE_ID_ASTA_MANAGER + " AND a.data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idUtente);
    }

    @Override
    public List<OggettoModel> trovaOggettiVendutiDaUtente(UUID idUtente) {
        final String sql = SELECT_FROM_OGGETTO_JOIN_ASTA + WHERE_ID_ASTA_MANAGER + " AND a.data_fine IS NOT NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idUtente);
    }

    // TODO: gestire il rifiuto di oggetti
    @Override
    public List<OggettoModel> trovaOggettiRifiutatiUtente(UUID idUtente) {
        final String sql = SELECT_FROM_OGGETTO_JOIN_ASTA + WHERE_ID_ASTA_MANAGER +
                " AND a.data_fine IS NOT NULL AND ";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOggettoFromResultSet(resultSet),
                idUtente);
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
    public int aggiornaOggetto(UUID id, OggettoModel oggettoAggiornato) {
        final String sql = "UPDATE oggetto SET nome = ?, descrizione = ?, url_immagine = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                oggettoAggiornato.getNome(), oggettoAggiornato.getDescrizione(), oggettoAggiornato.getUrlImmagine(),
                id);
    }

    private OggettoModel makeOggettoFromResultSet(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String nome = resultSet.getString("nome");
        String descrizione = resultSet.getString("descrizione");
        String urlImmagine = resultSet.getString("url_immagine");
        return new OggettoModel(id, nome, descrizione, urlImmagine);
    }
}
