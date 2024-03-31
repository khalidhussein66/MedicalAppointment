package com.appointment.appointmentmanagementproject.repositories;

import com.appointment.appointmentmanagementproject.dtos.response.AppointmentResponse;
import com.appointment.appointmentmanagementproject.models.Appointment;
import com.appointment.appointmentmanagementproject.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
   @Query("select a from Appointment a where a.appointmentDate=:date")
    List<Appointment> findAllByAppointmentDate(@Param("date") Date date);

    Appointment findAppointmentByPatient(Patient patient);
}
