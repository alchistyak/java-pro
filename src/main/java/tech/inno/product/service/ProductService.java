package tech.inno.product.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tech.inno.product.dto.ProductResponseDto;
import tech.inno.product.dto.UserProductResponseDto;
import tech.inno.product.dto.ProductDto;
import tech.inno.product.dto.UserResponseDto;
import tech.inno.product.entity.Product;
import tech.inno.product.entity.User;
import tech.inno.product.exception.ServiceException;
import tech.inno.product.repository.ProductRepository;
import tech.inno.product.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserService userService, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public ProductResponseDto saveProduct(ProductDto productDto) {
        User user = userService.findUserById(productDto.userid());
        Product product = new Product();
        product.setAccount(productDto.account());
        product.setBalance(productDto.balance());
        product.setType(productDto.type());
        product.setUser(user);
        product = productRepository.save(product);
        return new ProductResponseDto(product.getId(), product.getAccount(), product.getBalance(), product.getType());
    }
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Не найден продукт id=" + id));
        return new ProductResponseDto(product.getId(), product.getAccount(), product.getBalance(), product.getType());
    }

    public UserProductResponseDto getProductByUserId(Long userId) {
        User user = userService.findUserById(userId);
        List<Product> productList = productRepository.findAllByUserId(userId);
        UserResponseDto userResponseDto = new UserResponseDto(user.getId(), user.getUserName());
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (Product product : productList) {
            productResponseDtoList.add(new ProductResponseDto(product.getId(), product.getAccount(), product.getBalance(), product.getType()));
        }
        return new UserProductResponseDto(userResponseDto, productResponseDtoList);
    }
}
