package com.appointment.appointmentmanagementproject.controllers;



import com.appointment.appointmentmanagementproject.dtos.request.AppointmentDTO;
import com.appointment.appointmentmanagementproject.dtos.response.AppointmentHistoryResponse;
import com.appointment.appointmentmanagementproject.dtos.response.AppointmentResponse;
import com.appointment.appointmentmanagementproject.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping(path = "/create/appointment")
    AppointmentResponse createNewAppointment(@RequestBody @Valid AppointmentDTO appointmentDTO) {
        return appointmentService.addAppointment(appointmentDTO);
    }

    @GetMapping(path = "/appointments")
    List<AppointmentResponse> getAllAppointments(){
        return appointmentService.getAllTasks();
    }


    @GetMapping(path = "/appointments/date")
    List<AppointmentResponse> getAppointmentsByDate(@RequestParam("date") String date)
    {
     return appointmentService.filterAppointmentByDate(date);
    }
    @GetMapping(path = "/appointment/patientName")
    AppointmentResponse getAppointmentsByPatientName(@RequestParam("patientName") String patientName)
    {
        return appointmentService.filterAppointmentByPatientName(patientName);
    }

    @GetMapping(path = "/appointments/history")
    List<AppointmentHistoryResponse> previewAppointmentsHistory()
    {
        return appointmentService.previewAppiontmentsHistory();
    }
  @DeleteMapping(path = "/cancel/appointment/{appointmentId}")
    void cancelAppointment(@PathVariable("appointmentId") Long appointmentId){
        appointmentService.cancelAppointment(appointmentId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

