package com.unadopcion.unadopcion.pojo;

public class DenunciaPOJO {

    private String nombreUsuario;
    private int usuarioId;
    private int animalId;
    private String denunTipo;
    private String denunDescrip;
    private String detalles;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getDenunTipo() {
        return denunTipo;
    }

    public void setDenunTipo(String denunTipo) {
        this.denunTipo = denunTipo;
    }

    public String getDenunDescrip() {
        return denunDescrip;
    }

    public void setDenunDescrip(String denunDescrip) {
        this.denunDescrip = denunDescrip;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

}
