package com.msvc.auth.service;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.msvc.auth.entity.UserEntity;
import com.msvc.auth.validator.UserForm;

public interface IAuthService {

        public HashMap<String, ?> login(UserEntity user) throws Exception;

    public ResponseEntity<?> registrarUser (UserForm userForm, BindingResult result);

}
