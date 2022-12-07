package re.gb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.gb.model.Product;
import re.gb.repository.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/all")
    public String getStudents(Model model) {
        List<Product> products = productRepository.getProducts();
        model.addAttribute("products", products);
        return "products";
    }

}
