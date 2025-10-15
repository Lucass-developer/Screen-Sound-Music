package br.com.desafios.ScreenSoundMusic.model;

public enum Tipo {
    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipo;

    private Tipo(String tipo) {
        this.tipo = tipo;
    }

    //Getters and Setters
    public String getTipo() {
        return tipo;
    }
}
