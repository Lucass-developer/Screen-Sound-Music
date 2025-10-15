package br.com.desafios.ScreenSoundMusic.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String album;
    private LocalDate dataLancamento;

    @ManyToOne
    private Artista artista;

    //Constructors
    public Musica(String nome, String album, LocalDate dataLancamento) {
        this.nome = nome;
        this.album = album;
        this.dataLancamento = dataLancamento;
    }

    public Musica() {}

    @Override
    public String toString() {
        return "Musica: " + nome +  " - Album: " + album + "(" + artista.getNome() + ") - Data de Lançamento: " + dataLancamento;
    }

    //Getters and Setters
    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    
}
