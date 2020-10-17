package com.synowiec.LoginRegister.service;

import com.synowiec.LoginRegister.model.Fizjoterapeuta;
import com.synowiec.LoginRegister.model.Pacjent;
import com.synowiec.LoginRegister.model.Zabieg;
import com.synowiec.LoginRegister.web.dto.ZabiegDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ZabiegService extends UserDetailsService {

    Zabieg save(ZabiegDto rejestracja, Pacjent pacjent, Fizjoterapeuta fizjoterapeuta);

    List<Zabieg> listAll(String user, String email);

    Zabieg get(long id);

    void updateZabieg(Zabieg zabieg);

    void deleteZabieg(Zabieg zabieg);
}
