package com.synowiec.LoginRegister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.synowiec.LoginRegister.model.Fizjoterapeuta;

@Repository
public interface FizjoterapeutaRepository extends JpaRepository<Fizjoterapeuta, Long> {
        Fizjoterapeuta findByEmail(String email);

        }