package com.unadopcion.unadopcion.servicio;

import com.unadopcion.unadopcion.modelo.Logeo;
import com.unadopcion.unadopcion.repositorio.LogeoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LogeoServicio {

    @Autowired
    private LogeoRepositorio logeoRepositorio;

    @Transactional
    public Logeo crearLogeo(String nombre, String contrasena) {
        Logeo logeo = new Logeo();
        logeo.setLogeoNombre(nombre);
        logeo.setLogeoContra(contrasena);
        return logeoRepositorio.save(logeo);
    }

    @Transactional
    public Logeo guardar(Logeo logeo){
        return logeoRepositorio.save(logeo);
    }

    public boolean existsByEmail(String email) {
        return logeoRepositorio.existsByLogeoNombre(email);
    }

    public Logeo buscarEmail(String email) {
        return logeoRepositorio.findByLogeoNombre(email);
    }

    public boolean verificarContrasena(String nombre, String contrasena) {
        Logeo logeo = logeoRepositorio.findByLogeoNombre(nombre);
        if (logeo.getLogeoContra().equals(contrasena)) {
            return true;
        } else {
            return false;
        }
    }


    public boolean existeLogeoToken(String token) {
        return logeoRepositorio.existsByToken(token);
    }
    public Logeo buscarLogeoByToken(String token){
        return logeoRepositorio.findFirstByToken(token);
    }
}
