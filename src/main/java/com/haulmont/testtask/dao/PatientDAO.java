package com.haulmont.testtask.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.haulmont.testtask.JpaUtil;
import com.haulmont.testtask.domain.Patient;

public class PatientDAO {

    private final EntityManager em;

    public PatientDAO() {
        this.em = JpaUtil.getEmFactory().createEntityManager();
    }

    public Patient create(Patient patient) {
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
        return patient;
    }

    public Patient update(Patient patient) {
        em.getTransaction().begin();
        em.merge(patient);
        em.getTransaction().commit();
        return patient;
    }

    public void delete(Patient patient) {
        em.getTransaction().begin();
        em.remove(em.contains(patient) ? patient : em.merge(patient));
        em.getTransaction().commit();
    }

    public List<Patient> getAll() {
        return em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
    }
}
