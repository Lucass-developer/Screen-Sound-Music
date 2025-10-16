package br.com.desafios.ScreenSoundMusic.service;

import java.util.List;
import java.util.Scanner;

import br.com.desafios.ScreenSoundMusic.model.Musica;
import br.com.desafios.ScreenSoundMusic.repository.MusicaRepository;

public class MusicaServices {
    // Repository
    private final MusicaRepository musicaRepository;

    // Constructors
    public MusicaServices(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    // Public Methods
    public void cadastrarMusica(Scanner scanner) {

    }

    public void listarMusicas() {

    }

    // Private Methods
    private List<Musica> listaDeMusicas() {
        return musicaRepository.findAll();
    }

    private Boolean existeMusica(String nomeMusica) {
        return listaDeMusicas().stream().anyMatch(musica -> musica.getNome().equals(nomeMusica));
    }
    
}
