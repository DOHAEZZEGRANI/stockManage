package ma.xproce.stockmanag.web;

import jakarta.validation.Valid;
import ma.xproce.stockmanag.dao.entities.Fournisseur;

import ma.xproce.stockmanag.service.FournisseurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class FournisseurController {
    @Autowired
    private FournisseurService fournisseurService;

    @PostMapping("/saveFournisseur")
    public String ajouterfour(Model model,
                              @Valid Fournisseur fournisseur,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterproduit";
        }
        fournisseurService.addFournisseur(fournisseur);
        return "redirect:/listfour";
    }


    @GetMapping("indexlayoutfour")
    public String acc() {
        return "redirect:/listfour";
    }







    @GetMapping("/detailsfour")
    public String details(Model model,
                         @RequestParam(name = "id") Integer id) {
        Fournisseur fournisseur = fournisseurService.getFournisseurById(id);
        model.addAttribute("fournisseurWithDetails", fournisseur);
        return "/detailfournisseur";
    }



    @GetMapping("/deletefournisseur")
    public String deletefournisseur(@RequestParam(name = "id") Integer id) {
        if (fournisseurService.DeleteById(id)) {
            return "redirect:/listfour";
        } else {
            return "error";
        }
    }


    @PostMapping("/ajouterr")
    public String modifierfournisseurAction(Model model,
                                        @RequestParam(name = "id") Integer id,
                                        @RequestParam(name = "nom") String nom,
                                        @RequestParam(name = "tel") String tel,
                                        @RequestParam(name = "description") String description) {
        Fournisseur fournisseur = fournisseurService.getFournisseurById(id);
        if (fournisseur != null) {
            fournisseur.setNom(nom);
            fournisseur.setTel(tel);
            fournisseur.setDescription(description);
            fournisseurService.updateFournisseur(fournisseur);
            return "redirect:/listfour";
        } else {
            return "error";
        }
    }



    @GetMapping("/ajouterfournisseur")
    public String ajouterfournisseur(Model model) {
        model.addAttribute("Fournisseur", new Fournisseur());
        return "ajouterfournisseur";
    }



    @PostMapping("/ajouterOOnce")
    public String ajouterproduit(Model model,
                                 @Valid Fournisseur fournisseur,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterfournisseur";
        }
        fournisseurService.addFournisseur(fournisseur);
        return "redirect:/listfour";
    }



    @GetMapping("/listfour")
    public String listfournisseur(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "taille", defaultValue = "6") int taille,
                              @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Fournisseur> fournisseurs = fournisseurService.searchFournisseur(keyword, page, taille);
        model.addAttribute("listFournisseurs", fournisseurs.getContent());

        int[] pages = new int[fournisseurs.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutfournisseur";
    }



    @GetMapping("/editfournisseur")
    public String editproduitAction(Model model, @RequestParam(name = "id") Integer id) {
        Fournisseur fournisseur = fournisseurService.getFournisseurById(id);
        if (fournisseur != null) {
            model.addAttribute("FournisseurToBeUpdated", fournisseur);
            return "updatefournisseur";
        } else {
            return "error";
        }
    }
}
