package br.com.desafios.ScreenSoundMusic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.desafios.ScreenSoundMusic.model.Album;
import br.com.desafios.ScreenSoundMusic.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    @Query("SELECT a FROM Artista a WHERE a.nome ILIKE %:nomeArtista%")
    Artista findByNomeILIKE(String nomeArtista);

    @Query("SELECT a.albums FROM Artista a WHERE a = :artista")
    List<Album> findAlbumsByArtista(Artista artista);

}
