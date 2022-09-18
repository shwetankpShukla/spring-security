package com.sp.user.serivice;

import com.sp.user.domain.RoleStb;
import com.sp.user.domain.UserMtb;

import java.util.List;

/*************************************
 * This Class is used to
 * Author  : Shwetank Shukla
 * File    : com.sp.user.serivice.UserService
 *
 * Date    : 15 September 2022
 * Version : 1.0
 **************************************/
public interface UserService {
    UserMtb saveUser(UserMtb userMtb);
    RoleStb saveRole(RoleStb roleStb);

    void addRoleToUser(String userName,String userRole);

    UserMtb getUser(String userName);

    List<UserMtb> getUsers();
}
