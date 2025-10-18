package br.com.desafios.ScreenSoundMusic.service;

import java.util.List;
import java.util.Scanner;

import br.com.desafios.ScreenSoundMusic.model.Musica;
import br.com.desafios.ScreenSoundMusic.repository.AlbumRepository;
import br.com.desafios.ScreenSoundMusic.repository.ArtistaRepository;
import br.com.desafios.ScreenSoundMusic.repository.MusicaRepository;

public class MusicaServices {
    // Repository
    private final MusicaRepository musicaRepository;
    private final ArtistaServices artistaServices;
    private final AlbumServices albumServices;

    // Constructors
    public MusicaServices(MusicaRepository musicaRepository, ArtistaRepository artistaRepository, AlbumRepository albumRepository) {
        this.musicaRepository = musicaRepository;
        this.artistaServices = new ArtistaServices(artistaRepository);
        this.albumServices = new AlbumServices(albumRepository);
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
        return listaDeMusicas().stream().anyMatch(musica -> musica.getNome().equalsIgnoreCase(nomeMusica));
    }
    
}
