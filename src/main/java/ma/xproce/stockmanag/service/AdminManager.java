package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Admin;
import ma.xproce.stockmanag.dao.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminManager implements AdminService{
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin creerAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin obtenirAdminParId(Integer id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Admin introuvable"));
    }

    @Override
    public List<Admin> obtenirTousLesAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public void supprimerAdmin(Integer id) {
        adminRepository.deleteById(id);
    }

}
