package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository("postgres-amministratore")
public class PostgresAmministratoreDAO implements AmministratoreDAO {
    @Override
    public boolean insersciamministratore(AmministratoreModel amministratoreModel) {
        return false;
    }

    @Override
    public boolean eliminaAmministratore(UUID id) {
        return false;
    }

    @Override
    public AmministratoreModel trovaAmministratore(UUID id) {
        return null;
    }

    @Override
    public List<AmministratoreModel> trovaAmministratori() {
        return Collections.emptyList();
    }

    @Override
    public boolean aggiornaAmministratore(UUID id, AmministratoreModel amministratoreAggiornato) {
        return false;
    }
}
