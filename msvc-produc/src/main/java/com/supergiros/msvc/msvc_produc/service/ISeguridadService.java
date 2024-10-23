package com.supergiros.msvc.msvc_produc.service;


import java.util.HashMap;

public interface ISeguridadService {

    public HashMap<String,Boolean> validarToken(String userId,String token);
}
