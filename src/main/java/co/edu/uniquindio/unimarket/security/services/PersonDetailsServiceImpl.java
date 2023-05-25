package co.edu.uniquindio.unimarket.security.services;

import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.repositories.PersonRepo;
import co.edu.uniquindio.unimarket.security.model.PersonDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PersonDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepo personRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Person person = personRepo.findPersonByEmail(email);
        if(person == null) {
            try {
                throw new Exception("La persona no existe");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            return PersonDetailsImpl.build(person);
        }
    }
}
