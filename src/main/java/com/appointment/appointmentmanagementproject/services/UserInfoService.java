package com.appointment.appointmentmanagementproject.services;

import com.appointment.appointmentmanagementproject.Utils.UserInfoDetails;
import com.appointment.appointmentmanagementproject.dtos.request.UserInfoDto;
import com.appointment.appointmentmanagementproject.models.UserInfo;
import com.appointment.appointmentmanagementproject.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = userInfoRepository.findByName(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserInfoDto userInfoDto) {
        userInfoDto.setPassword(encoder.encode(userInfoDto.getPassword()));
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userInfoDto.getName());
        userInfo.setRole(userInfoDto.getRole());
        userInfo.setEmail(userInfoDto.getEmail());
        userInfoRepository.save(userInfo);
        return "User Added Successfully";
    }


}
