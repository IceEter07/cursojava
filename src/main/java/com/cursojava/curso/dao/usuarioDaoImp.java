package com.cursojava.curso.dao;

import com.cursojava.curso.models.usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @Override
    public usuario obtenerUsuarioPorCredenciales(usuario usuario) {
        String query = "FROM usuario WHERE email = :email";
        List<usuario> lista =  entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (lista.isEmpty()){
            return null;
        }
        //Obtener la contraseña y compararla con Argon 2
        String passwordHashed = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0);
        }
        return null;
    }
}
