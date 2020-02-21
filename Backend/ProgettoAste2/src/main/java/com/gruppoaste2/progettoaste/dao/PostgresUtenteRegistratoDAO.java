package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository("postgres")
public class PostgresUtenteRegistratoDAO implements UtenteRegistratoDAO{

    @Override
    public boolean insersciUtenteRegistrato(UtenteRegistratoModel utenteRegistrto) {
        return false;
    }

    @Override
    public boolean eliminaUtenteRegistrato(UUID id) {
        return false;
    }

    @Override
    public UtenteRegistratoModel trovaUtenteRegistro(UUID id) {
        return null;
    }

    @Override
    public List<UtenteRegistratoModel> trovaTuttiUtentiRegistrati() {
        return Collections.emptyList();
    }

    @Override
    public boolean aggiornaUtenteRegistrto(UUID id, UtenteRegistratoModel utenteAggiornato) {
        return false;
    }
}
