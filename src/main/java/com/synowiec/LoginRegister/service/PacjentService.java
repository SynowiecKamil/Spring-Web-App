package com.synowiec.LoginRegister.service;

import com.synowiec.LoginRegister.model.Pacjent;
import com.synowiec.LoginRegister.web.dto.PacjentRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface PacjentService extends UserDetailsService {

    Pacjent findByEmail(String email);

    Pacjent save(PacjentRegistrationDto registration);

    Pacjent get(long id);

    void updatePacjent(Pacjent pacjent);
}