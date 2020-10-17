package com.synowiec.LoginRegister.service;

import com.synowiec.LoginRegister.model.Fizjoterapeuta;
import com.synowiec.LoginRegister.model.Pacjent;
import com.synowiec.LoginRegister.model.Zabieg;
import com.synowiec.LoginRegister.repository.ZabiegRepository;
import com.synowiec.LoginRegister.web.dto.ZabiegDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class ZabiegServiceImpl implements ZabiegService{

    @Autowired
    private ZabiegRepository zabiegRepository;

    public Zabieg save(ZabiegDto rejestracja, Pacjent pacjent, Fizjoterapeuta fizjoterapeuta){
        Zabieg zabieg = new Zabieg();
        zabieg.setName(rejestracja.getName());
        zabieg.setPacjent(pacjent);
        zabieg.setFizjoterapeuta(fizjoterapeuta);
        return zabiegRepository.save(zabieg);
    }

    public List<Zabieg> listAll(String user, String email) {
        if (user == "pacjent") {
            return zabiegRepository.findAllByOwner(email);
        }
        return zabiegRepository.findAllByOwnerFizjo(email);
    }

    public Zabieg get(long id) {
        return zabiegRepository.findById(id).get();
    }

    @Transactional
    public void updateZabieg(Zabieg zabieg) {
        zabiegRepository.updateZabieg(zabieg.getId(), zabieg.getName());
    }

    @Transactional
    public void deleteZabieg(Zabieg zabieg) {
        zabiegRepository.deleteById(zabieg.getId());
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
