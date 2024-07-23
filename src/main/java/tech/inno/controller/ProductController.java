package tech.inno.controller;

import org.springframework.web.bind.annotation.*;
import tech.inno.dto.ProductDto;
import tech.inno.dto.UserProductsDto;
import tech.inno.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ProductDto save(@RequestBody ProductDto productDto) {
        System.out.println(productDto);
        return productService.addProduct(productDto);
    }

    @GetMapping("/userid/")
    public UserProductsDto getProductsByUserId(@RequestParam(name = "userid") Long userId) {
        return productService.getProductsByUserId(userId);
    }

    @GetMapping("/id/")
    public ProductDto getProductById(@RequestParam(name = "id") Long id) {
        return productService.getProductById(id);
    }
}
