package com.appointment.appointmentmanagementproject.models;

import javax.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "appointments")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @OneToOne
    Patient patient;
    Integer patientReservationNumber;
    String appointmentType;
    Date appointmentDate;
}
