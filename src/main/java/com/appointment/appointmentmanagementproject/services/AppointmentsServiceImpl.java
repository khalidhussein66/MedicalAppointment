package com.appointment.appointmentmanagementproject.services;

import com.appointment.appointmentmanagementproject.Utils.CancellationReason;
import com.appointment.appointmentmanagementproject.dtos.request.AppointmentDTO;
import com.appointment.appointmentmanagementproject.dtos.response.AppointmentHistoryResponse;
import com.appointment.appointmentmanagementproject.dtos.response.AppointmentResponse;
import com.appointment.appointmentmanagementproject.models.Appointment;
import com.appointment.appointmentmanagementproject.models.AppointmentHistory;
import com.appointment.appointmentmanagementproject.models.Patient;
import com.appointment.appointmentmanagementproject.repositories.AppointmentHistoryRepository;
import com.appointment.appointmentmanagementproject.repositories.AppointmentRepository;
import com.appointment.appointmentmanagementproject.repositories.PatientRepository;
import com.appointment.appointmentmanagementproject.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final AppointmentHistoryRepository appointmentHistoryRepository;
    @Autowired
    public AppointmentsServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository, AppointmentHistoryRepository appointmentHistoryRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.appointmentHistoryRepository = appointmentHistoryRepository;
    }

    @Override
    public AppointmentResponse addAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        Patient patient = new Patient();
        patient.setPatientName(appointmentDTO.getPatientName());
        patient.setPatientAge(appointmentDTO.getPatientAge());
        patient.setGender(appointmentDTO.getGender());
        appointment.setAppointmentType(appointmentDTO.getAppointmentType());
        appointment.setPatientReservationNumber(appointmentDTO.getPatientReservationNumber());
        appointment.setAppointmentDate(new Date());
        appointment.setPatient(patient);
        Appointment savedAppointment= appointmentRepository.save(appointment);
        AppointmentHistory appointmentHistory = new AppointmentHistory();
        appointmentHistory.setAppointmentId(savedAppointment.getId());
        appointmentHistory.setPatientId(savedAppointment.getPatient().getPatientId());
        appointmentHistory.setAppointmentStatus("confirmed");
        appointmentHistoryRepository.save(appointmentHistory);
        return getAppointmentResponse(savedAppointment);
    }

    @Override
    public List<AppointmentResponse> getAllTasks() {
   List<Appointment> appointmentList =  appointmentRepository.findAll();
     List<AppointmentResponse> appointmentResponses = new ArrayList<>();
     for (Appointment appointment : appointmentList){
         AppointmentResponse appointmentResponse = getAppointmentResponse(appointment);
         appointmentResponses.add(appointmentResponse);
     }
     return appointmentResponses;
    }

    @Override
    public List<AppointmentResponse> filterAppointmentByDate(String date) {
        Date appointmentDate =new Date(date);
        List<Appointment> appointmentList =  appointmentRepository.findAllByAppointmentDate(appointmentDate);
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        for (Appointment appointment : appointmentList){
            AppointmentResponse appointmentResponse = getAppointmentResponse(appointment);
            appointmentResponses.add(appointmentResponse);
        }
        return appointmentResponses;

    }

    @Override
    public List<AppointmentHistoryResponse> previewAppiontmentsHistory() {
       List<AppointmentHistory> appointmentHistoryList = appointmentHistoryRepository.findAll();
        List<AppointmentHistoryResponse> appointmentHistoryResponseList = new ArrayList<>();
         for (AppointmentHistory appointmentHistory : appointmentHistoryList){
             AppointmentHistoryResponse appointmentHistoryResponse = new AppointmentHistoryResponse();
             appointmentHistoryResponse.setId(appointmentHistory.getId());
             appointmentHistoryResponse.setAppointmentId(appointmentHistory.getAppointmentId());
             appointmentHistoryResponse.setPatientId(appointmentHistory.getPatientId());
             appointmentHistoryResponse.setAppointmentStatus(appointmentHistory.getAppointmentStatus());
           appointmentHistoryResponseList.add(appointmentHistoryResponse);
         }

        return appointmentHistoryResponseList;
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
        Optional<Appointment> appointment= Optional.ofNullable(appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("appointment not found")));
       appointmentRepository.deleteById(appointment.get().getId());
       AppointmentHistory appointmentHistory = new AppointmentHistory();
       appointmentHistory.setAppointmentStatus("canceled");
       appointmentHistory.setAppointmentStatus(String.valueOf(CancellationReason.BASED_ON_PATIENT_REQUEST));
       appointmentHistory.setAppointmentId(appointment.get().getId());
       appointmentHistory.setPatientId(appointment.get().getPatient().getPatientId());
       appointmentHistoryRepository.save(appointmentHistory);
    }

    @Override
    public AppointmentResponse filterAppointmentByPatientName(String patientName) {
     Patient patient = patientRepository.findPatientByPatientName(patientName);
     Appointment appointment = appointmentRepository.findAppointmentByPatient(patient);
     return getAppointmentResponse(appointment);
    }

    private AppointmentResponse getAppointmentResponse(Appointment savedAppointment) {
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setAppointmentId(savedAppointment.getId());
        appointmentResponse.setPatient(savedAppointment.getPatient());
        appointmentResponse.setAppointmentDate(savedAppointment.getAppointmentDate());
        return appointmentResponse;
    }
}
