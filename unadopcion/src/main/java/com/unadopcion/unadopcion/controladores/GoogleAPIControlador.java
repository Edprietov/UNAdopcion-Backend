package com.unadopcion.unadopcion.controladores;

import com.unadopcion.unadopcion.herramientas.MiLogger;
import com.unadopcion.unadopcion.modelo.Logeo;
import com.unadopcion.unadopcion.modelo.Usuario;
import com.unadopcion.unadopcion.servicio.LogeoServicio;
import com.unadopcion.unadopcion.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
public class GoogleAPIControlador {
    MiLogger miLogger = new MiLogger(GoogleAPIControlador.class);
    // componente a que se va redirigir depues de autenticacion con Google
    private final String COMPONENTE_URL = "http://localhost:8081/";

    @Autowired
    private LogeoServicio logeoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @RequestMapping(value = "/registrar-usuario")
    public ResponseEntity<Object> registrar(@AuthenticationPrincipal OAuth2User user) throws URISyntaxException {
        String url = COMPONENTE_URL;
        Usuario usuario = new Usuario();
        HttpHeaders httpHeaders = new HttpHeaders();
        // Principal que llega de Google
        String nombre = user.getAttribute("name"); // nombre del usuario
        String idGoogle = user.getAttribute("sub");// id de google
        String urlFoto = user.getAttribute("picture");// foto en version URL. Puede cambiar, siempre guardar
        String correo = user.getAttribute("email");


        if (!usuarioServicio.usuarioExistePorGoogleId(idGoogle)) {
            Logeo logeo = new Logeo();

            logeo.setLogeoNombre(correo);
            logeo.setLogeoContra(idGoogle);
            logeo = logeoServicio.guardar(logeo);

            usuario.setLogeoId(logeo.getLogeoId());
            usuario.setUsuarioEmail(correo);
            usuario.setUsuarioNombreReal(nombre);
            usuario.setUsuarioGoogleId(idGoogle);
            usuario.setUsuarioUrlFoto(urlFoto);
            usuario.setUsuarioGoogleId(idGoogle);

            usuario.setUsuarioNombre(nombre);
            usuario.setUsuarioRol("f");
            usuario.setUsuarioTelefono("123");

            usuarioServicio.crearUsuario(usuario);
            url += "registro/";
        } else {
            url += "logeo/";
        }

        url = url.replaceAll(" ", "%20");// replazar espacios en url
        URI comp = new URI(url);
        httpHeaders.setLocation(comp);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        //return new ResponseEntity<>(usuario, httpHeaders, HttpStatus.SEE_OTHER);
    }

}
