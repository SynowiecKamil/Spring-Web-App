package com.synowiec.LoginRegister.service;

import com.synowiec.LoginRegister.model.Fizjoterapeuta;
import com.synowiec.LoginRegister.model.Role;
import com.synowiec.LoginRegister.repository.FizjoterapeutaRepository;
import com.synowiec.LoginRegister.web.dto.FizjoRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FizjoterapeutaServiceImpl implements FizjoterapeutaService {

    @Autowired
    private FizjoterapeutaRepository fizjoRepository;

    @Autowired
    private BCryptPasswordEncoder fizjoPasswordEncoder;
    private String email;

    public FizjoterapeutaServiceImpl() {
    }

    public Fizjoterapeuta findByEmail(String email){
        return fizjoRepository.findByEmail(email);
    }

    public List<Fizjoterapeuta> listAll(){
        return fizjoRepository.findAll();
    }

    public Fizjoterapeuta saveFizjo(FizjoRegistrationDto registration){
        Fizjoterapeuta fizjo = new Fizjoterapeuta();
        fizjo.setFirstName(registration.getFirstName());
        fizjo.setLastName(registration.getLastName());
        fizjo.setEmail(registration.getEmail());
        fizjo.setPassword(fizjoPasswordEncoder.encode(registration.getPassword()));
        fizjo.setRoles(Arrays.asList(new Role("Fizjoterapeuta")));
        return fizjoRepository.save(fizjo);
    }

    public Fizjoterapeuta get(long id){
        return fizjoRepository.findById(id).get();
    }

    @Transactional
    public void updateFizjoterapeuta(Fizjoterapeuta fizjoterapeuta) {
        fizjoRepository.updateFizjo(fizjoterapeuta.getId(), fizjoterapeuta.getFirstName(), fizjoterapeuta.getLastName());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        this.email = email;
        Fizjoterapeuta fizjo = fizjoRepository.findByEmail(email);
        if (fizjo == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(fizjo.getEmail(),
                fizjo.getPassword(),
                mapRolesToAuthorities(fizjo.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
