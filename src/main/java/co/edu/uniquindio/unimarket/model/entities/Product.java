package co.edu.uniquindio.unimarket.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 100)
    private String title;
    @Column(nullable = false)
    private StateProduct state;
    @Column(nullable = false)
    private float realPrice;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private String description;
    @Column(nullable = true,length = 5)
    private int discount;
    @Column(nullable = false)
    private int unities;
    @Column(nullable = false,length = 1)
    private float puntuation;
    @Column(nullable = false)
    private LocalDate deadline;
    @Column(nullable = false)
    private LocalDate creationDate;
    @ManyToOne
    private Category category;
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String,String> images;
    @ManyToOne
    private Person user;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    @ManyToMany(mappedBy = "favoriteProducts",cascade = CascadeType.ALL)
    private List<Person> userFavorite;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionDetail> transactionDetails;

}
