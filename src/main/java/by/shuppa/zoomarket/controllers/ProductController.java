package by.shuppa.zoomarket.controllers;

import by.shuppa.zoomarket.models.Product;
import by.shuppa.zoomarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/")
    public String mainPage(Model model) {
        if (!productService.listProducts().isEmpty()) {
            model.addAttribute("products", productService.listProducts());
        }
        return "main-page";
    }

    @GetMapping("/add")
    public String showAddPage() {
        return "addPage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }
}
