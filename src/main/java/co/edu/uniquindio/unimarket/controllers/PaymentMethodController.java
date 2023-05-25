package co.edu.uniquindio.unimarket.controllers;

import co.edu.uniquindio.unimarket.dto.MessageDTO;
import co.edu.uniquindio.unimarket.dto.PaymentMethodDTO;
import co.edu.uniquindio.unimarket.dto.PaymentMethodGetDTO;
import co.edu.uniquindio.unimarket.services.interfaces.PaymentMethodInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/metodos_de_pago")
public class PaymentMethodController {

    private final PaymentMethodInterface paymentMethodInterface;

    @PostMapping("/agregar")
    public ResponseEntity<MessageDTO> createPaymentMethod(@RequestBody PaymentMethodDTO paymentMethodDTO) throws Exception{
        paymentMethodInterface.createPaymentMethod(paymentMethodDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,false, "Metodo de pago agregado correctamente"));
    }

    @PutMapping("/actualizar/{paymentMethodId}")
    public ResponseEntity<MessageDTO> updatePaymentMethod(@PathVariable int paymentMethodId, @RequestBody PaymentMethodGetDTO paymentMethodGetDTO) throws Exception{
        paymentMethodInterface.updatePaymentMethod(paymentMethodId, paymentMethodGetDTO);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false, "Metodo de pago actualizado correctamente"));
    }

    @PutMapping("/eliminar/{paymentMethodId}")
    public ResponseEntity<MessageDTO> deletePaymentMethod(@PathVariable int paymentMethodId) throws Exception{
        paymentMethodInterface.deletePaymentMethod(paymentMethodId);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,"Metodo de pago eliminado correctamente"));
    }

    @GetMapping("/obtener_metodos_de_pago_person/{idPerson}")
    public ResponseEntity<MessageDTO> listPaymentMethodByPerson(@PathVariable String idPerson){
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,paymentMethodInterface.listPaymentMethodByPerson(idPerson)));
    }

}
