package com.projects.libraryproject.repository;

import com.projects.libraryproject.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> getRoleEntityByRoleName(String roleName);

}
