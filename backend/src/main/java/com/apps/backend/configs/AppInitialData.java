package com.apps.backend.configs;

import com.apps.backend.dao.AuthorityRepository;
import com.apps.backend.model.Authority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AppInitialData implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppInitialData.class);

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AppInitialData(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Authority> authorities = authorityRepository.findAll();
        LOGGER.info("Creating initial data...");
        if(authorities.isEmpty()){
            Authority authority1 = new Authority("ROLE_ADMIN");
            Authority authority2 = new Authority("ROLE_USER");
            authorityRepository.saveAll(Arrays.asList(authority1, authority2));
        }
    }
}
