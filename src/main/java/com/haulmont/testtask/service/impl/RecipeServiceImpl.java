package com.haulmont.testtask.service.impl;

import java.util.List;

import com.haulmont.testtask.dao.RecipeDAO;
import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.service.RecipeService;

public class RecipeServiceImpl implements RecipeService {

    private final RecipeDAO recipeDAO;

    public RecipeServiceImpl() {
        this.recipeDAO = new RecipeDAO();
    }

    @Override
    public Recipe create(Recipe recipe) {
        return recipeDAO.create(recipe);
    }

    @Override
    public Recipe update(Recipe recipe) {
        return recipeDAO.update(recipe);
    }

    @Override
    public void delete(Long recipeId) {
        recipeDAO.delete(new Recipe(recipeId));
    }

    @Override
    public List<Recipe> getByDoctorId(Long doctorId) {
        return recipeDAO.getByDoctorId(doctorId);
    }

    @Override
    public List<Recipe> getByPatientId(Long patientId) {
        return recipeDAO.getByPatientId(patientId);
    }

    @Override
    public List<Recipe> getAll() {
        return recipeDAO.getAll();
    }
}
