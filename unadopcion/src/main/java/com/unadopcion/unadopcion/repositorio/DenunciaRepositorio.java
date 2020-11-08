package com.unadopcion.unadopcion.repositorio;

import java.util.List;

import com.unadopcion.unadopcion.modelo.Denuncia;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenunciaRepositorio extends CrudRepository<Denuncia, Integer> {

    List<Denuncia> findAllByUsuarioId(int usuarioId);

    List<Denuncia> findAllByAnimalId(int animalId);

    List<Denuncia> findAllByDenunTipo(String tipo);

    List<Denuncia> findAllByUsuarioIdAndAnimalId(int usuarioId, int animalId);
    List<Denuncia> findAllByUsuarioIdAndDenunTipo(int usuarioId, String tipo);
    List<Denuncia> findAllByAnimalIdAndDenunTipo(int animalId, String tipo);
    List<Denuncia> findAllByUsuarioIdAndAnimalIdAndDenunTipo(int usuarioId, int animalId,String tipo);
}