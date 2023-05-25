package co.edu.uniquindio.unimarket.controllers;

import co.edu.uniquindio.unimarket.dto.MessageDTO;
import co.edu.uniquindio.unimarket.dto.TransactionDetailDTO;
import co.edu.uniquindio.unimarket.dto.TransactionDetailGetDTO;
import co.edu.uniquindio.unimarket.services.interfaces.TransactionDetailInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/detalles_transacciones")
@SuppressWarnings("unchecked")

public class TransactionDetailController {

    private final TransactionDetailInterface transactionDetailInterface;

    @GetMapping("/obtener_detalles_transaccion/{idTransaction}")
    public ResponseEntity<MessageDTO> listTransactionDetailByTransaction(@PathVariable int idTransaction){
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,transactionDetailInterface.listTransactionDetailByTransaction(idTransaction)));
    }

}
