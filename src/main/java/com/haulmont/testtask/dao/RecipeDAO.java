package com.haulmont.testtask.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.haulmont.testtask.JpaUtil;
import com.haulmont.testtask.domain.Recipe;

public class RecipeDAO {

    private final EntityManager em;

    public RecipeDAO() {
        this.em = JpaUtil.getEmFactory().createEntityManager();
    }

    public Recipe create(Recipe recipe) {
        em.getTransaction().begin();
        em.persist(recipe);
        em.getTransaction().commit();
        return recipe;
    }

    public Recipe update(Recipe recipe) {
        em.getTransaction().begin();
        em.merge(recipe);
        em.getTransaction().commit();
        return recipe;
    }

    public void delete(Recipe recipe) {
        em.getTransaction().begin();
        em.remove(em.contains(recipe) ? recipe : em.merge(recipe));
        em.getTransaction().commit();
    }

    public List<Recipe> getByDoctorId(Long doctorId) {
        return em.createQuery(String.format("SELECT r FROM Recipe r where r.doctor.id = %s", doctorId), Recipe.class).getResultList();
    }

    public List<Recipe> getByPatientId(Long patientId) {
        return em.createQuery(String.format("SELECT r FROM Recipe r where r.patient.id = %s", patientId), Recipe.class).getResultList();
    }

    public List<Recipe> getAll() {
        return em.createQuery("SELECT p FROM Recipe p", Recipe.class).getResultList();
    }
}
