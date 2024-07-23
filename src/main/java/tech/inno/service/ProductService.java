package tech.inno.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tech.inno.dao.ProductRepository;
import tech.inno.dao.UserRepository;
import tech.inno.dto.ProductDto;
import tech.inno.data.Product;
import tech.inno.data.User;
import tech.inno.dto.UserDto;
import tech.inno.dto.UserProductsDto;
import tech.inno.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;
    UserRepository userRepository;

    public ProductService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public ProductDto addProduct(ProductDto productDto) {
        Optional<User> user = null;
        try {
            user = userRepository.getById(productDto.getUserid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!user.isPresent())
            throw new ServiceException("User id=" + productDto.getUserid() + " not found", HttpStatus.NOT_FOUND);

        Product product = new Product(user.get(), null, productDto.getAccount(), productDto.getBalance(), productDto.getType());

        try {
            Long result = productRepository.save(product);
            productDto.setId(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productDto;
    }

    public UserProductsDto getProductsByUserId(Long userId) {
        Optional<User> user = null;
        List<Product> products = new ArrayList<>();
        List<ProductDto> productDtoList = new ArrayList<>();
        try {
            user = userRepository.getById(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!user.isPresent())
            throw new ServiceException("User id=" + userId + " not found", HttpStatus.NOT_FOUND);

        try {
            products = productRepository.getProductsByUserId(user.get());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Product product : products) {
            productDtoList.add(new ProductDto(product.getId(), product.getAccount(), product.getBalance(), product.getType(), product.getUser().getId()));
        }

        return new UserProductsDto(
                new UserDto(user.get().getId(), user.get().getUsername()),
                productDtoList);
    }

    public ProductDto getProductById(Long id) {
        Product product = null;
        try {
            product = productRepository.getProductById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (product == null)
            throw new ServiceException("Product id=" + id + " not found", HttpStatus.NOT_FOUND);

        return new ProductDto(product.getId(), product.getAccount(), product.getBalance(), product.getType(), product.getUser().getId());
    }
}
