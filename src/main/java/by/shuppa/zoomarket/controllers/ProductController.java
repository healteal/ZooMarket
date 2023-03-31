package by.shuppa.zoomarket.controllers;

import by.shuppa.zoomarket.models.Product;
import by.shuppa.zoomarket.repositories.ProductRepository;
import by.shuppa.zoomarket.services.ProductService;
import by.shuppa.zoomarket.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserService userService;
    @GetMapping("/")
    public String mainPage(Model model,
                           Principal principal,
                           @RequestParam(name = "previousPage", required = false) Long previousPage,
                           @RequestParam(name = "nextPage", required = false) Long nextPage) {
        long page = 1L;
        if (previousPage != null) {
            page = previousPage;
        } else if (nextPage != null) {
            page = nextPage;
        }
        model.addAttribute("pageNumber", page);
        if (page > 1) {
            model.addAttribute("previousPage", Boolean.TRUE);
        }
        if (!(page * 9 >= productService.listProducts()
                .stream()
                .filter(product -> product.getMarketUser().isEnable())
                .count())) {
            model.addAttribute("nextPage", Boolean.TRUE);
        }
        if (productService.getUserByPrincipal(principal).getUsername() != null) {
            model.addAttribute("marketUser", productService.getUserByPrincipal(principal));
            model.addAttribute("userRole", productService.getUserByPrincipal(principal)
                    .getUserRoles()
                    .stream()
                    .findFirst()
                    .orElseThrow());
        }
        if (productService.listProducts().stream().anyMatch(product -> product.getMarketUser().isEnable())) {
            model.addAttribute("products", productService.listProducts()
                    .stream().filter(product -> product.getMarketUser().isEnable())
                    .skip(9 * (page - 1))
                    .limit(9)
                    .toList());
        } else {
            model.addAttribute("message", "Ничего не найдено :(");
        }
        return "main-page";
    }

    @GetMapping("/add")
    public String showAddPage(Model model,
                              Principal principal) {
        if (productService.getUserByPrincipal(principal).getUsername() != null) {
            model.addAttribute("marketUser", productService.getUserByPrincipal(principal));
            model.addAttribute("userRole", userService.getMarketUserRole(principal));
        }
        return "add-product";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam(name = "first_image") MultipartFile firstImage,
                             @RequestParam(name = "second_image") MultipartFile secondImage,
                             @RequestParam(name = "third_image") MultipartFile thirdImage,
                             @RequestParam(name = "type_of_product") String typeOfProduct,
                             Product product,
                             Principal principal) {
        productService.addProduct(principal, product, firstImage, secondImage, thirdImage, typeOfProduct);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable Long id,
                              Model model,
                              Principal principal) {
        if (productService.getUserByPrincipal(principal).getUsername() != null) {
            if (productService.getUserByPrincipal(principal).getProducts().contains(productRepository.findById(id).orElseThrow())) {
                model.addAttribute("productOwner", Boolean.TRUE);
            }
            model.addAttribute("marketUser", productService.getUserByPrincipal(principal));
            model.addAttribute("userRole", userService.getMarketUserRole(principal));
        }
        model.addAttribute("product", productRepository.findById(id).orElseThrow());
        model.addAttribute("productImages", productRepository.findById(id).orElseThrow().getProductImages());
        return "product-page";
    }

    @PostMapping("/edit/product/{id}")
    public String editProduct(@PathVariable Long id,
                              @RequestParam(name = "description", required = false) String description,
                              @RequestParam(name = "price", required = false) Double price) {
        Product temporary = productRepository.findById(id).orElseThrow();
        if (description.length() != 0) {
            temporary.setDescription(description);
            if (temporary.getDescription().length() > 21) {
                temporary.setShortDescription(temporary.getDescription().substring(0, 20) + "...");
            } else {
                temporary.setShortDescription(temporary.getDescription());
            }
        }
        if (price != null) {
            temporary.setPrice(price);
        }
        productRepository.save(temporary);
        return "redirect:/product/{id}";
    }

    @GetMapping("/about")
    public String aboutPage(Model model,
                            Principal principal) {
        if (productService.getUserByPrincipal(principal).getUsername() != null) {
            model.addAttribute("marketUser", productService.getUserByPrincipal(principal));
            model.addAttribute("userRole", userService.getMarketUserRole(principal));
        }
        return "about-page";
    }

    @GetMapping("/search")
    public String searchPage(Model model,
                             Principal principal,
                             @RequestParam(name = "search") String search) {
        if (productService.getUserByPrincipal(principal).getUsername() != null) {
            model.addAttribute("marketUser", productService.getUserByPrincipal(principal));
            model.addAttribute("userRole", userService.getMarketUserRole(principal));
        }
        model.addAttribute("products", productService.listProducts()
                .stream()
                .filter(product -> product.getDescription().toLowerCase().contains(search.toLowerCase())
                        || product.getName().toLowerCase().contains(search.toLowerCase()))
                .toList());
        model.addAttribute("searchedWord", search);
        return "search";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/";
    }
}
