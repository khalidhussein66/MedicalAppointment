package com.appointment.appointmentmanagementproject.controllers;



import com.appointment.appointmentmanagementproject.dtos.request.AuthRequest;
import com.appointment.appointmentmanagementproject.dtos.request.UserInfoDto;
import com.appointment.appointmentmanagementproject.services.JwtService;
import com.appointment.appointmentmanagementproject.services.UserInfoService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private UserInfoService service;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public UserController(UserInfoService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfoDto userInfoRequest) {
        return service.addUser(userInfoRequest);
    }


    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
