package com.apps.backend.dao;

import com.apps.backend.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    public Optional<Authority> findByAuthority(String name);
}
