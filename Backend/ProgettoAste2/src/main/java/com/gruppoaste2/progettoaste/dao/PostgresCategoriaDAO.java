package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AttributoModel;
import com.gruppoaste2.progettoaste.model.CategoriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-categoria")
public class PostgresCategoriaDAO implements CategoriaDAO{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresCategoriaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int inserisciCategoria(UUID idCategoria, CategoriaModel categoria) {
        final String sql = "INSERT INTO categoria (id, nome) VALUES (?, ?)";
        return jdbcTemplate.update(sql, idCategoria, categoria.getNome());
    }

    @Override
    public int eliminaCategoria(UUID id) {
        final String sql = "DELETE FROM categoria WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<CategoriaModel> trovaCategoria(UUID id) {
        final String sql = "SELECT * FROM categoria WHERE id = ?";
        List<CategoriaModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    UUID idTrovato = UUID.fromString(resultSet.getString("id"));
                    String nomeTrovato = resultSet.getString("nome");
                    return new CategoriaModel(idTrovato, Collections.emptyMap(), nomeTrovato);
                },
                id);

        CategoriaModel returnable = (results.isEmpty())? null : results.get(0);
        return  Optional.ofNullable(returnable);

    }

    @Override
    public List<CategoriaModel> trovaCategorie() {
        final String sql = "SELECT * FROM categoria";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    UUID idTrovato = UUID.fromString(resultSet.getString("id"));
                    String nomeTrovato = resultSet.getString("nome");
                    return new CategoriaModel(idTrovato, Collections.emptyMap(), nomeTrovato);
                });
    }

    @Override
    public int aggiornaCategoria(UUID id, CategoriaModel categoriaAggiornata) {
        final String sql = "UPDATE categoria SET nome = ?";
        return jdbcTemplate.update(sql,categoriaAggiornata.getNome());
    }

    @Override
    public List<AttributoModel> trovaAttributiCategoria(UUID idCategoria) {
        final String sql = "SELECT * FROM attributo";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String nome = resultSet.getString("nome");
                    return new AttributoModel(id,nome);
                },
                idCategoria);
    }

    @Override
    public List<CategoriaModel> trovaCategorieOggetto(UUID idOggetto) {
        final String sql = "SELECT c.id, c.nome FROM categoria_oggetto as co, categoria as c WHERE co.id_oggetto = ? AND c.id_categoria = c.id";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String nome = resultSet.getString("nome");
                    return new CategoriaModel(id, Collections.emptyMap(), nome);
                },
                idOggetto);
    }

    @Override
    public String valoreAttributoOggetto(UUID idOggetto, UUID idAttributo) {
        final String sql = "SELECT valore FROM attributo_oggetto WHERE id_oggetto = ? AND id_categoria = ?";
        return jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> resultSet.getString("valore"),
                idOggetto, idAttributo);
    }
}

