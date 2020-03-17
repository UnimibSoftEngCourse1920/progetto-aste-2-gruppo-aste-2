package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AttributoModel;
import com.gruppoaste2.progettoaste.model.CategoriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-categoria")
public class PostgresCategoriaDAO implements CategoriaDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresCategoriaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String aggiungiCategoria(CategoriaModel categoria) {
        final String sql = "INSERT INTO categoria(id) " +
                "VALUES(?, ?)";
        if(jdbcTemplate.update(sql, categoria.getId()) == 0)
            return null;

        List<AttributoModel> attributi = categoria.getAttributi();
        if(attributi != null)
            for(AttributoModel attributo : attributi)
                if(aggiungiAttributoCategoria(categoria.getId(), attributo) == null)
                    return null;

        return categoria.getId();
    }

    @Override
    public UUID aggiungiAttributoCategoria(UUID idAttributo, String idCategoria, AttributoModel attributo) {
        final String sql = "INSERT INTO attributo(id, id_categoria, nome) " +
                "VALUES(?, ?, ?)";
        if(jdbcTemplate.update(sql, idAttributo, idCategoria, attributo.getNome()) == 0)
            return null;
        return idAttributo;
    }

    @Override
    public int eliminaCategoria(String idCategoria) {
        if(eliminaAttributiCategoria(idCategoria) == 0)
            return 0;

        final String sql = "DELETE FROM categoria WHERE id = ?";
        return jdbcTemplate.update(sql, idCategoria);
    }

    @Override
    public int eliminaAttributiCategoria(String idCategoria) {
        final String sql = "DELETE FROM attributo WHERE id_categoria = ?";
        return jdbcTemplate.update(sql, idCategoria);
    }

    @Override
    public Optional<CategoriaModel> trovaCategoria(String idCategoria) {
        final String sql = "SELECT * FROM categoria WHERE id = ?";
        List<CategoriaModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> makeCategoriaFromResultSet(resultSet),
                idCategoria);
        CategoriaModel returnable = (results.isEmpty()) ? null : results.get(0);
        return Optional.ofNullable(returnable);
    }

    @Override
    public List<CategoriaModel> trovaCategorie() {
        final String sql = "SELECT * FROM categoria";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeCategoriaFromResultSet(resultSet));
    }

    @Override
    public List<AttributoModel> trovaAttributiCategoria(String idCategoria) {
        final String sql = "SELECT * FROM attributo WHERE id_categoria = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAttributoFromResultSet(resultSet),
                idCategoria);
    }

    @Override
    public List<CategoriaModel> trovaCategorieOggetto(UUID idOggetto) {
        final String sql = "SELECT * FROM categoria " +
                "JOIN categoria_oggetto ON id = id_categoria " +
                "WHERE id_oggetto = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeCategoriaFromResultSet(resultSet),
                idOggetto);
    }

    @Override
    public String valoreAttributoOggetto(UUID idOggetto, UUID idAttributo) {
        final String sql = "SELECT valore FROM attributo_oggetto WHERE id_oggetto = ? AND id_attributo = ?";
        return jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> resultSet.getString("valore"),
                idOggetto, idAttributo);
    }

    @Override
    public int aggiornaCategoria(String idCategoria, CategoriaModel categoriaAggiornata) {
        final String sql = "UPDATE categoria SET id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, categoriaAggiornata.getId(), idCategoria);
    }

    @Override
    public boolean controllaCategoriaEsiste(CategoriaModel categoria) {
        final String sql = "SELECT EXISTS" +
                "(SELECT 1 FROM categoria WHERE id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, categoria.getId());
    }

    @Override
    public int assegnaCategoriaAdOggetto(UUID idOggetto, String idCategoria) {
        final String sql = "INSERT INTO categoria_oggetto(id_oggetto, id_categoria) " +
                "VALUES(?, ?)";
        if(jdbcTemplate.update(sql, idOggetto, idCategoria) == 0)
            return 0;

        List<AttributoModel> attributi = trovaAttributiCategoria(idCategoria);
        if(attributi != null)
            for(AttributoModel attributo : attributi)
                if(assegnaValoreAttributoAdOggetto(idOggetto, attributo) == 0)
                    return 0;

        return 1;
    }

    @Override
    public int rimuoviCategoriaDaOggetto(UUID idOggetto, String idCategoria) {
        List<AttributoModel> attributi = trovaAttributiCategoria(idCategoria);
        if(attributi != null)
            for(AttributoModel attributo : attributi)
                if(rimuoviValoreAttributoDaOggetto(idOggetto, attributo.getId()) == 0)
                    return 0;

        final String sql = "DELETE FROM categoria_oggetto WHERE id_oggetto = ? AND id_categoria = ?";
        return jdbcTemplate.update(sql, idOggetto, idCategoria);
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

    private CategoriaModel makeCategoriaFromResultSet(ResultSet resultSet) throws SQLException {
        String idCategoria = resultSet.getString("id");
        List<AttributoModel> attributi = trovaAttributiCategoria(idCategoria);
        return new CategoriaModel(idCategoria, attributi);
    }

    private AttributoModel makeAttributoFromResultSet(ResultSet resultSet) throws SQLException {
        UUID idAttributo = UUID.fromString(resultSet.getString("id"));
        String nome = resultSet.getString("nome");
        return new AttributoModel(idAttributo, nome, null);
    }
}
