package com.fptyoga.yogacenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    List<Role> findByRoleidNot(Long roleId);
}
