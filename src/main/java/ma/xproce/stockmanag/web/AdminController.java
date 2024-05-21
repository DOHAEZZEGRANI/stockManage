package ma.xproce.stockmanag.web;

import ma.xproce.stockmanag.dao.entities.Admin;
import ma.xproce.stockmanag.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    private AdminService am;


    @GetMapping("/AdminRegister")
    public String getpagedash(){
        return "RegisterAdmin";
    }
    @GetMapping("/Dashboard")
    public String getpagedash1(){
        return "home";
    }

    @PostMapping("/admin/register")
    public String registerAdmin(@RequestParam String username,
                                @RequestParam String password) {
        Admin admin=new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        am.AddAdmin(admin);
        return "redirect:/login";
    }
}