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

    private final AttributoDAO attributoDAO;

    @Autowired
    public PostgresCategoriaDAO(JdbcTemplate jdbcTemplate, AttributoDAO attributoDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.attributoDAO = attributoDAO;
    }

    @Override
    public String aggiungiCategoria(CategoriaModel categoria) {
        final String sql = "INSERT INTO categoria(id) " +
                "VALUES(?)";
        if(jdbcTemplate.update(sql, categoria.getId()) == 0)
            return null;


        List<AttributoModel> attributi = categoria.getAttributi();
        if(attributi != null)
            for(AttributoModel attributo : attributi)
                if(attributoDAO.aggiungiAttributo(categoria.getId(), attributo) == null)
                    return null;



        return categoria.getId();
    }

    @Override
    public int eliminaCategoria(String idCategoria) {
        List<AttributoModel> attributi = attributoDAO.trovaAttributiCategoria(idCategoria);
        if(attributi != null)
            for(AttributoModel attributo : attributi)
                if(attributoDAO.eliminaAttributo(attributo.getId()) == 0)
                    return 0;

        final String sql = "DELETE FROM categoria WHERE id = ?";
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
    public List<CategoriaModel> trovaCategorieOggetto(UUID idOggetto) {
        final String sql = "SELECT * FROM categoria " +
                "JOIN categoria_oggetto ON id = id_categoria " +
                "WHERE id_oggetto = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeCategoriaOggettoFromResultSet(resultSet),
                idOggetto);
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
        return jdbcTemplate.update(sql, idOggetto, idCategoria);
    }

    @Override
    public int rimuoviCategoriaDaOggetto(UUID idOggetto, String idCategoria) {
        List<AttributoModel> attributi = attributoDAO.trovaAttributiCategoria(idCategoria);
        if(attributi != null)
            for(AttributoModel attributo : attributi)
                if(attributoDAO.rimuoviValoreAttributoDaOggetto(idOggetto, attributo.getId()) == 0)
                    return 0;

        final String sql = "DELETE FROM categoria_oggetto WHERE id_oggetto = ? AND id_categoria = ?";
        return jdbcTemplate.update(sql, idOggetto, idCategoria);
    }

    private CategoriaModel makeCategoriaFromResultSet(ResultSet resultSet) throws SQLException {
        String idCategoria = resultSet.getString("id");
        List<AttributoModel> attributi = attributoDAO.trovaAttributiCategoria(idCategoria);
        return new CategoriaModel(idCategoria, attributi);
    }

    private CategoriaModel makeCategoriaOggettoFromResultSet(ResultSet resultSet) throws SQLException {
        String idCategoria = resultSet.getString("id");

        UUID idOggetto = UUID.fromString(resultSet.getString("id_oggetto"));
        List<AttributoModel> attributi = attributoDAO.trovaAttributiOggetto(idOggetto);

        return new CategoriaModel(idCategoria, attributi);
    }
}
