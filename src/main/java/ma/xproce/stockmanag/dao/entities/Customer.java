package ma.xproce.stockmanag.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id ;
    private String nom;
    private String tel;
    private String email;
    private String description;
    private String password;
    private String username;
    private String confirmPassword;
    @ManyToMany
    @JoinTable(
            name = "achat",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id")
    )
    private List<Produit> achats = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
