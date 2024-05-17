package ma.xproce.stockmanag.web;

import jakarta.validation.Valid;
import ma.xproce.stockmanag.dao.entities.Fournisseur;
import ma.xproce.stockmanag.dao.entities.Reclamation;
import ma.xproce.stockmanag.service.FournisseurService;
import ma.xproce.stockmanag.service.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;

    @PostMapping("/saveReclamation")
    public String ajouterrec(Model model,
                              @Valid Reclamation reclamation,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterreclamation";
        }
        reclamationService.addReclamation(reclamation);
        return "redirect:/indexReclamation";
    }


    @GetMapping("indexlayoutReclamation")
    public String accrec() {
        return "redirect:/indexReclamation";
    }







    @GetMapping("/detailsReclamation")
    public String detailsrec(Model model,
                          @RequestParam(name = "id") Integer id) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        model.addAttribute("reclamationWithDetails", reclamation);
        return "/detailreclamation";
    }



    @GetMapping("/deletereclamation")
    public String deleterec(@RequestParam(name = "id") Integer id) {
        if (reclamationService.DeleteById(id)) {
            return "redirect:/indexReclamation";
        } else {
            return "error";
        }
    }


    @PostMapping("/ajouterrec")
    public String modifierreclamationAction(Model model,
                                            @RequestParam(name = "id") Integer id,
                                            @RequestParam(name = "description") String description) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        if (reclamation != null) {
            reclamation.setDescription(description);
            reclamationService.updateReclamation(reclamation);
            return "redirect:/indexReclamation";
        } else {
            return "error";
        }
    }




    @GetMapping("/ajouterreclamation")
    public String ajouterfournisseur(Model model) {
        model.addAttribute("Reclamation", new Reclamation());
        return "ajouterreclamation";
    }



    @PostMapping("/ajjouterOOnce")
    public String ajouterproduit(Model model,
                                 @Valid Reclamation reclamation,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterreclamation";
        }
        reclamationService.addReclamation(reclamation);
        return "redirect:/listfour";
    }



    @GetMapping("/indexReclamation")
    public String listreclamation(Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "taille", defaultValue = "6") int taille,
                                  @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Reclamation> reclamations = reclamationService.searchReclamation(keyword, page, taille);
        model.addAttribute("listReclamation", reclamations.getContent());

        int[] pages = new int[reclamations.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutReclamation";
    }



    @GetMapping("/editReclamation")
    public String editReclamationAction(Model model, @RequestParam(name = "id") Integer id) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        if (reclamation != null) {
            model.addAttribute("ReclamationToBeUpdated", reclamation);
            return "updateReclamation";
        } else {
            return "error";
        }
    }
}
