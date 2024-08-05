package tech.inno.product.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
@ToString(exclude = "user")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account")
    private String account;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;
}
