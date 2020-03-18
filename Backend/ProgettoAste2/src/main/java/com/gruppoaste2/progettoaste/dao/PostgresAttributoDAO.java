package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AttributoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-attributo")
public class PostgresAttributoDAO implements AttributoDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresAttributoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID aggiungiAttributo(UUID idAttributo, String idCategoria, AttributoModel attributo) {
        final String sql = "INSERT INTO attributo(id, id_categoria, nome) " +
                "VALUES(?, ?, ?)";
        if(jdbcTemplate.update(sql, idAttributo, idCategoria, attributo.getNome()) == 0)
            return null;
        return idAttributo;
    }

    @Override
    public int eliminaAttributo(UUID idAttributo) {
        final String sql = "DELETE FROM attributo WHERE id = ?";
        return jdbcTemplate.update(sql, idAttributo);
    }

    @Override
    public Optional<AttributoModel> trovaAttributo(UUID idAttributo) {
        final String sql = "SELECT * FROM attributo WHERE id = ?";
        List<AttributoModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> makeAttributoCategoriaFromResultSet(resultSet),
                idAttributo);
        AttributoModel returnable = (results.isEmpty()) ? null : results.get(0);
        return Optional.ofNullable(returnable);
    }

    @Override
    public List<AttributoModel> trovaAttributi() {
        final String sql = "SELECT * FROM attributi";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAttributoCategoriaFromResultSet(resultSet));
    }

    @Override
    public List<AttributoModel> trovaAttributiCategoria(String idCategoria) {
        final String sql = "SELECT * FROM attributo WHERE id_categoria = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAttributoCategoriaFromResultSet(resultSet),
                idCategoria);
    }

    @Override
    public List<AttributoModel> trovaAttributiOggetto(UUID idOggetto) {
        final String sql = "SELECT * FROM attributo " +
                "JOIN attributo_oggetto ON id = id_attributo " +
                "WHERE id_oggetto = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAttributoOggettoFromResultSet(resultSet),
                idOggetto);
    }

    @Override
    public int aggiornaAttributo(UUID idAttributo, AttributoModel attributoAggiornato) {
        final String sql = "UPDATE attributo SET nome = ? WHERE id = ?";
        return jdbcTemplate.update(sql, attributoAggiornato.getNome(), idAttributo);
    }

    @Override
    public int aggiornaAttributoOggetto(UUID idAttributo, UUID idOggetto, AttributoModel attributoAggiornato) {
        final String sql = "UPDATE attributo_oggetto SET valore = ? " +
                "WHERE id_oggetto = ? AND id_attributo = ?";
        return jdbcTemplate.update(sql, attributoAggiornato.getValore(), idOggetto, idAttributo);
    }

    @Override
    public int assegnaValoreAttributoAdOggetto(UUID idOggetto, AttributoModel attributo) {
        final String sql = "INSERT INTO attributo_oggetto(id_oggetto, id_attributo, valore) " +
                "VALUES(?, ?, ?)";
        return jdbcTemplate.update(sql, idOggetto, attributo.getId(), attributo.getValore());
    }

    @Override
    public int rimuoviValoreAttributoDaOggetto(UUID idOggetto, UUID idAttributo) {
        final String sql = "DELETE FROM attributo_oggetto WHERE id_oggetto = ? AND id_attributo = ?";
        return jdbcTemplate.update(sql, idOggetto, idAttributo);
    }

    private AttributoModel makeAttributoCategoriaFromResultSet(ResultSet resultSet) throws SQLException {
        UUID idAttributo = UUID.fromString(resultSet.getString("id"));
        String nome = resultSet.getString("nome");
        return new AttributoModel(idAttributo, nome);
    }

    private AttributoModel makeAttributoOggettoFromResultSet(ResultSet resultSet) throws SQLException {
        UUID idAttributo = UUID.fromString(resultSet.getString("id"));
        String nome = resultSet.getString("nome");
        String valore = resultSet.getString("valore");
        return new AttributoModel(idAttributo, nome, valore);
    }
}
