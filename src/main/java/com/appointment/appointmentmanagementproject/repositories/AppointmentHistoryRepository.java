package com.appointment.appointmentmanagementproject.repositories;

import com.appointment.appointmentmanagementproject.models.AppointmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory,Long> {
}
