package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Commande;
import ma.xproce.stockmanag.dao.entities.Commande;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CommandeSevice {
    public Boolean DeleteById(Integer id);
    public Commande addCommande(Commande Commande);
    public List<Commande> getAllCommande();
    public Page<Commande> searchCommandes(String keyword, int page, int taille);
    public List<Commande> getByKeyword(String keyword);
    public Commande getCommandeById(Integer id);
    public Commande updateCommande(Commande Commande);
    public Boolean deleteCommande(Integer id);


}
