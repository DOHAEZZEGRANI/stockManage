package ma.xproce.stockmanag.web;

import jakarta.validation.Valid;
import ma.xproce.stockmanag.dao.entities.Produit;

import ma.xproce.stockmanag.dao.entities.Stock;
import ma.xproce.stockmanag.service.ProduitService;
import ma.xproce.stockmanag.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/savestock")
    public String ajoutersto(Model model,
                              @Valid Stock stock,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterstock";
        }
        stockService.addStock(stock);
        return "redirect:/liststock";
    }


    @GetMapping("indexstocklayout")
    public String accstock() {
        return "redirect:/liststock";
    }






    @GetMapping("/stockdetails")
    public String detail(Model model,
                         @RequestParam(name = "id") Integer id) {
        Stock stock = stockService.getStockById(id);
        model.addAttribute("stockWithDetails", stock);
        return "/detailstock";
    }



    @GetMapping("/deletestock")
    public String deleteproduit(@RequestParam(name = "id") Integer id) {
        if (stockService.deleteStockById(id)) {
            return "redirect:/liststock";
        } else {
            return "error";
        }
    }


    @PostMapping("/add")
    public String modifierStockAction(Model model,
                                        @RequestParam(name = "id") Integer id,
                                        @RequestParam(name = "total") Integer total,
                                        @RequestParam(name = "description") String description) {
        Stock stock = stockService.getStockById(id);
        if (stock != null) {
            stock.setTotal(total);
            stock.setDescription(description);
            stockService.updateStock(stock);
            return "redirect:/liststock";
        } else {
            return "error";
        }
    }



    @GetMapping("/ajouterstock")
    public String ajouterstock(Model model) {
        model.addAttribute("Stock", new Stock());
        return "ajouterstock";
    }



    @PostMapping("/addOnce")
    public String ajouterstock(Model model,
                                 @Valid Stock stock,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterstock";
        }
        stockService.addStock(stock);
        return "redirect:/liststock";
    }



    @GetMapping("/liststock")
    public String listProduit(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "taille", defaultValue = "6") int taille,
                              @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Stock> stocks = stockService.searchStocks(keyword, page, taille);
        model.addAttribute("liststocks", stocks.getContent());

        int[] pages = new int[stocks.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutstock";
    }



    @GetMapping("/editstock")
    public String editstockAction(Model model, @RequestParam(name = "id") Integer id) {
        Stock stock = stockService.getStockById(id);
        if (stock != null) {
            model.addAttribute("stockToBeUpdated", stock);
            return "updatestock";
        } else {
            return "error";
        }
    }
}

