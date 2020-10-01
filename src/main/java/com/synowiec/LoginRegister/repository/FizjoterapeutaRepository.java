package com.synowiec.LoginRegister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.synowiec.LoginRegister.model.Fizjoterapeuta;

@Repository
public interface FizjoterapeutaRepository extends JpaRepository<Fizjoterapeuta, Long> {
        Fizjoterapeuta findByEmail(String email);

        @Modifying
        @Query("update Fizjoterapeuta f set f.firstName = :firstName, f.lastName = :lastName where f.id = :id")
        void updateFizjo(@Param(value = "id") long id, @Param(value = "firstName") String firstName, @Param(value = "lastName")String lastName);

}