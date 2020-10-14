package com.synowiec.LoginRegister.repository;

import com.synowiec.LoginRegister.model.Fizjoterapeuta;
import com.synowiec.LoginRegister.model.Zabieg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZabiegRepository extends JpaRepository<Zabieg, Long> {

}
