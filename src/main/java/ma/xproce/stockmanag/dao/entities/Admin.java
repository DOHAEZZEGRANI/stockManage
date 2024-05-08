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

public class Admin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id ;
    private String nom;
    private String tel;
    private String email;
    private String description;
    private String password;
    @OneToMany(mappedBy = "admin")
    private List<Customer> clients = new ArrayList<>();
}
