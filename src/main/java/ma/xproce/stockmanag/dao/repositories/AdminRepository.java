package ma.xproce.stockmanag.dao.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.stockmanag.dao.entities.Admin;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);
}
