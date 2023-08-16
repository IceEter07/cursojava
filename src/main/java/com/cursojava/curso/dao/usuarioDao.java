package com.cursojava.curso.dao;

import com.cursojava.curso.models.usuario;

import java.util.List;

public interface usuarioDao {
    List<usuario> getUsuarios();

    void eliminar(Long id);

    void registarUsuario(usuario usuario);

    usuario obtenerUsuarioPorCredenciales(usuario usuario);
}
