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



    @PostMapping("/register")
    public String registerUser(@RequestParam(name = "nom") String nom,
                               @RequestParam(name = "tel") String tel,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password) {
        try {
            Customer customer = new Customer();
            customer.setNom(nom);
            customer.setTel(tel);
            customer.setEmail(email);
            customer.setUsername(username);
            customer.setPassword(password);

            Customer addedCustomer = customerService.addcustomer(customer);
            if (addedCustomer != null) {
                System.out.println("Customer added successfully: " + addedCustomer);
            } else {
                System.out.println("Failed to add customer. Please try again.");
            }

            return "redirect:login";
        } catch (Exception e) {
            // Log the exception (you can use a logging framework here)
            System.err.println("An error occurred while registering the customer: " + e.getMessage());
            e.printStackTrace();

            // You can also return a different view in case of error
            return "redirect:error";
        }
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
