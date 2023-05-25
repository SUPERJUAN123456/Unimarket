package co.edu.uniquindio.unimarket.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentMethod {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50,nullable = false)
    private String bankingEntity;
    @Column(length = 16,nullable = false)
    private String cardNumber;
    @Column(length = 100, nullable = false)
    private String titularName;
    @Column(nullable = false)
    private LocalDate expirationDate;
    @Column(nullable = false,length = 3)
    private int cvv;

    @Column(nullable = false,length = 3)
    private  boolean state;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person user;
    @OneToMany(mappedBy = "paymentMethod",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

}
