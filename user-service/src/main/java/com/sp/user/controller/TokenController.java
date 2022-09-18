package com.sp.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.user.domain.RoleStb;
import com.sp.user.domain.UserMtb;
import com.sp.user.serivice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/*************************************
 * This Class is used to
 * Author  : Shwetank Shukla
 * File    : com.sp.user.controller.TokenController
 *
 * Date    : 17 September 2022
 * Version : 1.0
 **************************************/
@RestController
@RequestMapping("/api/token/")
@Slf4j
public class TokenController {

    @Autowired
    private UserService userService;

    @GetMapping("refresh")
    public  void  refreshToken(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String userName =decodedJWT.getSubject();
                UserMtb userMtb =userService.getUser(userName);
                String access_token =JWT.create().withSubject(userMtb.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", userMtb.getRoles().stream().map(RoleStb::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> tokenMap = new HashMap<>();
                tokenMap.put("access_token",access_token);
                tokenMap.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokenMap);

            }catch (Exception exception){
                log.error("Error occurred while login {} ",exception.getMessage());
                response.setHeader("Error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String,String> errorMap = new HashMap<>();
                errorMap.put("Error",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),errorMap);
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
