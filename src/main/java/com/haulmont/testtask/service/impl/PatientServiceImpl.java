package com.haulmont.testtask.service.impl;

import java.util.List;

import com.haulmont.testtask.dao.PatientDAO;
import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.service.PatientService;

public class PatientServiceImpl implements PatientService {

    private final PatientDAO patientDAO;

    public PatientServiceImpl() {
        this.patientDAO = new PatientDAO();
    }

    @Override
    public Patient create(Patient patient) {
        return patientDAO.create(patient);
    }

    @Override
    public Patient update(Patient patient) {
        return patientDAO.update(patient);
    }

    @Override
    public void delete(Long patientId) {
        patientDAO.delete(new Patient(patientId));
    }

    @Override
    public List<Patient> getAll() {
        return patientDAO.getAll();
    }
}
