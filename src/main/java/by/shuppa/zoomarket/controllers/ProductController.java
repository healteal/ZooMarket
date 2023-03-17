package by.shuppa.zoomarket.controllers;

import by.shuppa.zoomarket.models.Product;
import by.shuppa.zoomarket.repositories.ProductRepository;
import by.shuppa.zoomarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("products", productService.listProducts()
                .stream()
                .limit(9)
                .toList());
        return "main-page";
    }

    @GetMapping("/add")
    public String showAddPage() {
        return "addPage";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam(name = "first_image") MultipartFile firstImage,
                             @RequestParam(name = "second_image") MultipartFile secondImage,
                             @RequestParam(name = "third_image") MultipartFile thirdImage,
                             Product product) {
        productService.addProduct(product, firstImage, secondImage, thirdImage);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElseThrow());
        model.addAttribute("productImages", productRepository.findById(id).orElseThrow().getProductImages());
        return "product-page";
    }
}
