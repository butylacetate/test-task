package com.haulmont.testtask.dto;

import java.io.Serializable;

import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.domain.RecipePriority;

public class RecipeDTO implements Serializable {

    private Long id;
    private String description;
    private String createDate;
    private Integer validity;
    private RecipePriority priority;
    private String patient;
    private String doctor;

    public RecipeDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.description = recipe.getDescription();
        this.createDate = recipe.getCreateDate();
        this.validity = recipe.getValidity();
        this.priority = recipe.getPriority();
        this.patient = recipe.getPatient().getSurname();
        this.doctor = recipe.getDoctor().getSurname();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public RecipePriority getPriority() {
        return priority;
    }

    public void setPriority(RecipePriority priority) {
        this.priority = priority;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
