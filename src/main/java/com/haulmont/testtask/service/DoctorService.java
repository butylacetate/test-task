package com.haulmont.testtask.service;

import java.util.List;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.dto.DoctorStatsDTO;

public interface DoctorService {

    Doctor create(Doctor doctor);

    Doctor update(Doctor doctor);

    void delete(Long doctorId);

    List<Doctor> getAll();

    List<DoctorStatsDTO> getAllWithStats();
}
