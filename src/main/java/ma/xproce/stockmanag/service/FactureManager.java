package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Facture;
import ma.xproce.stockmanag.dao.repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class FactureManager implements FactureSevice{
    @Autowired
    private FactureRepository factureRepository;

    @Override
    public Facture creerFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Facture obtenirFactureParId(Integer id) {
        return factureRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Facture introuvable"));
    }

    @Override
    public List<Facture> obtenirToutesLesFactures() {
        return factureRepository.findAll();
    }

    @Override
    public void supprimerFacture(Integer id) {
        factureRepository.deleteById(id);
    }
}
