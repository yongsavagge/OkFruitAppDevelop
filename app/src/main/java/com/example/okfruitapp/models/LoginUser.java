package com.example.okfruitapp.models;

public class LoginUser {
    String nombre;
    String contrasena;
    public String getNombre() {
        return nombre;
    }
    public LoginUser(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
