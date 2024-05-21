package com.packtpub.onlineauction.controller;

import com.packtpub.onlineauction.entity.Product;
import com.packtpub.onlineauction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-add";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes) {
        try {
            productService.saveProduct(product, file);
            redirectAttributes.addFlashAttribute("message", "Product saved successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Failed to save product");
        }
        return "redirect:/products";
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list";
    }
}