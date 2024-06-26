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
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String description;
    private double price;
    private Integer quantite;
    private String imageUrl;

     @ManyToMany(mappedBy = "achats")
    private List<Customer> clients = new ArrayList<>();
    // Produit entity
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

}
