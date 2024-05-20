package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Commande;
import ma.xproce.stockmanag.dao.entities.Commande;
import ma.xproce.stockmanag.dao.repositories.CommandeRepository;
import ma.xproce.stockmanag.dao.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeManager implements CommandeSevice{
    @Autowired
    private CommandeRepository CommandeRepository;


    @Override
    public Boolean DeleteById(Integer id) {
        try {
            CommandeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Commande addCommande(Commande Commande) {
        try {
            return CommandeRepository.save(Commande);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Commande> getAllCommande() {
        try {
            return CommandeRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Page<Commande> searchCommandes(String keyword, int page, int taille) {
        return CommandeRepository.findByDescriptionContains(keyword, (Pageable) PageRequest.of(page, taille));
    }

    @Override
    public List<Commande> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Commande getCommandeById(Integer id) {
        return CommandeRepository.findById(id).get();
    }

    @Override
    public Commande updateCommande(Commande Commande) {
        try {
            return CommandeRepository.save(Commande);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteCommande(Integer id) {
        try {
            CommandeRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


}
