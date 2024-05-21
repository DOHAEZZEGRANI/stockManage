package ma.xproce.stockmanag.web;

import jakarta.validation.Valid;
import ma.xproce.stockmanag.dao.entities.Commande;

import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.dao.entities.Stock;
import ma.xproce.stockmanag.service.CommandeSevice;
import ma.xproce.stockmanag.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private CustomerService customerService;

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
    @GetMapping("listcomande")
    public String listCo(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "taille", defaultValue = "6") int taille,
                               @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Commande> Commandes = CommandeService.searchCommandes(keyword, page, taille);
        model.addAttribute("listCommandes", Commandes.getContent());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Customer customer=customerService.findByUsername(username);
        model.addAttribute("Customer", customer);


        int[] pages = new int[Commandes.getTotalPages()];
        model.addAttribute("pages", pages);

        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);

        return "listcommande";
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
            return "redirect:/indexcommande";
        } else {
            return "error";
        }
    }
    @PostMapping("/validation")
    public String validercommande(@RequestParam(name = "id") Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Customer customer=customerService.findByUsername(username);
        Commande commande=CommandeService.getCommandeById(id);
        commande.setValider(true);
        commande.setCustomer(customer);
        CommandeService.updateCommande(commande);
        return "redirect:/indexcommande";
    }



    @PostMapping("/ajouterco")
    public String modifierCommandeAction(Model model,
                                         @RequestParam(name = "id") Integer id,
                                         @RequestParam(name = "description") String description,
                                         @RequestParam(name = "quantitecommander") Integer quantitecommander
                                        ) {
        Commande commande = CommandeService.getCommandeById(id);
        if (commande != null) {
            commande.setDescription(description);

            commande.setDescription(description);
            commande.setValider(false);
            commande.setQuantitecommander(quantitecommander);
            CommandeService.updateCommande(commande);
            return "redirect:/indexcommande";
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Customer customer=customerService.findByUsername(username);
        model.addAttribute("Customer", customer);


        int[] pages = new int[Commandes.getTotalPages()];
        model.addAttribute("pages", pages);

        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutcommande";
    }


    @GetMapping("/editCommande")
    public String editCommandeAction(Model model, @RequestParam(name = "id") Integer id) {
        Commande Commande = CommandeService.getCommandeById(id);

            model.addAttribute("CommandeToBeUpdated", Commande);
            return "updatecommande";

    }
}



