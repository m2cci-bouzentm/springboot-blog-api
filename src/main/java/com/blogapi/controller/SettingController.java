package com.blogapi.controller;


import com.blogapi.dto.UserDTO;
import com.blogapi.response.SettingUpdateResponse;
import com.blogapi.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
@CrossOrigin
public class SettingController {

    private final SettingService settingService;

    @Autowired
    public SettingController( SettingService settingService) {
        this.settingService = settingService;
    }


    @PutMapping("/username")
    public ResponseEntity<SettingUpdateResponse> updateUsername(@RequestBody UserDTO userDTO) {
        return settingService.updateUsername(userDTO);
    }

    @PutMapping("/email")
    public ResponseEntity<SettingUpdateResponse> updateEmail(@RequestBody UserDTO userDTO) {
        return  settingService.updateEmail(userDTO);
    }

    @PutMapping("/password")
    public ResponseEntity<SettingUpdateResponse> updatePassword(@RequestBody UserDTO userDTO) {
        return settingService.updatePassword(userDTO);
    }

    @PutMapping("/avatarUrl")
    public ResponseEntity<SettingUpdateResponse> updateAvatarUrl(@RequestBody UserDTO userDTO) {
        return settingService.updateAvatarUrl(userDTO);
    }

    @PutMapping("/role")
    public ResponseEntity<SettingUpdateResponse> updateRole(@RequestBody UserDTO userDTO) {
        return settingService.updateRole(userDTO);
    }
}
