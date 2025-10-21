package br.com.desafios.ScreenSoundMusic.service;

import java.util.Comparator;
import java.util.List;

import br.com.desafios.ScreenSoundMusic.model.Album;
import br.com.desafios.ScreenSoundMusic.model.Artista;
import br.com.desafios.ScreenSoundMusic.repository.AlbumRepository;

public class AlbumServices {
    // Services
    private final InputValidator inputValidator;
    private final AlbumRepository albumRepository;
    private final ArtistaServices artistaServices;

    // Constructors
    public AlbumServices(AlbumRepository albumRepository, ArtistaServices artistaServices,
            InputValidator inputValidator) {
        this.albumRepository = albumRepository;
        this.artistaServices = artistaServices;
        this.inputValidator = inputValidator;
    }

    // Public Methods
    public void listarAlbums() {
        System.out.println("--- Albums ---");
        listaDeAlbums().stream()
                .sorted(Comparator.comparing(Album::getArtista, Comparator.comparing(Artista::getNome)))
                .forEach(System.out::println);
    }

    public void cadastrarAlbum(Artista artista) {
        boolean resposta;
        do {
            System.out.println("--- Cadastro de Album ---");
            if (artista == null) {
                if (inputValidator.confirmar("Deseja Adionar um novo artista? (S/N)")) {
                    artistaServices.cadastrarArtista(); 
                } else {
                    artista = artistaServices.selecionarArtista();
                }
            }
            String nomeAlbum = inputValidator.lerTextoVazio("Nome do Album: ");
            if (existeAlbum(nomeAlbum)) {
                System.out.println("Album já cadastrado!");
                return;
            }
            Integer anoDeLancamento = inputValidator.lerInteiroComLimite("Ano de Lançamento:", 1900, 2025);
            salvarAlbum(nomeAlbum, artista, anoDeLancamento);

            resposta = inputValidator.confirmar("Deseja Adionar outro album? (S/N)");

        } while (resposta);
    }

    public Album selecionarAlbum(Artista artista) {
        int contador = 1;
        while (true) {
            List<Album> albums = artistaServices.listaDeAlbums(artista);
            System.out.println("Lista de Album:");
            for (Album a : albums) {
                System.out.println(contador + " - " + a.getNome());
                contador++;
            }
            int numeroAlbum = inputValidator.lerInteiroComLimite("Digite o número do Album (0 Para Adicionar um Novo)",
                    0, albums.size());
            if (numeroAlbum == 0) {
                cadastrarAlbum(artista);
                contador = 1;
            } else {
                return albums.get(numeroAlbum - 1);
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
