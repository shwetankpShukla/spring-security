package com.sp.user.controller;

import com.sp.user.domain.RoleStb;
import com.sp.user.domain.UserMtb;
import com.sp.user.serivice.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/*************************************
 * This Class is used to
 * Author  : Shwetank Shukla
 * File    : com.sp.user.controller.UserController
 *
 * Date    : 15 September 2022
 * Version : 1.0
 **************************************/
@RestController
@RequestMapping("/api/user")
//@RequiredArgsConstructor

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserMtb>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());

    }

    @PostMapping ("/save/user")
    public ResponseEntity<UserMtb> saveUser(@RequestBody UserMtb userMtb) {
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(userMtb));

    }

    @PostMapping ("/save/role")
    public ResponseEntity<RoleStb> saveRole(@RequestBody RoleStb roleStb) {
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/role/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveRole(roleStb));

    }

    @PostMapping ("/role/assign")
    public ResponseEntity<RoleStb> assignRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }



    @GetMapping("/")
    public ResponseEntity<List<UserMtb>> getUser() {
        return ResponseEntity.ok().body(userService.getUsers());

    }


}
@Data
class RoleToUserForm{
    String userName;
    String roleName;
}

