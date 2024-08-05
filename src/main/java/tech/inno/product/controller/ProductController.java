package tech.inno.product.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.inno.product.dto.ProductResponseDto;
import tech.inno.product.dto.UserProductResponseDto;
import tech.inno.product.dto.ProductDto;
import tech.inno.product.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create/")
    public ProductResponseDto saveProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @GetMapping("/find/id/")
    public ProductResponseDto getProductById(@RequestParam(name = "id") Long id) {
        System.out.println("id = " + id);
        return productService.getProductById(id);
    }

    @GetMapping("/find/userid/")
    public UserProductResponseDto getProductByUserId(@RequestParam(name = "userid") Long userId) {
        return productService.getProductByUserId(userId);
    }
}
