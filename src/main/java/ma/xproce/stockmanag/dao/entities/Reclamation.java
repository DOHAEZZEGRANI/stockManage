package ma.xproce.stockmanag.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    // Reclamation entity
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

}

