package com.msvc.auth.controller;

import java.util.HashMap;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.msvc.auth.entity.UserEntity;
import com.msvc.auth.service.IAuthService;
import com.msvc.auth.validator.UserForm;


//import com.msvc.gateway.service.IJWTUtilityService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;
   /* @Autowired
    private IJWTUtilityService jwtUtilityService;*/


    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody UserEntity user) throws Exception {;
       HashMap<String,?> login = authService.login(user);
       if(login.containsKey("jwt")){
           return ResponseEntity.ok()
                   .header("x-Auth-Token", (String) login.get("jwt"))
                   .header("userID", (String) login.get("userID"))
                   .body(login);
       }
       return  new ResponseEntity<>(login, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    private ResponseEntity<?> register (@Valid @RequestBody UserForm userForm, BindingResult result){
        return authService.registrarUser(userForm,result);
    }

}
