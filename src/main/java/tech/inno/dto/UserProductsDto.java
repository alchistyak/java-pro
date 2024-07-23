package tech.inno.dto;

import tech.inno.data.Product;
import tech.inno.data.User;

import java.util.List;

public class UserProductsDto {
    private UserDto userDto;
    private List<ProductDto> listProductDto;

    public UserProductsDto(UserDto user, List<ProductDto> listProductDto) {
        this.userDto = user;
        this.listProductDto = listProductDto;
    }

    public UserDto getUser() {
        return userDto;
    }

    public void setUser(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<ProductDto> getProducts() {
        return listProductDto;
    }

    public void setProducts(List<ProductDto> products) {
        this.listProductDto = products;
    }
}
