package com.haulmont.testtask.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.haulmont.testtask.JpaUtil;
import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.dto.DoctorStatsDTO;

public class DoctorDAO {

    private final EntityManager em;

    public DoctorDAO() {
        this.em = JpaUtil.getEmFactory().createEntityManager();
    }

    public Doctor create(Doctor doctor) {
        em.getTransaction().begin();
        em.persist(doctor);
        em.getTransaction().commit();
        return doctor;
    }

    public Doctor update(Doctor doctor) {
        em.getTransaction().begin();
        em.merge(doctor);
        em.getTransaction().commit();
        return doctor;
    }

    public void delete(Doctor doctor) {
        em.getTransaction().begin();
        em.remove(em.contains(doctor) ? doctor : em.merge(doctor));
        em.getTransaction().commit();
    }

    public List<Doctor> getAll() {
        return em.createQuery("SELECT p FROM Doctor p", Doctor.class).getResultList();
    }

    public List<DoctorStatsDTO> getAllWithStats() {
        return (List<DoctorStatsDTO>) em.createQuery("SELECT p, count(r.id) FROM Doctor p left join Recipe r on r.doctor.id = p.id group by p.id")
                .getResultStream().map(o -> new DoctorStatsDTO((Doctor) ((Object[]) o)[0], (Long) ((Object[]) o)[1])).collect(Collectors.toList());
    }
}
