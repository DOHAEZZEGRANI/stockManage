package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Lignedecommande;
import ma.xproce.stockmanag.dao.repositories.LignedecommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LignedecommandeManager implements LignedecommandeService {
    @Autowired
    private LignedecommandeRepository lignedecommandeRepository;

    @Override
    public Lignedecommande creerLignedecommande(Lignedecommande lignedecommande) {
        return lignedecommandeRepository.save(lignedecommande);
    }

    @Override
    public Lignedecommande obtenirLignedecommandeParId(Integer id) {
        return lignedecommandeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lignedecommande introuvable"));
    }

    @Override
    public List<Lignedecommande> obtenirToutesLesLignedecommandes() {
        return lignedecommandeRepository.findAll();
    }

    @Override
    public void supprimerLignedecommande(Integer id) {
        lignedecommandeRepository.deleteById(id);
    }
}
