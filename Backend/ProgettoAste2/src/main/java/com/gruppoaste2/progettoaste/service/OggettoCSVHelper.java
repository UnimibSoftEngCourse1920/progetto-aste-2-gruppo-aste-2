package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.model.AttributoModel;
import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.model.OggettoModel;

import java.util.ArrayList;
import java.util.List;

class OggettoCSVHelper {

    private static final String NEW_LINE = "\n";
    private static final String SEPARATOR = ",";
    private static final String BEGIN_ATTRIBUTO = "[";
    private static final String END_ATTRIBUTO = "]";

    static List<OggettoModel> readOggettiCSV(String oggettiCSV) {
        List<OggettoModel> oggetti = new ArrayList<>();

        while(!oggettiCSV.isEmpty()) {
            int beginIndex = oggettiCSV.indexOf(NEW_LINE) + 1;
            int endIndex = (oggettiCSV.substring(beginIndex).contains(NEW_LINE)) ?
                    oggettiCSV.indexOf(NEW_LINE, beginIndex) : oggettiCSV.length();

            String oggettoCSV = oggettiCSV.substring(beginIndex, endIndex);
            oggetti.add(readOggettoCSV(oggettoCSV));

            oggettiCSV = oggettiCSV.substring(endIndex);
        }
        return oggetti;
    }

    static String writeOggettiCSV(List<OggettoModel> oggetti) {
        String oggettiCSV = "Oggetti:";

        if(oggetti != null)
            for(OggettoModel oggetto : oggetti)
                oggettiCSV = oggettiCSV.concat(NEW_LINE).concat(writeOggettoCSV(oggetto));

        return oggettiCSV;
    }

    private static OggettoModel readOggettoCSV(String oggettoCSV) {
        String nomeOggetto = readFieldFromOggettoCSV(oggettoCSV);
        oggettoCSV = updateOggettoCSV(oggettoCSV);

        String descrizione = readFieldFromOggettoCSV(oggettoCSV);
        oggettoCSV = updateOggettoCSV(oggettoCSV);

        String urlImmagine = readFieldFromOggettoCSV(oggettoCSV);
        oggettoCSV = updateOggettoCSV(oggettoCSV);

        List<CategoriaModel> categorie = new ArrayList<>();
        while(!oggettoCSV.isEmpty()) {
            String nomeCategoria = readFieldFromOggettoCSV(oggettoCSV);
            oggettoCSV = updateOggettoCSV(oggettoCSV);

            List<AttributoModel> attributi = new ArrayList<>();
            while(!oggettoCSV.isEmpty() && oggettoCSV.charAt(0) == BEGIN_ATTRIBUTO.charAt(0)) {
                String nomeAttributo = readFieldFromOggettoCSV(oggettoCSV);
                oggettoCSV = updateOggettoCSV(oggettoCSV);

                String valore = readFieldFromOggettoCSV(oggettoCSV);
                oggettoCSV = updateOggettoCSV(oggettoCSV);

                attributi.add(new AttributoModel(null, nomeAttributo, valore));
            }

            categorie.add(new CategoriaModel(nomeCategoria, attributi));
        }

        return new OggettoModel(null, nomeOggetto, descrizione, urlImmagine, categorie);
    }

    private static String readFieldFromOggettoCSV(String oggettoCSV) {
        int beginIndex = (oggettoCSV.charAt(0) == BEGIN_ATTRIBUTO.charAt(0)) ? 1 : 0;
        int endIndex;

        if(oggettoCSV.contains(SEPARATOR)) {
            int separatorIndex = oggettoCSV.indexOf(SEPARATOR);
            endIndex = (oggettoCSV.charAt(separatorIndex - 1) == END_ATTRIBUTO.charAt(0)) ?
                    (separatorIndex - 1) : separatorIndex;
        }
        else
            endIndex = (oggettoCSV.charAt(oggettoCSV.length() - 1) == END_ATTRIBUTO.charAt(0)) ?
                    oggettoCSV.length() - 1 : oggettoCSV.length();

        return oggettoCSV.substring(beginIndex, endIndex);
    }

    private static String updateOggettoCSV(String oggettoCSV) {
        if(oggettoCSV.contains(SEPARATOR))
            return oggettoCSV.substring(oggettoCSV.indexOf(SEPARATOR) + 1);
        return "";
    }

    private static String writeOggettoCSV(OggettoModel oggetto) {
        String oggettoCSV = oggetto.getNome().concat(SEPARATOR).concat(oggetto.getDescrizione()).concat(SEPARATOR)
                .concat(oggetto.getUrlImmagine());

        List<CategoriaModel> categorie = oggetto.getCategorie();
        if(categorie != null)
            for(CategoriaModel categoria : categorie) {
                oggettoCSV = oggettoCSV.concat(SEPARATOR).concat(categoria.getId());

                List<AttributoModel> attributi = categoria.getAttributi();
                if(attributi != null)
                    for(AttributoModel attributo : attributi)
                        oggettoCSV = oggettoCSV.concat(SEPARATOR).concat(BEGIN_ATTRIBUTO).concat(attributo.getNome())
                                .concat(SEPARATOR).concat(attributo.getValore()).concat(END_ATTRIBUTO);
            }
        return oggettoCSV;
    }
}
