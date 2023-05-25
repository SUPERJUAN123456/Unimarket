package co.edu.uniquindio.unimarket.repositories;

import co.edu.uniquindio.unimarket.model.entities.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailRepo extends JpaRepository<TransactionDetail,Integer> {

    @Query("select d from TransactionDetail d where d.transaction.id = :idTransaction")
    List<TransactionDetail> findByTransaction(int idTransaction);
}
