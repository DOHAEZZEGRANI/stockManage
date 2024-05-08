package ma.xproce.stockmanag.web;

import jakarta.validation.Valid;
import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Register"; // Vue HTML du formulaire d'inscription
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("customer") Customer customer,
                               BindingResult bindingResult, Model model) {
        // Validation côté serveur
        if (!customer.getPassword().equals(customer.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Les mots de passe ne correspondent pas");
        }

        if (bindingResult.hasErrors()) {
            // S'il y a des erreurs de validation, revenir à la page d'inscription avec les erreurs affichées
            return "Register";
        }

        // Encoder le mot de passe avant de l'enregistrer dans la base de données
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        // Votre logique pour enregistrer le client
        // Assurez-vous que la méthode registerUser retourne une indication de succès ou d'échec
        boolean registrationSuccess = customerService.registerUser(customer);

        if (registrationSuccess) {
            // Redirection après l'inscription réussie
            return "redirect:/login";
        } else {
            // Gestion de l'échec de l'inscription
            model.addAttribute("error", "Une erreur s'est produite lors de l'inscription. Veuillez réessayer.");
            return "Register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Vue HTML du formulaire de connexion
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "login"; // Vue HTML de la page d'accès refusé
    }
}
