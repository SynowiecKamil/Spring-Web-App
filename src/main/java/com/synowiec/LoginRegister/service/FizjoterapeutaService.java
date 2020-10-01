package com.synowiec.LoginRegister.service;

import com.synowiec.LoginRegister.constraint.FieldMatch;
import com.synowiec.LoginRegister.model.Fizjoterapeuta;
import com.synowiec.LoginRegister.web.dto.FizjoRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface FizjoterapeutaService extends UserDetailsService {

    Fizjoterapeuta findByEmail(String email);

    Fizjoterapeuta saveFizjo(FizjoRegistrationDto registration);

    Fizjoterapeuta get(long id);

    void updateFizjoterapeuta(Fizjoterapeuta fizjoterapeuta);

    List<Fizjoterapeuta> listAll();
}
