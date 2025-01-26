package com.apps.backend.service.impl;

import com.apps.backend.dao.AuthorityRepository;
import com.apps.backend.dao.UserRepository;
import com.apps.backend.dto.GenericResponseDTO;
import com.apps.backend.dto.LoginRequestDTO;
import com.apps.backend.dto.RegisterRequestDTO;
import com.apps.backend.exceptions.AuthorityNotFoundException;
import com.apps.backend.model.Authority;
import com.apps.backend.model.User;
import com.apps.backend.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public GenericResponseDTO createUser(RegisterRequestDTO requestDTO) {
        Set<Authority> newAuthorities = new HashSet<>();

        for(Authority authority  : requestDTO.getAuthorities()){
            System.out.println(authority.getAuthority());
            Authority fetched = authorityRepository.findByAuthority(authority.getAuthority())
                    .orElseThrow(() -> new AuthorityNotFoundException(String.format("Authority %s not found", authority.getAuthority())));

            newAuthorities.add(fetched);
        }

        User user = modelMapper.map(requestDTO, User.class);
        user.setAuthorities(newAuthorities);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        userRepository.save(user);

        return new GenericResponseDTO(user, 200);
    }

    @Override
    public GenericResponseDTO authenticateUser(LoginRequestDTO requestDTO) {
        return null;
    }
}
