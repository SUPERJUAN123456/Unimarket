package co.edu.uniquindio.unimarket.controllers;

import co.edu.uniquindio.unimarket.dto.MessageDTO;
import co.edu.uniquindio.unimarket.dto.TransactionDTO;
import co.edu.uniquindio.unimarket.dto.TransactionGetDTO;
import co.edu.uniquindio.unimarket.services.interfaces.TransactionInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transacciones")
public class TransactionController {

    private final TransactionInterface transactionInterface;

    @PostMapping("/crear")
    public ResponseEntity<MessageDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception{
        transactionInterface.createTransaction(transactionDTO);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.CREATED, false,"Transaccion creada correctamente"));

    }

    @GetMapping("/obtener_transaction_person/{idPerson}")
    public ResponseEntity<MessageDTO> listTransactionByPerson(@PathVariable String idPerson){
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,transactionInterface.listTransactionByPerson(idPerson)));
    }

    @GetMapping("/obtener/{idTransaction}")
    public ResponseEntity<MessageDTO> getTransactionDTO(@PathVariable int idTransaction) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,transactionInterface.getTransactionDTO(idTransaction)));
    }

}
