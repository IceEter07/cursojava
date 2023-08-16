package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.usuarioDao;
import com.cursojava.curso.models.usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authController {
    @Autowired
    private usuarioDao UsuarioDao;

    @Autowired
    private JWTUtil jwtutil;



    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody usuario usuario){

        usuario usuarioLogeado = UsuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogeado != null) {

            //Se retorna el TOKEN
            return jwtutil.create(String.valueOf(usuarioLogeado.getId()), usuarioLogeado.getEmail());
        }
        return "Fault";
    }
}
