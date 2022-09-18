package com.sp.user.serivice;

import com.sp.user.domain.RoleStb;
import com.sp.user.domain.UserMtb;
import com.sp.user.repository.RoleRepository;
import com.sp.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*************************************
 * This Class is used to
 * Author  : Shwetank Shukla
 * File    : com.sp.user.serivice.UserServiceImpl
 *
 * Date    : 15 September 2022
 * Version : 1.0
 **************************************/
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMtb userMtb =getUser(username);
        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
        userMtb.getRoles().forEach(role ->{
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(userMtb.getUserName(),userMtb.getPassword(),roles);
    }

    @Override
    public UserMtb saveUser(UserMtb userMtb) {
        log.info("UserServiceImpl >> saveUser {}",userMtb.getUserName());
        userMtb.setPassword(passwordEncoder.encode(userMtb.getPassword()));
        return userRepository.save(userMtb);
    }

    @Override
    public RoleStb saveRole(RoleStb roleStb) {
        log.info("UserServiceImpl >> saveRole {}",roleStb.getName());
        return roleRepository.save(roleStb);
    }

    @Override
    public void addRoleToUser(String userName, String userRole) {
        log.info("UserServiceImpl >> addRoleToUser {} {}",userName,userRole);
        UserMtb mtb = userRepository.findByUserName(userName);
        RoleStb stb = roleRepository.findByName(userRole);
        mtb.getRoles().add(stb);

    }

    @Override
    public UserMtb getUser(String userName) {
        log.info("UserServiceImpl >> getUser {}",userName);
        UserMtb userMtb =userRepository.findByUserName(userName);
        if(userMtb ==null){
            log.error("user not found in database {}",userName);
            throw  new UsernameNotFoundException("user not found in database");
        }
        return userMtb;
    }

    @Override
    public List<UserMtb> getUsers() {
        log.info("UserServiceImpl >> getUsers ");
        return userRepository.findAll();
    }


}
