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
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer total;
    private String description;

    @OneToMany(mappedBy = "stock")
    private List<Lignedecommande> lignesCommande = new ArrayList<>();

}
