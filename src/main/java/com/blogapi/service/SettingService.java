package com.blogapi.service;

import com.blogapi.dao.UserDAO;
import com.blogapi.dto.UserDTO;
import com.blogapi.entity.CustomUserDetailsImpl;
import com.blogapi.entity.User;
import com.blogapi.response.SettingUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SettingService {


    private final UserDAO userDAO;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SettingService(UserDAO userDAO, JwtService jwtService, BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userDAO = userDAO;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public ResponseEntity<SettingUpdateResponse> updateUsername(UserDTO userDTO) {
        CustomUserDetailsImpl userDetails = getCustomUserDetails();

        userDAO.updateUsername(userDetails.getId(), userDTO.getUsername());

//        update userDetails in spring context
        CustomUserDetailsImpl updatedUserDetails = (CustomUserDetailsImpl) userDetailsService.loadUserByUsername(userDTO.getUsername());

        return getSettingUpdateResponse(userDTO, updatedUserDetails);
    }


    public ResponseEntity<SettingUpdateResponse> updateEmail(UserDTO userDTO) {
        CustomUserDetailsImpl userDetails = getCustomUserDetails();

        userDAO.updateEmail(userDetails.getId(), userDTO.getEmail());

        return getSettingUpdateResponse(userDTO, userDetails);
    }

    public ResponseEntity<SettingUpdateResponse> updatePassword(UserDTO userDTO) {
        CustomUserDetailsImpl userDetails = getCustomUserDetails();

        userDAO.updateUserPassword(userDetails.getId(), passwordEncoder.encode(userDTO.getPassword()));

//        update userDetails in spring context
        CustomUserDetailsImpl updatedUserDetails = (CustomUserDetailsImpl) userDetailsService.loadUserByUsername(userDTO.getUsername());

        return getSettingUpdateResponse(userDTO, updatedUserDetails);
    }


    public ResponseEntity<SettingUpdateResponse> updateAvatarUrl(UserDTO userDTO) {
        CustomUserDetailsImpl userDetails = getCustomUserDetails();

        userDAO.updateUserAvatarUrl(userDetails.getId(), userDTO.getAvatarUrl());

        return getSettingUpdateResponse(userDTO, userDetails);
    }

    public ResponseEntity<SettingUpdateResponse> updateRole(UserDTO userDTO) {
//        CustomUserDetailsImpl userDetails = getCustomUserDetails();

//        TODO check for the author key before changing the role
//        userDAO.updateUserRole(userDetails.getId(), userDTO.getRole());

        return null;
    }


    private CustomUserDetailsImpl getCustomUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
        return userDetails;
    }

    private ResponseEntity<SettingUpdateResponse> getSettingUpdateResponse(UserDTO userDTO, CustomUserDetailsImpl userDetails) {
        String jwtToken = jwtService.generateToken(userDetails);

        SettingUpdateResponse res = new SettingUpdateResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());

        User user = userDAO.findUser(userDTO.getId());
        UserDTO userDto = new UserDTO(user);
        res.setUser(userDto);
        res.getUser().setId(userDetails.getId());
        res.getUser().setPassword(null);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
