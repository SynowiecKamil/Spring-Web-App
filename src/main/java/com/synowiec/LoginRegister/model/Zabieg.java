package com.synowiec.LoginRegister.model;

import org.springframework.data.web.JsonPath;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Zabieg {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    public Zabieg() {
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name="pacjent_id", nullable = false)
    private Pacjent pacjent;

    @ManyToOne
    @NotNull
    @JoinColumn(name="fizjoterapeuta_id", nullable = false)
    private Fizjoterapeuta fizjoterapeuta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public Fizjoterapeuta getFizjoterapeuta() {
        return fizjoterapeuta;
    }

    public void setFizjoterapeuta(Fizjoterapeuta fizjoterapeuta) {
        this.fizjoterapeuta = fizjoterapeuta;
    }

    @Override
    public String toString() {
        return "Zabieg{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pacjent=" + pacjent +
                ", fizjoterapeuta=" + fizjoterapeuta +
                '}';
    }
}
