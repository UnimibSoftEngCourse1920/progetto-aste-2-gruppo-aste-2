package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AstaModel;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class PostgresAstaDAO implements AstaDAO {
    @Override
    public boolean inserisciAsta(AstaModel asta) {
        return false;
    }

    @Override
    public boolean eliminaAsta(UUID id) {
        return false;
    }

    @Override
    public AstaModel trovaAsta(UUID id) {
        return null;
    }

    @Override
    public List<AstaModel> trovaAste() {
        return Collections.emptyList();
    }

    @Override
    public boolean aggiornaAsta(UUID id, AstaModel astAggiornata) {
        return false;
    }

    @Override
    public boolean faiOffertaBustaChiusa(UUID id, double offerta) {
        return false;
    }

    @Override
    public boolean faiOffertaSuperamentoImmediato(UUID id, double offerta) {
        return false;
    }
}
