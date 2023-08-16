package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.usuarioDao;
import com.cursojava.curso.models.usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class userController {

    @Autowired
    private usuarioDao UsuarioDao;

    @Autowired
    private JWTUtil jwtutil;

    /*@RequestMapping(value = "api/usuario/{id}")
    public usuario getUser(@PathVariable Long id){
        usuario User = new usuario();
        User.setId(id);
        User.setNombre("Lucas");
        User.setApellido("Reyes");
        User.setEmail("Lucas@gmail.com");
        User.setTelefono("23456782344"); User.setId(id);
        return User;
    }*/

    private boolean validarToken(String token){
        //Extraer el ID del usuario
        String userId = jwtutil.getKey(token);
        return userId != null;

    }
    @RequestMapping(value = "api/usuarios")
    //Recibir el token del usuario a traves de la cabecera
    public List<usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){

        if (!validarToken(token)){
            return null;
        }

        return UsuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody usuario usuario){

        //Argon 2 ayuda a encriptar la contraseña
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //Iteraciones, memoria, paralelismo, string a hashear
        String hash =argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);


        UsuarioDao.registarUsuario(usuario);

    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id ){
        if (!validarToken(token)){
            return;
        }
        UsuarioDao.eliminar(id);
    }
}
