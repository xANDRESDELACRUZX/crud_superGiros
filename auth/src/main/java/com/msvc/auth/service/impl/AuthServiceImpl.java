package com.msvc.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.msvc.auth.entity.UserEntity;
import com.msvc.auth.repository.IUserRepository;
import com.msvc.auth.service.IAuthService;
import com.msvc.auth.service.IJWTUtilityService;
import com.msvc.auth.validator.UserForm;

import jakarta.validation.Valid;

@Service
public class AuthServiceImpl implements IAuthService{

    
    @Autowired
    private IUserRepository userRepositorio;

    @Autowired
    private IJWTUtilityService jwtUtilityService;

    //@Autowired //validaror en un futri
    //private IUserService userService;

    @Override
    public HashMap<String, ?> login(UserEntity user) throws Exception {
        try {
            HashMap<String, Object> jwt = new HashMap<>();
            UserEntity userFind  = userRepositorio.finByEmail(user.getEmail()).orElse(null);

              if(userFind == null){
                  jwt.put("error","usuario no encontrado");
                  return  jwt;
              }

              if (verificarPassword(user.getPassword(), userFind.getPassword())){
                  var token = jwtUtilityService.generateJWT(userFind.getId());

                  //actualizar user
                  userFind.setToken(token);
                  userRepositorio.save(userFind);

                  //set response
                  jwt.put("jwt", token);
                  jwt.put("userID", userFind.getId().toString());
                  jwt.put("login", true);
                  jwt.put("msg", "usuario logeado correctamente");
              }else
                  jwt.put("error","error en la autenticacion");

            return jwt;
        }catch (Exception e){
            throw new Exception(e.toString());
        }

    }
    private  boolean verificarPassword (String userLoginPassword, String password){
        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        return  encoder.matches(userLoginPassword,password);
    }
    @Override
    public ResponseEntity<?> registrarUser (@Valid @ModelAttribute UserForm userForm, BindingResult result){

        if (result.hasErrors()) {
            // Mapa para almacenar errores
            Map<String, String> errores = new HashMap<>();

            // Recorrer los errores y añadirlos al mapa
            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errores);
        }

        //validar si existe el usuario
        List<UserEntity> getAllUsers = userRepositorio.findAll();

        for (UserEntity existingUser : getAllUsers){
         if (existingUser.getEmail().equals(userForm.getEmail())){
             return ResponseEntity.badRequest().body("el correo ya existes");
         }
        }


        //llenar el modelo user
        var user = new UserEntity();
        user.setNombres(userForm.getNombres());
        user.setApellidos(userForm.getApellidos());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());

        //encriptar pasword
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));

        //guardar user
        userRepositorio.save(user);
        return ResponseEntity.ok("usuario creado correctamente");
    }


}
