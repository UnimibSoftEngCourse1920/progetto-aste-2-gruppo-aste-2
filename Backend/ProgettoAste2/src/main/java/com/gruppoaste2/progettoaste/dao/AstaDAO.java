package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AstaModel;

import java.util.List;
import java.util.UUID;

public interface AstaDAO {

    public boolean inserisciAsta(AstaModel asta);

    public boolean eliminaAsta (UUID id);

    public AstaModel trovaAsta(UUID id);

    public List<AstaModel> trovaAste();

    public boolean aggiornaAsta(UUID id, AstaModel astAggiornata);

    public boolean faiOffertaBustaChiusa(UUID id, double offerta);

    public boolean faiOffertaSuperamentoImmediato(UUID id, double offerta);
}
