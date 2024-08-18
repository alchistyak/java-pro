package tech.inno.task8.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "day_limits")
public class DayLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "unused_limit")
    private BigDecimal unusedLimit;
    @Column(name = "active")
    private Boolean active;
    @Column (name = "date_begin")
    private LocalDateTime dateBegin;
}
