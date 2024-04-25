package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Lignedecommande;

import java.util.List;

public interface LignedecommandeService {
    Lignedecommande creerLignedecommande(Lignedecommande lignedecommande);
    Lignedecommande obtenirLignedecommandeParId(Integer id);
    List<Lignedecommande> obtenirToutesLesLignedecommandes();
    void supprimerLignedecommande(Integer id);
}
