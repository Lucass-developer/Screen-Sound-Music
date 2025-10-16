package br.com.desafios.ScreenSoundMusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafios.ScreenSoundMusic.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
