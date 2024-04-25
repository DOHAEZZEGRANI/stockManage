package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Reclamation;
import ma.xproce.stockmanag.dao.repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class ReclamationManager implements ReclamationService{
    @Autowired
    private ReclamationRepository reclamationRepository;

    @Override
    public Reclamation creerReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation obtenirReclamationParId(Integer id) {
        return reclamationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Réclamation introuvable"));
    }

    @Override
    public List<Reclamation> obtenirToutesLesReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public void supprimerReclamation(Integer id) {
        reclamationRepository.deleteById(id);
    }
}
