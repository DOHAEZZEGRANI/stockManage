package ma.xproce.stockmanag.web;

import jakarta.validation.Valid;
import ma.xproce.stockmanag.dao.entities.Produit;

import ma.xproce.stockmanag.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @PostMapping("/savevideo")
    public String ajouterprod(Model model,
                             @Valid Produit produit,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterproduit";
        }
        produitService.addProduit(produit);
        return "redirect:/indexpage";
    }


    @GetMapping("indexlayout")
    public String acc() {
        return "redirect:/indexpage";
    }

    @GetMapping("")
    public String accu() {
        return "redirect:/indexpage";
    }





    @GetMapping("/details")
    public String detail(Model model,
                              @RequestParam(name = "id") Integer id) {
        Produit produit = produitService.getProduitById(id);
        model.addAttribute("produitWithDetails", produit);
        return "/detailproduit";
    }



    @GetMapping("/delete")
    public String deleteproduit(@RequestParam(name = "id") Integer id) {
        if (produitService.DeleteById(id)) {
            return "redirect:/indexpage";
        } else {
            return "error";
        }
    }


    @PostMapping("/ajouter")
    public String modifierProduitAction(Model model,
                                        @RequestParam(name = "id") Integer id,
                                        @RequestParam(name = "nom") String nom,
                                        @RequestParam(name = "price") Double price,
                                        @RequestParam(name = "description") String description) {
        Produit produit = produitService.getProduitById(id);
        if (produit != null) {
            produit.setNom(nom);
            produit.setPrice(price);
            produit.setDescription(description);
            produitService.updateProduit(produit);
            return "redirect:/indexpage";
        } else {
            return "error";
        }
    }



    @GetMapping("/ajouterproduit")
    public String ajouterproduit(Model model) {
        model.addAttribute("Product", new Produit());
        return "ajouterproduit";
    }



    @PostMapping("/ajouterOnce")
    public String ajouterproduit(Model model,
                                 @Valid Produit produit,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterproduit";
        }
        produitService.addProduit(produit);
        return "redirect:/indexpage";
    }



    @GetMapping("/indexpage")
    public String listProduit(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "taille", defaultValue = "6") int taille,
                              @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Produit> produits = produitService.searchProduits(keyword, page, taille);
        model.addAttribute("listProducts", produits.getContent());

        int[] pages = new int[produits.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "indexlayout";
    }



    @GetMapping("/editProduit")
    public String editproduitAction(Model model, @RequestParam(name = "id") Integer id) {
        Produit produit = produitService.getProduitById(id);
        if (produit != null) {
            model.addAttribute("productToBeUpdated", produit);
            return "updateProduct";
        } else {
            return "error";
        }
    }
}

