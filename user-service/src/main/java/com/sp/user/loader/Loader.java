package com.sp.user.loader;

import com.sp.user.domain.RoleStb;
import com.sp.user.domain.UserMtb;
import com.sp.user.repository.RoleRepository;
import com.sp.user.repository.UserRepository;
import com.sp.user.serivice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/*************************************
 * This Class is used to
 * Author  : Shwetank Shukla
 * File    : com.sp.user.loader.Loader
 *
 * Date    : 15 September 2022
 * Version : 1.0
 **************************************/
@Component
@Slf4j
public class Loader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        addLoader();

    }

    private void addLoader() {
        if(roleRepository.count()==0){
            roleRepository.save(RoleStb.builder().name("ROLE_USER").build());
            roleRepository.save(RoleStb.builder().name("ROLE_MANGER").build());
            roleRepository.save(RoleStb.builder().name("ROLE_ADMIN").build());
            roleRepository.save(RoleStb.builder().name("ROLE_SUPER_ADMIN").build());
        }
        if(userRepository.count()==0){
            userService.saveUser(UserMtb.builder().userName("kalyan").password("Password12").mobile("9876543210")
                    .email("kalyan@gail.com").roles(new ArrayList<>()).build());
            userService.saveUser(UserMtb.builder().userName("oviya").password("Password12").mobile("9876543210")
                    .email("oviya@gail.com").roles(new ArrayList<>()).build());
            userService.saveUser(UserMtb.builder().userName("manhohar").password("Password12").mobile("9876543210")
                    .email("manhohar@gail.com").roles(new ArrayList<>()).build());
            userService.saveUser(UserMtb.builder().userName("neha").password("Password12").mobile("9876543210")
                    .email("neha@gail.com").roles(new ArrayList<>()).build());
            userService.saveUser(UserMtb.builder().userName("thiru").password("Password12").mobile("9876543210")
                    .email("thiru@gail.com").roles(new ArrayList<>()).build());

            userService.saveUser(UserMtb.builder().userName("naveen").password("Password12").mobile("9876543210")
                    .email("naveen@gail.com").roles(new ArrayList<>()).build());

            userService.addRoleToUser("kalyan","ROLE_SUPER_ADMIN");
            userService.addRoleToUser("thiru","ROLE_ADMIN");
            userService.addRoleToUser("oviya","ROLE_MANGER");
            userService.addRoleToUser("neha","ROLE_USER");
            userService.addRoleToUser("naveen","ROLE_USER");
            userService.addRoleToUser("manohar","ROLE_USER");

        }
        log.info("Role added kalyan , ROLE_USER , ROLE_MANGER , ROLE_ADMIN, ROLE_SUPER_ADMIN");
        log.info("user added kalyan , oviya , neha , manohar, thiru, naveen");

    }
}
