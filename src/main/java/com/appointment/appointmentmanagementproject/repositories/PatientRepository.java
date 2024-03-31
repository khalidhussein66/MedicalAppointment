package com.appointment.appointmentmanagementproject.repositories;

import com.appointment.appointmentmanagementproject.models.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
 Patient findPatientByPatientName(String patientName);
}
