package br.com.desafios.ScreenSoundMusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafios.ScreenSoundMusic.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Artista findByNomeILIKE(String nomeArtista);

}
