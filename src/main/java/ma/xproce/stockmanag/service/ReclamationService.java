package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Reclamation;

import java.util.List;

public interface ReclamationService {
    Reclamation creerReclamation(Reclamation reclamation);
    Reclamation obtenirReclamationParId(Integer id);
    List<Reclamation> obtenirToutesLesReclamations();
    void supprimerReclamation(Integer id);
}
