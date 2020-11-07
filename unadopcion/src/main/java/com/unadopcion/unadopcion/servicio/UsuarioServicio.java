package com.unadopcion.unadopcion.servicio;

import com.unadopcion.unadopcion.modelo.Usuario;
import com.unadopcion.unadopcion.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Usuario crearUsuario(Usuario user) {
        return usuarioRepositorio.save(user);
    }


    public void editar(Usuario user) {
        usuarioRepositorio.save(user);
    }

    @Transactional
    public Usuario crearContacto() {
        Usuario usuario = new Usuario();
        return usuarioRepositorio.save(usuario);
    }

    public Usuario buscarPorLogeoId(int id){
        return usuarioRepositorio.getFirstByLogeoId(id);
    }
    public boolean existeEmail(String email) {
        return usuarioRepositorio.existsByUsuarioEmail(email);
    }
    public Usuario buscarCorreo(String email){
        return usuarioRepositorio.findFirstByUsuarioEmail(email);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public Usuario buscarUsuarioNombre(String nombre) {
        return usuarioRepositorio.findByUsuarioNombreRealIsLike(nombre);
    }

    public Usuario buscarUsuarioPorGoogleId(String googleId) {
        return usuarioRepositorio.getUsuarioByUsuarioGoogleId(googleId);
    }

    public List<Usuario> buscarUsuarios(String nombre) {
        return usuarioRepositorio.findAllByUsuarioNombreRealIsLike(nombre);
    }

    public boolean usuarioExiste(String nombre) {
        return usuarioRepositorio.existsByUsuarioNombre(nombre);
    }

    public Usuario usuarioEnEdición(String rol){
        return usuarioRepositorio.findByUsuarioRol(rol);
    }

    public boolean usuarioExistePorGoogleId(String googleId) {
        return usuarioRepositorio.existsByUsuarioGoogleId(googleId);
    }

    public boolean usuarioIdExiste(int usuarioId) {
        return usuarioRepositorio.existsByUsuarioId(usuarioId);
    }

}

