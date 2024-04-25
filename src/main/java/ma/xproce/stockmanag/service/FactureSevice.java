package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Facture;

import java.util.List;

public interface FactureSevice {
    Facture creerFacture(Facture facture);
    Facture obtenirFactureParId(Integer id);
    List<Facture> obtenirToutesLesFactures();
    void supprimerFacture(Integer id);
}
