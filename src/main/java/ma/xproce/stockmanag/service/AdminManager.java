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
    private AdminRepository ar;
    @Override
    public Admin FindAdminByUserName(String username) {
        return ar.findByUsername(username);

    }

    @Override
    public Admin AddAdmin(Admin admin) {
        return ar.save(admin);
    }

}
