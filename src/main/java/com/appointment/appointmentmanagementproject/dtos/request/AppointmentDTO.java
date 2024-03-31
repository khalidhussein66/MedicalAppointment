package com.appointment.appointmentmanagementproject.dtos.request;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AppointmentDTO {
   @NotNull(message = "patient name is missing ")
   String patientName;
    @NotNull(message = "age is missing ")
    String patientAge;
    @NotNull(message = "gender is missing ")
    String gender;
    @NotNull(message = "Reservation Number is missing ")
    Integer patientReservationNumber;
    String appointmentType;

}
