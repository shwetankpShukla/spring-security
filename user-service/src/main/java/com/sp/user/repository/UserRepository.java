package com.sp.user.repository;

import com.sp.user.domain.UserMtb;
import org.springframework.data.jpa.repository.JpaRepository;

/*************************************
 * This Class is used to
 * Author  : Shwetank Shukla
 * File    : com.sp.user.repository.UserRepository
 *
 * Date    : 15 September 2022
 * Version : 1.0
 **************************************/
public interface UserRepository extends JpaRepository<UserMtb,Long> {

    UserMtb findByUserName(String userName);
}
