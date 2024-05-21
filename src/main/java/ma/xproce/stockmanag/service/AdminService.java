package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Admin;

import java.util.List;

public interface AdminService {
    public Admin FindAdminByUserName(String username);
    public Admin AddAdmin(Admin admin);

}
