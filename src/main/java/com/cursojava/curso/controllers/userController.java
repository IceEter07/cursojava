package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.usuarioDao;
import com.cursojava.curso.models.usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class userController {

    @Autowired
    private usuarioDao UsuarioDao;

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

    @RequestMapping(value = "api/usuarios")
    public List<usuario> getUsuarios(){
        return UsuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody usuario usuario){
        UsuarioDao.registarUsuario(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id ){
        UsuarioDao.eliminar(id);
    }
}
