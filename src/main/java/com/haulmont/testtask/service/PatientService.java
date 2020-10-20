package com.haulmont.testtask.service;

import java.util.List;

import com.haulmont.testtask.domain.Patient;

public interface PatientService {

    Patient create(Patient patient);

    Patient update(Patient patient);

    void delete(Long patientId);

    List<Patient> getAll();
}
