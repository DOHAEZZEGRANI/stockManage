package ma.xproce.stockmanag.web;

import jakarta.validation.Valid;
import ma.xproce.stockmanag.dao.entities.Commande;
import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.dao.entities.Produit;

import ma.xproce.stockmanag.dao.entities.Stock;
import ma.xproce.stockmanag.service.CommandeSevice;
import ma.xproce.stockmanag.service.CustomerService;
import ma.xproce.stockmanag.service.ProduitService;
import ma.xproce.stockmanag.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProduitController {

    @Autowired
    private ProduitService produitService;
    @Autowired
    private StockService stockService;
    @Autowired
    private CommandeSevice commandeSevice;
    @Autowired
    private CustomerService customerService;

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

    @GetMapping("home")
    public String accu() {
        return "home";
    }
    @GetMapping("about")
    public String accccu() {
        return "about";
    }





    @GetMapping("/details")
    public String detail(Model model,
                              @RequestParam(name = "id") Integer id) {
        Produit produit = produitService.getProduitById(id);
        Stock stock  = stockService.getStockById(id);


        model.addAttribute("produitWithDetails", produit);
        model.addAttribute("stockWithDetails", stock);

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
    @GetMapping("/panier")
    public String commanderproduit(Model model,
            @RequestParam(name = "id") Integer id,@RequestParam(name = "quantity") Integer quantity
                                   ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Customer customer=customerService.findByUsername(username);
        Produit produit=produitService.getProduitById(id);
        Commande commande=new Commande();
        commande.setStock(produit.getStock());
        commande.setDescription(produit.getDescription());
        commande.setQuantitecommander(quantity);
        commande.setCustomer(customer);
        commandeSevice.addCommande(commande);

            return "redirect:/indexcommande";

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
        List<Stock> stocks=stockService.getAllStocks();
        model.addAttribute("Stock", stocks);

        return "ajouterproduit";
    }



    @PostMapping("/ajouterOnce")
    public String ajouterproduit(Model model, @Valid Stock stock,


                                 @Valid Produit produit,
                                 BindingResult bindingResult) {


        stock.setTotal(produit.getQuantite());

        produit.setStock(stock);

        stockService.addStock(stock);
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

            model.addAttribute("productToBeUpdated", produit);
            return "updateProduct";

    }
    @GetMapping("/login")
    public String login() {
        return "login"; // Votre page de connexion
    }
    @GetMapping("/Register")
    public String re() {
        return "RegisterCustomer"; // Votre page de connexion
    }
    @GetMapping("")
    public String choose() {
        return "log"; // Votre page de connexion
    }
}

