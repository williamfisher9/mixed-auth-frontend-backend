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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public GenericResponseDTO createUser(RegisterRequestDTO requestDTO) {
        Set<Authority> newAuthorities = new HashSet<>();

        for(Authority authority  : requestDTO.getAuthorities()){
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
        System.out.println(requestDTO.getUsername());
        System.out.println(requestDTO.getPassword());

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword());

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(token);
        } catch (Exception e){
            return new GenericResponseDTO(e.getMessage(), 401);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, Object> principal = new HashMap<>();
        principal.put("authenticated", authentication.isAuthenticated());
        principal.put("user_id", ((User) authentication.getPrincipal()).getId());
        principal.put("username", ((UserDetails) authentication.getPrincipal()).getUsername());
        principal.put("authorities", ((UserDetails) authentication.getPrincipal()).getAuthorities());

        return new GenericResponseDTO(principal, 200);
    }
}
