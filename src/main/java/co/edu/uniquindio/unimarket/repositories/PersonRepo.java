package co.edu.uniquindio.unimarket.repositories;

import co.edu.uniquindio.unimarket.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, String> {

    @Query("select p from Person p where p.email = :email")
    Person findPersonByEmail(String email);

    @Query("select p from Person p where p.id = :id")
    Person findPersonById(String id);

}
