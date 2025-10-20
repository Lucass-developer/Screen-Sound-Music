package br.com.desafios.ScreenSoundMusic.service;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import br.com.desafios.ScreenSoundMusic.model.Album;
import br.com.desafios.ScreenSoundMusic.model.Artista;
import br.com.desafios.ScreenSoundMusic.repository.AlbumRepository;

public class AlbumServices {
    private final AlbumRepository albumRepository;
    private final ArtistaServices artistaServices;

    // Constructors
    public AlbumServices(AlbumRepository albumRepository, ArtistaServices artistaServices) {
        this.albumRepository = albumRepository;
        this.artistaServices = artistaServices;
    }

    // Public Methods
    public void listarAlbums() {
        System.out.println("--- Albums ---");
        listaDeAlbums().stream()
                .sorted(Comparator.comparing(Album::getArtista, Comparator.comparing(Artista::getNome)))
                .forEach(System.out::println);
    }

    public void cadastrarAlbum(Scanner scanner, Artista artista) {
        String resposta;
        do {
            System.out.println("--- Cadastro de Album ---");
            String novoArtista;
            if (artista == null) {
                do {
                    System.out.println("Deseja Adionar um novo artista? (S/N)");
                    novoArtista = scanner.nextLine();
                    if (novoArtista.equalsIgnoreCase("S")) {
                        artistaServices.cadastrarArtista(scanner);
                    }
                } while (!novoArtista.equalsIgnoreCase("N"));
                artista = artistaServices.selecionarArtista(scanner);
            }
            System.out.print("Nome do Album: ");
            String nomeAlbum = scanner.nextLine();
            if (existeAlbum(nomeAlbum)) {
                System.out.println("Album já cadastrado!");
                return;
            }
            System.out.print("Ano de Lançamento: ");
            Integer anoDeLancamento = scanner.nextInt();
            scanner.nextLine();
            salvarAlbum(nomeAlbum, artista, anoDeLancamento);

            System.out.println("Deseja Adionar outro album? (S/N)");
            resposta = scanner.nextLine();

        } while (!resposta.equalsIgnoreCase("N"));
    }

    public Album selecionarAlbum(Artista artista, Scanner scanner) {
        int contador = 1;
        while (true) {
            List<Album> albums = artistaServices.listaDeAlbums(artista);
            System.out.println("Lista de Album:");
            for (Album a : albums) {
                System.out.println(contador + " - " + a.getNome());
                contador++;
            }
            System.out.println("Digite o número do Album (0 Para Adicionar um Novo):");
            int numeroAlbum = scanner.nextInt();
            scanner.nextLine();

            if (numeroAlbum <= albums.size() && numeroAlbum >= 0) {
                if (numeroAlbum == 0) {
                    cadastrarAlbum(scanner, artista);
                    contador = 1;
                } else {
                    return albums.get(numeroAlbum - 1);
                }
            } else {
                System.out.println("Album não encontrado!\n");
                contador = 1;
            }
        }
    }

    // Private Methods
    private List<Album> listaDeAlbums() {
        return albumRepository.findAll();
    }

    private void salvarAlbum(String nomeAlbum, Artista artista, Integer anoDeLancamento) {
        try {
            Album album = new Album(nomeAlbum, anoDeLancamento, artista);
            albumRepository.save(album);
            System.out.println("Album salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar o album: " + e.getMessage());
        }

    }

    private Boolean existeAlbum(String nomeAlbum) {
        return albumRepository.findAll().stream().anyMatch(album -> album.getNome().equals(nomeAlbum));
    }
}
