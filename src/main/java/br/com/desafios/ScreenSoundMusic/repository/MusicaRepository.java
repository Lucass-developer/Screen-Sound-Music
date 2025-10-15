package br.com.desafios.ScreenSoundMusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafios.ScreenSoundMusic.model.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

}
