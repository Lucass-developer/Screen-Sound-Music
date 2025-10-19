package br.com.desafios.ScreenSoundMusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.desafios.ScreenSoundMusic.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query("SELECT a FROM Album a WHERE a.nome ILIKE %:nomeAlbum%")
    Album findByNomeILIKE(String nomeAlbum);

}
