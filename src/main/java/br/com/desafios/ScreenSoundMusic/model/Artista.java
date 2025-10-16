package br.com.desafios.ScreenSoundMusic.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Album> albums;

    // Constructors
    public Artista(String nome, Tipo tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Artista() {}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Musica m : musicas) {
            stringBuilder
            .append("+ ")
            .append(m.getNome())
            .append(" - ")
            .append(m.getAlbum().getNome())
            .append("(")
            .append(m.getAlbum().getAnoLancamento())
            .append(")")
            .append("\n");
        }
        return "Artista: " + nome + " - Tipo: " + tipo + "\n" + stringBuilder.toString();
    }

    // Getters and Setters
    public List<Musica> getMusicas() {
        if (musicas == null) {
            System.out.println("O artista não possui músicas cadastradas.");
        }

        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
