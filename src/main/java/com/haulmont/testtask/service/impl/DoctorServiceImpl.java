package com.haulmont.testtask.service.impl;

import java.util.List;

import com.haulmont.testtask.dao.DoctorDAO;
import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.dto.DoctorStatsDTO;
import com.haulmont.testtask.service.DoctorService;

public class DoctorServiceImpl implements DoctorService {

    private final DoctorDAO doctorDAO;

    public DoctorServiceImpl() {
        this.doctorDAO = new DoctorDAO();
    }

    @Override
    public Doctor create(Doctor doctor) {
        return doctorDAO.create(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorDAO.update(doctor);
    }

    @Override
    public void delete(Long doctorId) {
        doctorDAO.delete(new Doctor(doctorId));
    }

    @Override
    public List<Doctor> getAll() {
        return doctorDAO.getAll();
    }

    @Override
    public List<DoctorStatsDTO> getAllWithStats() {
        return doctorDAO.getAllWithStats();
    }
}
