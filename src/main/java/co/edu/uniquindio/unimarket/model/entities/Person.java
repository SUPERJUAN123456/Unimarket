package co.edu.uniquindio.unimarket.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person implements Serializable {

    @Id
    @Column(length = 100,nullable = false,unique = true)
    private String id;

    @Column(length = 100,nullable = false)
    private String name;

    @Column(length = 100,nullable = false,unique = true)
    private String email;

    @Column(length = 100,nullable = false)
    private String country;

    @Column(length = 100,nullable = false)
    private String city;

    @Column(length = 100,nullable = false)
    private String adress;

    @Column(length = 15,nullable = false)
    private String cellphoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Rol rol;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> favoriteProducts;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentMethod> paymentMethods;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;



}
