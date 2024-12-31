package com.blogapi.controller;


import com.blogapi.dto.UserDTO;
import com.blogapi.response.SettingUpdateResponse;
import com.blogapi.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class SettingController {

    private final SettingsService settingsService;

    @Autowired
    public SettingController( SettingsService settingsService) {
        this.settingsService = settingsService;
    }


    @PutMapping("/username")
    public ResponseEntity<SettingUpdateResponse> updateUsername(@RequestBody UserDTO userDTO) {
        return settingsService.updateUsername(userDTO);
    }

    @PutMapping("/email")
    public ResponseEntity<SettingUpdateResponse> updateEmail(@RequestBody UserDTO userDTO) {
        return  settingsService.updateEmail(userDTO);
    }

    @PutMapping("/password")
    public ResponseEntity<SettingUpdateResponse> updatePassword(@RequestBody UserDTO userDTO) {
        return settingsService.updatePassword(userDTO);
    }

    @PutMapping("/avatarUrl")
    public ResponseEntity<SettingUpdateResponse> updateAvatarUrl(@RequestBody UserDTO userDTO) {
        return settingsService.updateAvatarUrl(userDTO);
    }

    @PutMapping("/role")
    public ResponseEntity<SettingUpdateResponse> updateRole(@RequestBody UserDTO userDTO) {
        return settingsService.updateRole(userDTO);
    }
}
