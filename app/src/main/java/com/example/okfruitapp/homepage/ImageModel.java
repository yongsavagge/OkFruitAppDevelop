package com.example.okfruitapp.models;

import okhttp3.RequestBody;

public class ImageModel {
    private String imagen;
    private String tipoFruta;

    public ImageModel(String tipoFruta, String imagen) {
        this.imagen = imagen;
        this.tipoFruta = tipoFruta;
    }

    public String getTipo() {
        return tipoFruta;
    }

    public void setTipo(String tipoFruta) {
        this.tipoFruta = tipoFruta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
