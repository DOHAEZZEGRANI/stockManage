package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Commande;
import ma.xproce.stockmanag.dao.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class CommandeManager implements CommandeSevice{
    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    public Commande creerCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public Commande obtenirCommandeParId(Integer id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Commande introuvable"));
    }

    @Override
    public List<Commande> obtenirToutesLesCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public void supprimerCommande(Integer id) {
        commandeRepository.deleteById(id);
    }

}
