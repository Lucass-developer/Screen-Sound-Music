package br.com.desafios.ScreenSoundMusic.main;

import java.util.Scanner;

import br.com.desafios.ScreenSoundMusic.repository.AlbumRepository;
import br.com.desafios.ScreenSoundMusic.repository.ArtistaRepository;
import br.com.desafios.ScreenSoundMusic.repository.MusicaRepository;
import br.com.desafios.ScreenSoundMusic.service.AlbumServices;
import br.com.desafios.ScreenSoundMusic.service.ArtistaServices;
import br.com.desafios.ScreenSoundMusic.service.MusicaServices;

public class Main {
    // Services
    Scanner scanner = new Scanner(System.in);
    private final ArtistaServices artistaServices;
    private final MusicaServices musicaServices;
    private final AlbumServices albumServices;

    // Constructors
    public Main(ArtistaRepository artistaRepository, MusicaRepository musicaRepository,
            AlbumRepository albumRepository) {
        this.artistaServices = new ArtistaServices(artistaRepository);
        this.musicaServices = new MusicaServices(musicaRepository, artistaRepository, albumRepository);
        this.albumServices = new AlbumServices(albumRepository);
    }

    // Public Methods
    public void menu() {
        int opcao;
        System.out.println("\n--- Bem-vindo ao Screen Sound Music! ---");
        do {
            System.out.println("""
                    -+- Menu Principal -+-
                    1 - Cadastrar Artista
                    2 - Cadastrar Album
                    3 - Cadastrar Música
                    4 - Listar Músicas
                    5 - Listar Artistas
                    6 - Listar Albums
                    7 - Buscar informações de um Artista
                    0 - Sair
                    """);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> artistaServices.cadastrarArtista(scanner);
                case 2 -> albumServices.cadastrarAlbum(scanner);
                case 3 -> musicaServices.cadastrarMusica(scanner);
                case 4 -> musicaServices.listarMusicas();
                case 5 -> artistaServices.listarArtistas();
                case 6 -> albumServices.listarAlbums();
                case 7 -> artistaServices.buscarInformacoesArtista(scanner);
                case 0 -> System.out.println("Saindo...\n");
                default -> System.out.println("Opçao invalida!\n");
            }
        } while (opcao != 0);
        scanner.close();
    }
}
