package com.synowiec.LoginRegister.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synowiec.LoginRegister.model.Pacjent;

@Repository
public interface PacjentRepository extends JpaRepository<Pacjent, Long> {
    Pacjent findByEmail(String email);

    @Modifying
    @Query("update Pacjent p set p.firstName = :firstName, p.lastName = :lastName where p.id = :id")
    void updatePacjent(@Param(value = "id") long id, @Param(value = "firstName") String firstName, @Param(value = "lastName")String lastName);

}
