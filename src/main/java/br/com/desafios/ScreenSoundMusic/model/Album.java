package br.com.desafios.ScreenSoundMusic.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private LocalDate anoLancamento;
    @ManyToOne
    private Artista artista;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas;

    //Cosntructors
    public Album(String nome, LocalDate anoDeLancamento, Artista artista) {
        this.nome = nome;
        this.anoLancamento = anoDeLancamento;
        this.artista = artista;
    }

    public Album() {}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Musica m : musicas) {
            stringBuilder
            .append("+ ")
            .append(m.getNome())
            .append(" - ")
            .append(m.getArtista().getNome())
            .append("\n");
        }
        return "Album: " + nome + " - Ano de Lançamento: " + anoLancamento + "\n" + stringBuilder.toString();
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public Artista getArtista() {
        return artista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }
}
