package com.dprogramacionjg.historialtecnologico.model;

public class Persona {

    private String Uid;
    private String Nombre;
    private String Apellidos;
    private String NumeroTelefonico;
    private String Correo;

    public Persona() {
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getNumeroTelefonico() {
        return NumeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        NumeroTelefonico = numeroTelefonico;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "Uid='" + Uid + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", NumeroTelefonico='" + NumeroTelefonico + '\'' +
                ", Correo='" + Correo + '\'' +
                '}';
    }


}
