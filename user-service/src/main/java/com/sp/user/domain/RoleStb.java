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
 * File    : com.sp.user.domain.RoleStb
 *
 * Date    : 15 September 2022
 * Version : 1.0
 **************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_stb")
@Builder
public class RoleStb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

}
