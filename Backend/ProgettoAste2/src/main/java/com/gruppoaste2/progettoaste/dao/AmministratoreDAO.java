package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;

import java.util.List;
import java.util.UUID;

public interface AmministratoreDAO {
    public boolean insersciamministratore(AmministratoreModel amministratoreModel);

    public boolean eliminaAmministratore(UUID id);

    public AmministratoreModel trovaAmministratore(UUID id);

    public List<AmministratoreModel> trovaAmministratori();

    public boolean aggiornaAmministratore(UUID id, AmministratoreModel amministratoreAggiornato);
}
