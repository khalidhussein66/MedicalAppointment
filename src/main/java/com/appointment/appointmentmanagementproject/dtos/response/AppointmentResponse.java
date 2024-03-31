package com.appointment.appointmentmanagementproject.dtos.response;

import com.appointment.appointmentmanagementproject.models.Patient;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AppointmentResponse {
    long appointmentId;
    Patient patient;
    Date appointmentDate;

}
