package com.unadopcion.unadopcion.controladores;

import com.unadopcion.unadopcion.herramientas.Fecha;
import com.unadopcion.unadopcion.herramientas.MiLogger;
import com.unadopcion.unadopcion.modelo.Denuncia;
import com.unadopcion.unadopcion.modelo.Usuario;
import com.unadopcion.unadopcion.pojo.DenunciaPOJO;
import com.unadopcion.unadopcion.servicio.DenunciaServicio;
import com.unadopcion.unadopcion.servicio.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DenunciaControlador {

    MiLogger miLogger = new MiLogger(DenunciaControlador.class);

    @Autowired
    private DenunciaServicio denunciaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @RequestMapping(value = "/consultar-maltrato/", produces = "application/json")
    public List<Denuncia> consultaMaltratoId(@RequestBody DenunciaPOJO denunciaPOJO) {
        boolean nombre = denunciaPOJO.getUsuarioId() > 0;
        boolean id = denunciaPOJO.getAnimalId() > 0;
        boolean tipo = denunciaPOJO.getDenunTipo() != "";
        //System.out.println(nombre + "-" + id + "-" + tipo);

        int id_user = denunciaPOJO.getUsuarioId();
        int id_animal = denunciaPOJO.getAnimalId();
        String tipo_animal = denunciaPOJO.getDenunTipo();

        if(nombre && id && tipo){
            return null;
        }else if(nombre && id){
            return denunciaServicio.findAllByUsuarioIdAndAnimalId(id_user,id_animal);
        }else if(nombre && tipo){
            return null;
        }else if(id && tipo){
            return null;
        }else if(id){
            return denunciaServicio.findAllByAnimalId(id_animal);
        }else if(tipo){
            return denunciaServicio.findAllByDenunTipo(tipo_animal);
        }else if(nombre){
            return denunciaServicio.findAllByUsuarioId(id_user);
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/denunciar-maltrato", method = RequestMethod.POST)
    public ResponseEntity<Void> denunciarMaltrato(@RequestBody DenunciaPOJO denunciaPOJO) {
        Usuario usuario = usuarioServicio.buscarUsuarioNombre(denunciaPOJO.getNombreUsuario());
        int usuarioId = 0;
        usuarioId = usuario.getUsuarioId();
        int animalId = 0;
        animalId = denunciaPOJO.getAnimalId();
        Fecha fecha = new Fecha();
        String denunTipo = denunciaPOJO.getDenunTipo();
        String denunDescrip = denunciaPOJO.getDenunDescrip();
        String detalles = denunciaPOJO.getDetalles();

        boolean existe = usuarioServicio.usuarioIdExiste(usuarioId);

        ;
        if (!existe) {
            miLogger.cuidado("El usuario " + usuarioId + " no existe");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            denunciaServicio.crearDenuncia(usuarioId, animalId, fecha.getFecha(), denunTipo, denunDescrip, detalles);
            miLogger.info("Se ha registrado la denuncia del usuario" + usuarioId);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @GetMapping(value = "/consultar-maltrato/usuario/{nombre}", produces = "application/json")
    public List<Denuncia> consultaMaltratoPorNombreUsuario(@PathVariable String nombre) {
        Usuario usuario_consultado = usuarioServicio.buscarUsuarioNombre(nombre);
        boolean existe = usuarioServicio.usuarioIdExiste(usuario_consultado.getUsuarioId());
        if (!existe) {
            miLogger.cuidado("El usuario consultado " + usuario_consultado.getUsuarioNombre() + " no existe. ");
        } else {
            miLogger.info("El usuario " + " consulto los casos de maltrato del usuario "
                    + usuario_consultado.getUsuarioNombre());
        }
        return denunciaServicio.findAllByUsuarioId(usuario_consultado.getUsuarioId());
    }

}