package ma.xproce.stockmanag.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public String loginCustomer(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpSession session) {
        Customer customer = customerService.findByUsername(username);

        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            // Authentification réussie
            session.setAttribute("loggedInCustomer", customer);
            return "redirect:/indexlayout"; // Rediriger vers le tableau de bord après connexion
        } else {
            // Authentification échouée, rediriger vers la page de connexion avec un message d'erreur
            return "redirect:/login?error=true";
        }
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "ajouterCustomer";
    }

    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute("customer") @Valid Customer customer,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ajouterCustomer";
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.addcustomer(customer);
        return "redirect:/login"; // Assuming this is the correct redirect URL
    }

    @PostMapping("/saveCustomer")
    public String ajoutercustomer(Model model,
                                  @Valid Customer customer,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterCustomer";
        }
        customerService.addcustomer(customer);
        return "redirect:/indexCustomer";
    }

    @GetMapping("layoutCustomer")
    public String accCustomer() {
        return "redirect:/indexCustomer";
    }

    @GetMapping("/detailsCustomer")
    public String detailCustomer(Model model,
                                 @RequestParam(name = "id") Integer id) {
        Customer customer = customerService.getcustomerById(id);
        model.addAttribute("CustomerWithDetails", customer);
        return "/detailCustomer";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam(name = "id") Integer id) {
        if (customerService.DeleteById(id)) {
            return "redirect:/indexCustomer";
        } else {
            return "error";
        }
    }

    @PostMapping("/modifierCustomer")
    public String modifierCustomerAction(Model model,
                                         @RequestParam(name = "id") Integer id,
                                         @RequestParam(name = "nom") String nom,
                                         @RequestParam(name = "tel") String tel,
                                         @RequestParam(name = "email") String email) {
        Customer customer = customerService.getcustomerById(id);
        if (customer != null) {
            customer.setNom(nom);
            customer.setTel(tel);
            customer.setEmail(email);

            customerService.updatecustomer(customer);
            return "redirect:/indexCustomer";
        } else {
            return "error";
        }
    }

    @GetMapping("/ajouterCustomer")
    public String ajouterCustomer(Model model) {
        model.addAttribute("Customer", new Customer());
        return "ajouterCustomer";
    }

    @PostMapping("/ajoutterOnce")
    public String ajouterCustomer(Model model,
                                  @Valid Customer customer,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterCustomer";
        }
        customerService.addcustomer(customer);
        return "redirect:/indexCustomer";
    }

    @GetMapping("/indexCustomer")
    public String listCustomer(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "taille", defaultValue = "6") int taille,
                               @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Customer> customers = customerService.searchcustomers(keyword, page, taille);
        model.addAttribute("listCustomer", customers.getContent());
        int[] pages = new int[customers.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutCustomer";
    }

    @GetMapping("/editCustomer")
    public String editCustomerAction(Model model, @RequestParam(name = "id") Integer id) {
        Customer customer = customerService.getcustomerById(id);
        if (customer != null) {
            model.addAttribute("CustomerToBeUpdated", customer);
            return "updateCustomer";
        } else {
            return "error";
        }
    }


}
