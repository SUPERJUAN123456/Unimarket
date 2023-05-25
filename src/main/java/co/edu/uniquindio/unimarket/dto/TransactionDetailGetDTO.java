package co.edu.uniquindio.unimarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TransactionDetailGetDTO {

    private int idProduct;
    private int id;
    private float price;
    private int unities;
    private int idTransaction;

}
