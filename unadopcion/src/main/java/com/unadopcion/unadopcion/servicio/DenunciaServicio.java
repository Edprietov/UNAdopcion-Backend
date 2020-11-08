package com.unadopcion.unadopcion.servicio;

import java.util.List;

import javax.transaction.Transactional;

import com.unadopcion.unadopcion.modelo.Denuncia;
import com.unadopcion.unadopcion.repositorio.DenunciaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DenunciaServicio {
    @Autowired
    private DenunciaRepositorio denunciaRepositorio;

    @Transactional
    public Denuncia crearDenuncia(int usuarioId, int animalId, String denunFecha, String denunTipo, String denunDescrip, String detalles) {

        Denuncia denuncia = new Denuncia();
        denuncia.setUsuarioId(usuarioId);
        denuncia.setAnimalId(animalId);
        denuncia.setDenunFecha(denunFecha);
        denuncia.setDenunTipo(denunTipo);
        denuncia.setDenunDescrip(denunDescrip);
        denuncia.setDetalles(detalles);
        return denunciaRepositorio.save(denuncia);

    }

    public List<Denuncia> findAllByUsuarioId(int idUser) {
        return denunciaRepositorio.findAllByUsuarioId(idUser);
    }

    public Denuncia actualizar(Denuncia denuncia) {
        return denunciaRepositorio.save(denuncia);
    }

    public List<Denuncia> findAllByAnimalId(int id) {
        return denunciaRepositorio.findAllByAnimalId(id);
    }

    public List<Denuncia> findAllByDenunTipo(String tipo){
        return denunciaRepositorio.findAllByDenunTipo(tipo);
    }
    public List<Denuncia> findAllByUsuarioIdAndAnimalId(int usuarioId, int animalId){
        return denunciaRepositorio.findAllByUsuarioIdAndAnimalId(usuarioId,animalId);
    }
}