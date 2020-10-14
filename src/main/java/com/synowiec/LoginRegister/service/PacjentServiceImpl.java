package com.synowiec.LoginRegister.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.synowiec.LoginRegister.model.Role;
import com.synowiec.LoginRegister.model.Pacjent;
import com.synowiec.LoginRegister.repository.PacjentRepository;


import com.synowiec.LoginRegister.web.dto.PacjentRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PacjentServiceImpl implements PacjentService {

    @Autowired
    private PacjentRepository pacjentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private String email;

    public Pacjent findByEmail(String email){
        return pacjentRepository.findByEmail(email);
    }

    public Pacjent save(PacjentRegistrationDto registration){
        Pacjent pacjent = new Pacjent();
        pacjent.setFirstName(registration.getFirstName());
        pacjent.setLastName(registration.getLastName());
        pacjent.setEmail(registration.getEmail());
        pacjent.setPassword(passwordEncoder.encode(registration.getPassword()));
        pacjent.setRoles(Arrays.asList(new Role("Pacjent")));
        return pacjentRepository.save(pacjent);
    }

    public Pacjent get(long id){
        return pacjentRepository.findById(id).get();
    }
    
    @Transactional
    public void updatePacjent(Pacjent pacjent) {
        pacjentRepository.updatePacjent(pacjent.getId(), pacjent.getFirstName(), pacjent.getLastName());
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        this.email = email;
        Pacjent pacjent = pacjentRepository.findByEmail(email);
        if (pacjent == null){
            throw new UsernameNotFoundException("Invalid pacjentname or password.");
        }
        return new org.springframework.security.core.userdetails.User(pacjent.getEmail(),
                pacjent.getPassword(),
                mapRolesToAuthorities(pacjent.getRoles()));
    }

    @Transactional
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
