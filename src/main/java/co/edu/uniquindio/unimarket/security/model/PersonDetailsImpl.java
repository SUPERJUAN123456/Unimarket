package co.edu.uniquindio.unimarket.security.model;

import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.model.entities.Rol;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class PersonDetailsImpl implements UserDetails {

    private String username, password;
    private Collection<? extends GrantedAuthority> authorities;
    private String id;
    private String name;

    public static PersonDetailsImpl build(Person person) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (person.getRol() == Rol.USUARIO) {
            authorities.add(new SimpleGrantedAuthority("USUARIO"));
        } else if (person.getRol() == Rol.MODERADOR) {
            authorities.add(new SimpleGrantedAuthority("MODERADOR"));
        }
        return new PersonDetailsImpl(person.getEmail(), person.getPassword(), authorities, person.getId(), person.getName());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
