package com.haulmont.testtask.service;

import java.util.List;

import com.haulmont.testtask.domain.Recipe;

public interface RecipeService {

    Recipe create(Recipe recipe);

    Recipe update(Recipe recipe);

    void delete(Long recipeId);

    List<Recipe> getByDoctorId(Long doctorId);

    List<Recipe> getByPatientId(Long patientId);

    List<Recipe> getAll();
}
