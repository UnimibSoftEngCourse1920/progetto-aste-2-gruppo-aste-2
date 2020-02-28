package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-oggetto") // serve per identificare il tipo di database da usare (per dipendency injection)
public class PostgresOggettoDAO implements OggettoDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final String NOME = "nome";
    private static final String DESCRIZIONE = "descrizione";
    private static final String URL = "url_immagine";

    @Autowired
    public PostgresOggettoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int inserisciOggetto(UUID id, UUID idAsta, OggettoModel oggetto) {
        final String sql = "INSERT INTO oggetto(id, id_asta, nome, descrizione, url_immagine) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, id, idAsta, oggetto.getNome(), oggetto.getDescrizione(), oggetto.getUrlImmagine());
    }

    @Override
    public int eliminaOggetto(UUID id) {
        final String sql = "DELETE FROM oggetto WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<OggettoModel> trovaOggetti() {
        final String sql = "SELECT * FROM oggetto";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String nome = resultSet.getString(NOME);
                    String descrizione = resultSet.getString(DESCRIZIONE);
                    String urlImmagine = resultSet.getString(URL);
                    return new OggettoModel(id,nome,descrizione,urlImmagine);
                });

    }

    @Override
    public List<OggettoModel> trovaOggetti(UUID idAsta) {
        final String sql = "SELECT * FROM oggetto WHERE id_asta = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String nome = resultSet.getString(NOME);
                    String descrizione = resultSet.getString(DESCRIZIONE);
                    String urlImmagine = resultSet.getString(URL);
                    return new OggettoModel(id,nome,descrizione,urlImmagine);
                },
                idAsta);

    }

    @Override
    public Optional<OggettoModel> trovaOggetto(UUID idOggetto) {
        final String sql = "SELECT * FROM oggetto WHERE id = ?";
        OggettoModel oggettoTrovato = jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String nome = resultSet.getString(NOME);
                    String descrizione = resultSet.getString(DESCRIZIONE);
                    String urlImmagine = resultSet.getString(URL);
                    return new OggettoModel(id,nome,descrizione,urlImmagine);
                },
                idOggetto);
        return Optional.ofNullable(oggettoTrovato);
    }

    @Override
    public int aggiornaOggetto(UUID idOggetto) {
        return 0;
    } // todo

    @Override
    public List<OggettoModel> oggettiRegistratiDaUtente(UUID idUtente) {
        final String sql = "SELECT o.id, o.nome, o.descrizione, o.url_immagine FROM oggetto as o, asta as a WHERE a.id_asta_manager = ? AND a.id = o.id_asta";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String nome = resultSet.getString(NOME);
                    String descrizione = resultSet.getString(DESCRIZIONE);
                    String urlImmagine = resultSet.getString(URL);
                    return new OggettoModel(id,nome,descrizione,urlImmagine);
                },
                idUtente);
    }

    @Override
    public List<OggettoModel> oggettiInCorsoAstaDaUtente(UUID idUtente) {
        final String sql = "SELECT o.id, o.nome, o.descrizione, o.url_immagine FROM oggetto as o, asta as a WHERE a.id_asta_manager = ? AND a.id = o.id_asta AND a.data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String nome = resultSet.getString(NOME);
                    String descrizione = resultSet.getString(DESCRIZIONE);
                    String urlImmagine = resultSet.getString(URL);
                    return new OggettoModel(id,nome,descrizione,urlImmagine);
                },
                idUtente);
    }

    @Override
    public Optional<List<OggettoModel>> oggettiVintiDaUtente(UUID idUtente) {
        return Optional.empty();
    }
}
