package com.geekbrains.controllers;

import com.geekbrains.entities.Product;
import com.geekbrains.entities.Products;
import com.geekbrains.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/product")
public class ProductsController {
    private ProductsService productsService;
    private ArrayList<Product> products;

    public ProductsController(){
        products = new ArrayList<Product>();
    }

    @Autowired
    public void setProductsService(ProductsService productsService){
        this.productsService = productsService;
    }

    @RequestMapping("/showProduct")
    public String showProduct(Model uiModel) {

        /*Product[] products = new Product[2];
        products [0] = productsService.getStudentById(1, "milk", 35.60);
        products [1] = productsService.getStudentById(2, "cheese", 50);*/


        /*products.add(productsService.getStudentById(1, "milk", 35.60));
        products.add(productsService.getStudentById(2, "cheese", 50));*/
        Products productsList = new Products();

        Product[] resultProduct = new Product[products.size()];
        for (int i = 0; i < products.size(); i++) {
            resultProduct[i] = products.get(i);
        }

        productsList.setProducts(resultProduct);
        uiModel.addAttribute("products",  productsList);

        return "products";
    }
   @RequestMapping("/showForm")
    public String showSimpleForm(Model uiModel) {
        Product product = new Product();
        uiModel.addAttribute("product", product);
        return "product-form";
    }


    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("product") Product product) {
        products.add(product);
        return "product-form-result";
    }

}
