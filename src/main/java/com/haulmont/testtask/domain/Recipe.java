package com.haulmont.testtask.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String description;
    @Column(name = "created_date")
    private String createDate;
    @Column
    private Integer validity;
    @Column
    private RecipePriority priority;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    }

    public Recipe() {
    }

    public Recipe(Long id, String description, Integer validity, RecipePriority priority, Patient patient, Doctor doctor) {
        this.id = id;
        this.description = description;
        this.validity = validity;
        this.priority = priority;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Recipe(String description, Integer validity, RecipePriority priority, Patient patient, Doctor doctor) {
        this.description = description;
        this.validity = validity;
        this.priority = priority;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Recipe(Long id) {
        this.id = id;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
