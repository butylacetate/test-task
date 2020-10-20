package com.haulmont.testtask.dto;

import com.haulmont.testtask.domain.Doctor;

public class DoctorStatsDTO {

    private Long id;
    private String surname;
    private String name;
    private String secondName;
    private Long recipeCount;

    public DoctorStatsDTO(Long id, String surname, String name, String secondName, Long recipeCount) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.recipeCount = recipeCount;
    }

    public DoctorStatsDTO(Doctor doctor, Long recipeCount) {
        this.id = doctor.getId();
        this.surname = doctor.getSurname();
        this.name = doctor.getName();
        this.secondName = doctor.getSecondName();
        this.recipeCount = recipeCount;
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

    public Long getRecipeCount() {
        return recipeCount;
    }

    public void setRecipeCount(Long recipeCount) {
        this.recipeCount = recipeCount;
    }
}
