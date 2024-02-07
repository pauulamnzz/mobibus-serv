package net.ausiasmarch.mobibus.entity;

public class ParadaEmtEntity {
    private String numParada;
    private String nomParada;
    private String nomLinea;
    private String tiempo;
    private String numLinea;

    public ParadaEmtEntity(String numParada, String nomParada, String nomLinea,
     String tiempo, String numLinea) {
        this.numParada = numParada;
        this.nomParada = nomParada;
        this.nomLinea = nomLinea;
        this.tiempo = tiempo;
        this.numLinea = numLinea;
    }

    public ParadaEmtEntity(String numParada, String nomParada) {
        this.numParada = numParada;
        this.nomParada = nomParada;
    }

 
    public String getNomParada() {
        return nomParada;
    }

    public void setNomParada(String nomParada) {
        this.nomParada = nomParada;
    }

    public String getNomLinea() {
        return nomLinea;
    }

    public void setNomLinea(String nomLinea) {
        this.nomLinea = nomLinea;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getNumParada() {
        return numParada;
    }

    public void setNumParada(String numParada) {
        this.numParada = numParada;
    }

    public String getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(String numLinea) {
        this.numLinea = numLinea;
    }
}

