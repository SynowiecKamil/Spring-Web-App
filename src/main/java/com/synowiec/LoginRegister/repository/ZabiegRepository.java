package com.synowiec.LoginRegister.repository;

import com.synowiec.LoginRegister.model.Fizjoterapeuta;
import com.synowiec.LoginRegister.model.Zabieg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZabiegRepository extends JpaRepository<Zabieg, Long> {

    @Query("SELECT p.zabieg FROM Pacjent p WHERE p.email = :email")
    public List<Zabieg> findAllByOwner(@Param(value = "email") String email);

    @Query("SELECT f.zabieg FROM Fizjoterapeuta f WHERE f.email = :email")
    public List<Zabieg> findAllByOwnerFizjo(@Param(value = "email") String email);

    @Modifying
    @Query("update Zabieg z set z.name = :name where z.id = :id")
    void updateZabieg(@Param(value = "id") long id, @Param(value = "name") String name);

}
