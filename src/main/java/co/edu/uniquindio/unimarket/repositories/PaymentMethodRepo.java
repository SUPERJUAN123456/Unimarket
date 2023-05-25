package co.edu.uniquindio.unimarket.repositories;

import co.edu.uniquindio.unimarket.model.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Integer> {

    @Query("select p from PaymentMethod p where p.cardNumber = :cardNumber")
    Optional<PaymentMethod> findByCardNumber(String cardNumber);

    @Query("select p from PaymentMethod p where p.user.id = :idPerson and p.state = true")
    List<PaymentMethod> findByPerson(String idPerson);
}
