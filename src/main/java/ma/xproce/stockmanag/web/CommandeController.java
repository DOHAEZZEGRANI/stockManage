package ma.xproce.stockmanag.web;

import jakarta.validation.Valid;
import ma.xproce.stockmanag.dao.entities.Commande;

import ma.xproce.stockmanag.dao.entities.Stock;
import ma.xproce.stockmanag.service.CommandeSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class CommandeController {

    @Autowired
    private CommandeSevice CommandeService;

    @PostMapping("/savecommande")
    public String ajoutercommande(Model model,
                                  @Valid Commande Commande,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterCommande";
        }
        CommandeService.addCommande(Commande);
        return "redirect:/indexpage";
    }


    @GetMapping("indexlayoutco")
    public String accommande() {
        return "redirect:/indexpage";
    }




    @GetMapping("/detailsco")
    public String detailcommande(Model model,
                                 @RequestParam(name = "id") Integer id) {
        Commande Commande = CommandeService.getCommandeById(id);
        model.addAttribute("CommandeWithDetails", Commande);
        return "/detailCommande";
    }


    @GetMapping("/deleteco")
    public String deleteCommande(@RequestParam(name = "id") Integer id) {
        if (CommandeService.DeleteById(id)) {
            return "redirect:/indexpage";
        } else {
            return "error";
        }
    }


    @PostMapping("/ajouterco")
    public String modifierCommandeAction(Model model,
                                         @RequestParam(name = "id") Integer id,
                                         @RequestParam(name = "description") String description,
                                         @RequestParam(name = "stock") Stock stock) {
        Commande commande = CommandeService.getCommandeById(id);
        if (commande != null) {
            commande.setDescription(description);
            commande.setStock(stock);
            commande.setDescription(description);
            CommandeService.updateCommande(commande);
            return "redirect:/indexpage";
        } else {
            return "error";
        }
    }


    @GetMapping("/ajouterCommande")
    public String ajouterCommande(Model model) {
        model.addAttribute("Product", new Commande());
        return "ajouterCommande";
    }


    @PostMapping("/ajouterOnceco")
    public String ajouterCommande(Model model,
                                  @Valid Commande Commande,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterCommande";
        }
        CommandeService.addCommande(Commande);
        return "redirect:/indexpage";
    }


    @GetMapping("/indexcommande")
    public String listCommande(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "taille", defaultValue = "6") int taille,
                               @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Commande> Commandes = CommandeService.searchCommandes(keyword, page, taille);
        model.addAttribute("listCommandes", Commandes.getContent());

        int[] pages = new int[Commandes.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutcommande";
    }


    @GetMapping("/editCommande")
    public String editCommandeAction(Model model, @RequestParam(name = "id") Integer id) {
        Commande Commande = CommandeService.getCommandeById(id);
        if (Commande != null) {
            model.addAttribute("productToBeUpdated", Commande);
            return "updateProduct";
        } else {
            return "error";
        }
    }
}



