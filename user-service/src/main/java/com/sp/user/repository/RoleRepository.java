package com.sp.user.repository;

import com.sp.user.domain.RoleStb;
import org.springframework.data.jpa.repository.JpaRepository;

/*************************************
 * This Class is used to
 * Author  : Shwetank Shukla
 * File    : com.sp.user.repository.RoleRepository
 *
 * Date    : 15 September 2022
 * Version : 1.0
 **************************************/
public interface RoleRepository extends JpaRepository<RoleStb,Long> {
    RoleStb findByName(String name);
}
