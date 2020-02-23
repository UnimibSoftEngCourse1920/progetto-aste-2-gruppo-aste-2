package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;

import java.util.List;
import java.util.UUID;

public interface UtenteRegistratoDAO {

    public boolean insersciUtenteRegistrato(UtenteRegistratoModel utenteRegistrato);

    public boolean eliminaUtenteRegistrato(UUID id);

    public UtenteRegistratoModel trovaUtenteRegistro(UUID id);

    public List<UtenteRegistratoModel> trovaTuttiUtentiRegistrati();

    public boolean aggiornaUtenteRegistrto(UUID id, UtenteRegistratoModel utenteAggiornato);

}
