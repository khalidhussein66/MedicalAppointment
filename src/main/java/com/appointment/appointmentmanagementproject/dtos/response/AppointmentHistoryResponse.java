package com.appointment.appointmentmanagementproject.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AppointmentHistoryResponse {
    Long id;
    Long appointmentId;
    Long patientId;
    String appointmentStatus;
    String cancellationReason;
}
