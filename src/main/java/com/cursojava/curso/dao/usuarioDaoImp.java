package com.cursojava.curso.dao;

import com.cursojava.curso.models.usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
//Permite armar las consultas de SQL a la BD
@Transactional
public class usuarioDaoImp implements usuarioDao{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<usuario> getUsuarios() {
        String query = "FROM usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        usuario Usuario = entityManager.find(usuario.class, id);
        entityManager.remove(Usuario);
    }

    @Override
    public void registarUsuario(usuario usuario) {
        entityManager.merge(usuario);
    }
}
