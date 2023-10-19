package com.example.okfruitapp.Module;



// Clase en la cual se destina cada campo del user, en este caso
// esta relacionado con la Tabla USER
public class Usuario {
    private String idUsuario;
    private String nombre;
    private  String apellidos;
    private String correo;
    private String numeroCelular;
    private String numeroTelefonico;
    private String numeroDocumento;
    private String Tipo_Documento_idTipo_Documento;
    private  String password;
    private  String creacion;


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipo_Documento_idTipo_Documento() {
        return Tipo_Documento_idTipo_Documento;
    }

    public void setTipo_Documento_idTipo_Documento(String tipo_Documento_idTipo_Documento) {
        Tipo_Documento_idTipo_Documento = tipo_Documento_idTipo_Documento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreacion() {
        return creacion;
    }

    public void setCreacion(String creacion) {
        this.creacion = creacion;
    }


}
