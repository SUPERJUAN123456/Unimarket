package co.edu.uniquindio.unimarket.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TransactionDetail {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private int unities;

    @ManyToOne(fetch = FetchType.LAZY)
    private Transaction transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Override
    public String toString() {
        return "producto: " + product +
                ", unidades: " + unities +
                ", precio: " + price ;
    }
}
