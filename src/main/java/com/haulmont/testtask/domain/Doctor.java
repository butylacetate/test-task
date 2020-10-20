package com.haulmont.testtask.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String surname;
    @Column
    private String name;
    @Column(name = "second_name")
    private String secondName;
    @Column
    private String specialization;

    public Doctor() {
    }

    public Doctor(Long id) {
        this.id = id;
    }

    public Doctor(String surname, String name, String secondName, String specialization) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.specialization = specialization;
    }

    public Doctor(Long id, String surname, String name, String secondName, String specialization) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.specialization = specialization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
