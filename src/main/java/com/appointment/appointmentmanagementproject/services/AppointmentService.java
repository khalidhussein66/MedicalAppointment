package com.appointment.appointmentmanagementproject.services;


import com.appointment.appointmentmanagementproject.dtos.request.AppointmentDTO;
import com.appointment.appointmentmanagementproject.dtos.response.AppointmentHistoryResponse;
import com.appointment.appointmentmanagementproject.dtos.response.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    AppointmentResponse addAppointment(AppointmentDTO appointmentDTO);

    List<AppointmentResponse> getAllTasks();

    List<AppointmentResponse> filterAppointmentByDate(String date);

    List<AppointmentHistoryResponse> previewAppiontmentsHistory();

    void cancelAppointment(Long appointmentId);

    AppointmentResponse filterAppointmentByPatientName(String date);
}
