package com.appointment.appointmentmanagementproject.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "patient")
@Data
public class Patient{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long patientId;
    String patientName;
    String patientAge;
    String gender;
}
