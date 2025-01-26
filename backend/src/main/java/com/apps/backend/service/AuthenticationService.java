package com.apps.backend.service;

import com.apps.backend.dto.GenericResponseDTO;
import com.apps.backend.dto.LoginRequestDTO;
import com.apps.backend.dto.RegisterRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    public GenericResponseDTO createUser(RegisterRequestDTO requestDTO);
    public GenericResponseDTO authenticateUser(LoginRequestDTO requestDTO);
}
