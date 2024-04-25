package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Commande;

import java.util.List;

public interface CommandeSevice {
    Commande creerCommande(Commande commande);
    Commande obtenirCommandeParId(Integer id);
    List<Commande> obtenirToutesLesCommandes();
    void supprimerCommande(Integer id);
}
