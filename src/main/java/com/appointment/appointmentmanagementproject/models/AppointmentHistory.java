package com.appointment.appointmentmanagementproject.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "appointments_history")
@Data
public class AppointmentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long appointmentId;
    Long patientId;
    String appointmentStatus;
    String cancellationReason;
}
