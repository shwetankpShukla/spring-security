package com.sp.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/*************************************
 * This Class is used to
 * Author  : Shwetank Shukla
 * File    : com.sp.user.domain.UserMtb
 *
 * Date    : 15 September 2022
 * Version : 1.0
 **************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_mtb")
@Builder
public class UserMtb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String email;
    private String mobile;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleStb> roles=new ArrayList<>();
}
