package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.channels.SelectableChannel;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

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
    public int aggiornaOggetto(UUID id, OggettoModel oggettoAggiornato) {
        final String sql = "UPDATE oggetto SET nome = ?, descrizione = ?, url_immagine = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                oggettoAggiornato.getNome(), oggettoAggiornato.getDescrizione(), oggettoAggiornato.getUrlImmagine(),
                id);
    }

    // TODO: importaOggetti
    @Override
    public long importaOggetti(UUID idAsta, String fileName) {
        String url = System.getProperty("jdbc-url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        long copyIn = 0;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            CopyManager copyManager = new CopyManager((BaseConnection) connection);

            try (FileInputStream fileInputStream = new FileInputStream(fileName);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,
                         StandardCharsets.UTF_8)) {
                final String selection =
                        "SELECT oggetto.nome, descrizione, url_immagine, categoria.nome, attributo.nome, valore " +
                                "FROM oggetto " +
                                "JOIN categoria_oggetto ON oggetto.id = categoria_oggetto.id_oggetto " +
                                "JOIN categoria ON categoria_oggetto.id_categoria = categoria.id " +
                                "JOIN attributo_oggetto ON oggetto.id = attributo_oggetto.id_oggetto " +
                                "JOIN attributo ON attributo_oggetto.id_attributo = attributo.id " +
                                "WHERE id_asta = " + idAsta;
                final String sql = "COPY (" + selection + ") FROM STDIN WITH DELIMITER '|'";
                copyIn = copyManager.copyIn(sql, inputStreamReader);
            }

        } catch (SQLException | IOException exception) {
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.SEVERE, exception.getMessage(), exception);
        }

        return copyIn;
    }

    // TODO: esportaOggetti
    @Override
    public long esportaOggetti(UUID idAsta, String fileName) {
        String url = System.getProperty("jdbc-url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        long copyOut = 0;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            CopyManager copyManager = new CopyManager((BaseConnection) connection);

            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                 OutputStreamWriter outputStreamWriter =
                         new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8)) {
                final String selection =
                        "SELECT oggetto.nome, descrizione, url_immagine, categoria.nome, attributo.nome, valore " +
                        "FROM oggetto " +
                        "JOIN categoria_oggetto ON oggetto.id = categoria_oggetto.id_oggetto " +
                        "JOIN categoria ON categoria_oggetto.id_categoria = categoria.id " +
                        "JOIN attributo_oggetto ON oggetto.id = attributo_oggetto.id_oggetto " +
                        "JOIN attributo ON attributo_oggetto.id_attributo = attributo.id " +
                        "WHERE id_asta = " + idAsta;
                final String sql = "COPY (" + selection + ") TO STDOUT WITH DELIMITER AS '|'";
                copyOut = copyManager.copyOut(sql, outputStreamWriter);
            }

        } catch (SQLException | IOException exception) {
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.SEVERE, exception.getMessage(), exception);
        }

        return copyOut;
    }

    private OggettoModel makeOggettoFromResultSet(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String nome = resultSet.getString("nome");
        String descrizione = resultSet.getString("descrizione");
        String urlImmagine = resultSet.getString("url_immagine");
        return new OggettoModel(id, nome, descrizione, urlImmagine);
    }
}
