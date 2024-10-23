package com.supergiros.msvc.msvc_produc.service.impl;

import com.supergiros.msvc.msvc_produc.repository.IUserRepository;
import com.supergiros.msvc.msvc_produc.service.ISeguridadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SeguridadServiceImpl implements ISeguridadService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public HashMap<String,Boolean> validarToken(String userId,String token){
        var id = Integer.parseInt(userId);
        var userEntity = userRepository.findById(id).orElse(null);
        HashMap<String,Boolean> response = new HashMap<>();
        if (userEntity != null){

             response.put("validacion",userEntity.getToken().equals(token));
             response.put("user",true);
             return  response;
        }
        response.put("validacion",false);
        response.put("user",false);
        return  response;

    }

}
