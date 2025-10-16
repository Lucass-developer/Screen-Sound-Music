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
    @ManyToOne
    private Album album;
    @ManyToOne
    private Artista artista;

    //Constructors
    public Musica(String nome, Album album, LocalDate dataLancamento, Artista artista) {
        this.nome = nome;
        this.album = album;
        this.artista = artista;
    }

    public Musica() {}

    @Override
    public String toString() {
        return "Musica: " + nome +  " - Album: " + album.getNome() + "(" + album.getAnoLancamento() + ") |" + artista.getNome() ;
    }

    //Getters and Setters
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

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
    
}
