package com.apps.backend.controller;

import com.apps.backend.dto.GenericResponseDTO;
import com.apps.backend.dto.LoginRequestDTO;
import com.apps.backend.dto.RegisterRequestDTO;
import com.apps.backend.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class AppController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AppController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<GenericResponseDTO>
    handleRegisterRequest(@Valid @RequestBody RegisterRequestDTO requestDTO){
        GenericResponseDTO responseDTO = authenticationService.createUser(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(responseDTO.getStatus()));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<GenericResponseDTO>
    handleLoginRequest(@Valid @RequestBody LoginRequestDTO requestDTO){
        System.out.println(requestDTO.getUsername());
        GenericResponseDTO responseDTO = authenticationService.authenticateUser(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(responseDTO.getStatus()));
    }
}
