package com.unadopcion.unadopcion.controladores;

import com.unadopcion.unadopcion.herramientas.MiLogger;
import com.unadopcion.unadopcion.modelo.Logeo;
import com.unadopcion.unadopcion.modelo.Usuario;
import com.unadopcion.unadopcion.pojo.NuevoUsuarioPOJO;
import com.unadopcion.unadopcion.pojo.UsuarioLogeoPOJO;
import com.unadopcion.unadopcion.servicio.LogeoServicio;
import com.unadopcion.unadopcion.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@CrossOrigin
@RestController
public class LogeoControlador {

    MiLogger miLogger = new MiLogger(LogeoControlador.class);
    @Autowired
    private LogeoServicio logeoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    @RequestMapping(value = "/buscar/{nombre}", produces = "application/json")
    public Usuario buscar(@PathVariable String nombre) {
        return usuarioServicio.usuarioEnEdición(nombre);
    }

    @RequestMapping(value = "/crear-usuario")
    public ResponseEntity<Void> crearNuevoUsuario(@RequestBody NuevoUsuarioPOJO nuevoUsuarioPOJO) {
        boolean correoExiste = logeoServicio.existsByEmail(nuevoUsuarioPOJO.getCorreo());
        // crea logeo primero, verifica que no exista usuario nombre o usuario con mismo
        // correo
        if (correoExiste) {
            Usuario user = new Usuario();
            Logeo logeo = logeoServicio.buscarEmail(nuevoUsuarioPOJO.getCorreo());
            user = usuarioServicio.buscarCorreo(nuevoUsuarioPOJO.getCorreo());

            // primero crear logeo
            ///logeo.setLogeoNombre(nuevoUsuarioPOJO.getNombre());
            logeo.setLogeoContra(passwordEncoder.encode(nuevoUsuarioPOJO.getContrasena()));
            logeo = logeoServicio.guardar(logeo);

            // crear usuario con el id del logeo
            ///user.setLogeoId(logeo.getLogeoId());
            user.setUsuarioNombre(nuevoUsuarioPOJO.getNombre());
            ///user.setUsuarioNombreReal(nuevoUsuarioPOJO.getNombreReal());
            ///user.setUsuarioEmail(nuevoUsuarioPOJO.getCorreo());
            user.setUsuarioTelefono(nuevoUsuarioPOJO.getTelefono());
            user.setUsuarioRol(nuevoUsuarioPOJO.getRol());
            user.setUsuarioLugar(nuevoUsuarioPOJO.getLugar());
            user.setUsuarioInfo(nuevoUsuarioPOJO.getInfo());
            ///user.setUsuarioGoogleId(nuevoUsuarioPOJO.getIdGoogle());
            user = usuarioServicio.crearUsuario(user);


            miLogger.info("Se registro un nuevo usuario con el mombre " + user.getUsuarioNombre() + " y rol "
                    + user.getUsuarioRol());
            // nuevo usuario creado
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            miLogger.cuidado("Ha ocurrido un error al intentar formalizar el registro de " + nuevoUsuarioPOJO.getNombre());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/editar-usuario")
    public ResponseEntity<Void> editarUsuario(@RequestBody NuevoUsuarioPOJO editarUsuarioPOJO) {

        boolean nombreExisteLog = logeoServicio.existsByEmail(editarUsuarioPOJO.getNombre());
        boolean nombreExisteUser = usuarioServicio.usuarioExiste(editarUsuarioPOJO.getNombre());

        if (nombreExisteLog && nombreExisteUser) {
            Usuario user = usuarioServicio.buscarUsuarioNombre(editarUsuarioPOJO.getNombre());
            Logeo logeo = logeoServicio.buscarEmail(editarUsuarioPOJO.getNombre());

            user.setUsuarioNombreReal(editarUsuarioPOJO.getNombreReal());
            user.setUsuarioEmail(editarUsuarioPOJO.getCorreo());
            user.setUsuarioTelefono(editarUsuarioPOJO.getTelefono());
            logeo.setLogeoContra(passwordEncoder.encode(editarUsuarioPOJO.getContrasena()));
            user.setUsuarioRol(editarUsuarioPOJO.getRol());
            user.setUsuarioLugar(editarUsuarioPOJO.getLugar());
            user.setUsuarioInfo(editarUsuarioPOJO.getInfo());

            logeoServicio.guardar(logeo);
            usuarioServicio.editar(user);

            miLogger.info("Se registro edición de usuario con el mombre " + user.getUsuarioNombre() + " y rol "
                    + user.getUsuarioRol());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/hacer-logeo")
    public ResponseEntity<String> hacerLogeo(@RequestBody UsuarioLogeoPOJO usuarioLogeoPOJO) {

        boolean existe = usuarioServicio.existeEmail(usuarioLogeoPOJO.getNombre());
        // si el usuario existe intentar logeo
        if (!existe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);// el usuario no fue encontrado
        } else {
            // intentar logeo, si no, returnar no autorizado
            Logeo logeo = logeoServicio.buscarEmail(usuarioLogeoPOJO.getNombre());
            if (passwordEncoder.matches(usuarioLogeoPOJO.getContrasena(), logeo.getLogeoContra())) {
                String token = generateNewToken();
                logeo.setToken(token);
                logeoServicio.guardar(logeo);
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @RequestMapping(value = "/mostrar-perfil/{token}", produces = "application/json")
    public Usuario buscarUsuarioPorId(@PathVariable String token) {
        boolean logeado = logeoServicio.existeLogeoToken(token);
        if (logeado) {
            Logeo logeo = logeoServicio.buscarLogeoByToken(token);
            Usuario user = usuarioServicio.buscarPorLogeoId(logeo.getLogeoId());
            return user;
        } else {
            miLogger.cuidado("El usuario no está Logeado o ha terminado su sesion" + token);
            return null;
        }
    }

    @RequestMapping(value = "mostrar-usuarios/{nombre}", produces = "application/json")
    public List<Usuario> mostrarUsuarios(@PathVariable String nombre) {
        return usuarioServicio.buscarUsuarios(nombre);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
