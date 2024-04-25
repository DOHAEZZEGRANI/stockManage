package ma.xproce.stockmanag.dao.repositories;

import ma.xproce.stockmanag.dao.entities.Admin;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
