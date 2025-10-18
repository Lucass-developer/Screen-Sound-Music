package br.com.desafios.ScreenSoundMusic.service;

import java.util.List;
import java.util.Scanner;

import br.com.desafios.ScreenSoundMusic.model.Album;
import br.com.desafios.ScreenSoundMusic.model.Artista;
import br.com.desafios.ScreenSoundMusic.repository.AlbumRepository;

public class AlbumServices {
    private final AlbumRepository albumRepository;

    // Constructors
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

    public Album selecionarAlbum(Artista artista, Scanner scanner) {
        int contador = 0;
        while (true) {
            System.out.println("Lista de Album (0 Para Adicionar um Novo):");
            for (Album a : artista.getAlbums()) {
                System.out.println(contador++ + " - " + a.getNome());
            }
            System.out.println("Digite o número do Album:");
            int numeroAlbum = scanner.nextInt();
            scanner.nextLine();
            if (numeroAlbum == 0 ) {
                cadastrarAlbum(scanner);
            } 
            if (numeroAlbum <= artista.getAlbums().size() && numeroAlbum > 0) {
                return artista.getAlbums().get(numeroAlbum);
            } else {
                System.out.println("Album não encontrado!\n");
            }
        }
    }

    // Private Methods
    private List<Album> listaDeAlbums() {
        return albumRepository.findAll();
    }

    private Boolean existeAlbum(String nomeAlbum) {
        return albumRepository.findAll().stream().anyMatch(album -> album.getNome().equals(nomeAlbum));
    }
}
