package com.unadopcion.unadopcion.modelo;


import javax.persistence.*;


@Entity
@Table( name = "logeo")
public class Logeo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int logeoId;
    private String logeoNombre;
    private String logeoContra;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;


    public Logeo() {
    }

    public int getLogeoId() {
        return logeoId;
    }

    public void setLogeoId(int logeoId) {
        this.logeoId = logeoId;
    }

    public String getLogeoNombre() {
        return logeoNombre;
    }

    public void setLogeoNombre(String logeoNombre) {
        this.logeoNombre = logeoNombre;
    }

    public String getLogeoContra() {
        return logeoContra;
    }

    public void setLogeoContra(String logeoContrasena) {
        this.logeoContra = logeoContrasena;
    }
}
