package com.unadopcion.unadopcion.modelo;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usuarioId;
    private Integer logeoId;
    private String usuarioNombre;
    private String usuarioRol;
    private double usuarioPuntos;
    private String usuarioEmail;
    private String usuarioTelefono;
    private String usuarioLugar;
    private String usuarioInfo;
    private String usuarioNombreReal;
    private String usuarioUrlFoto;
    private String usuarioGoogleId;


    public Usuario() {
    }

    public String getUsuarioGoogleId() {
        return usuarioGoogleId;
    }

    public void setUsuarioGoogleId(String googleId) {
        this.usuarioGoogleId = googleId;
    }

    public String getUsuarioUrlFoto() {
        return usuarioUrlFoto;
    }

    public void setUsuarioUrlFoto(String usuarioUrlFoto) {
        this.usuarioUrlFoto = usuarioUrlFoto;
    }

    public void setLogeoId(Integer logeoId) {
        this.logeoId = logeoId;
    }

    public String getUsuarioNombreReal() {
        return usuarioNombreReal;
    }

    public void setUsuarioNombreReal(String usuarioNombreReal) {
        this.usuarioNombreReal = usuarioNombreReal;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getLogeoId() {
        return logeoId;
    }

    public void setLogeoId(int logeoId) {
        this.logeoId = logeoId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(String usuarioRol) {
        this.usuarioRol = usuarioRol;
    }

    public double getUsuarioPuntos() {
        return usuarioPuntos;
    }

    public void setUsuarioPuntos(double usuarioPuntos) {
        this.usuarioPuntos = usuarioPuntos;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getUsuarioTelefono() {
        return usuarioTelefono;
    }

    public void setUsuarioTelefono(String usuarioTelefono) {
        this.usuarioTelefono = usuarioTelefono;
    }

    public String getUsuarioLugar() {
        return usuarioLugar;
    }

    public void setUsuarioLugar(String usuarioLugar) {
        this.usuarioLugar = usuarioLugar;
    }

    public String getUsuarioInfo() {
        return usuarioInfo;
    }

    public void setUsuarioInfo(String usuarioInfo) {
        this.usuarioInfo = usuarioInfo;
    }
}
