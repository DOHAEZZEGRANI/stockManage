package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Admin;

import java.util.List;

public interface AdminService {
    Admin creerAdmin(Admin admin);
    Admin obtenirAdminParId(Integer id);
    List<Admin> obtenirTousLesAdmins();
    void supprimerAdmin(Integer id);
}
