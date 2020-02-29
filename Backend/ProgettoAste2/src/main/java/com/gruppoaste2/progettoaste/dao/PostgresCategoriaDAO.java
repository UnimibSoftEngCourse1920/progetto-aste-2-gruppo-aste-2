package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.CategoriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Collections;

@Repository("postgres-categoria")
public class PostgresCategoriaDAO implements CategoriaDAO{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresCategoriaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int inserisciCategoria(UUID id, CategoriaModel categoria) {
        final String sql = "INSERT INTO categoria(id,attributi)" +
                " VALUES(?,?)";
        return jdbcTemplate.update(sql,
                id,categoria.getAttributi());
    }

    @Override
    public int eliminaCategoria(UUID id) {
        final String sql = "DELETE FROM categoria WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<CategoriaModel> trovaCategoria(UUID id) {
        final String sql = "SELECT * FROM categoria WHERE id = ?";
        CategoriaModel categoriaTrovata = jdbcTemplate.queryForObject(
                sql, new Object[]{id},
                (resultSet, i) -> {
                    UUID id_d = UUID.fromString(resultSet.getString("id"));
                    Map<String, String> attributi = categoria.getAttributi();
                    return new CategoriaModel(id_d, attributi);
                });
        return Optional.ofNullable(categoriaTrovata);
    }

    @Override
    public List<CategoriaModel> trovaCategorie() {
        final String sql = "SELECT * FROM categoria";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            Map<String, String> attributi = categoria.getAttributi();
            return new CategoriaModel(id, attributi);
        });
    }

    // Quando chiamato anche se magari cambio solo un valore per la categoria devo comunque specificare tutti gli altri
    // altrimenti di default sono null che il database non accetta (per i vincoli di not null)
    @Override
    public int aggiornaCategoria(UUID id, CategoriaModel categoriaAggiornato) {
        final String sql = "UPDATE categoria SET attributi = ? WHERE id = ?";
        return jdbcTemplate.update(sql, attributes(attributi), id);
    }

}
}
