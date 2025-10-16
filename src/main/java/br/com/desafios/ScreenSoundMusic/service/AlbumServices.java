package br.com.desafios.ScreenSoundMusic.service;

import java.util.List;
import java.util.Scanner;

import br.com.desafios.ScreenSoundMusic.model.Album;
import br.com.desafios.ScreenSoundMusic.repository.AlbumRepository;

public class AlbumServices {
    private final AlbumRepository albumRepository;

    //Constructors
    public AlbumServices(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    // Public Methods
    public void listarAlbums() {
       System.out.println("--- Albums ---");
       listaDeAlbums().forEach(System.out::println);
    }

    public void cadastrarAlbum(Scanner scanner) {
       
    }

    // Private Methods
    private List<Album> listaDeAlbums() {
        return albumRepository.findAll();
    }

    private Boolean existeAlbum(String nomeAlbum) {
        return albumRepository.findAll().stream().anyMatch(album -> album.getNome().equals(nomeAlbum));
    }
}
