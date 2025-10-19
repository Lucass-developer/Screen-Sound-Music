package br.com.desafios.ScreenSoundMusic.service;

import java.util.List;
import java.util.Scanner;

import br.com.desafios.ScreenSoundMusic.model.Album;
import br.com.desafios.ScreenSoundMusic.model.Artista;
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
        this.albumServices = new AlbumServices(albumRepository, artistaServices);
    }

    // Public Methods
    public void cadastrarMusica(Scanner scanner) {
        String resposta;
        do {
            System.out.println("--- Cadastro de Música ---");
            Artista artista = artistaServices.selecionarArtista(scanner);
            Album album = albumServices.selecionarAlbum(artista, scanner);
            System.out.print("Nome da Música: ");
            String nomeMusica = scanner.nextLine();
            if (existeMusica(nomeMusica)) {
                System.out.println("Música já cadastrada!");
                return;
            }
            salvarMusica(nomeMusica, album, artista);
            System.out.println("Deseja Adionar outra música? (S/N)");
            resposta = scanner.nextLine();
        } while (!resposta.equalsIgnoreCase("N"));
    }

    public void listarMusicas() {
        System.out.println("--- Músicas ---");
        musicaRepository.findAll().forEach(System.out::println);
    }

    // Private Methods
    private List<Musica> listaDeMusicas() {
        return musicaRepository.findAll();
    }

    private Boolean existeMusica(String nomeMusica) {
        return listaDeMusicas().stream().anyMatch(musica -> musica.getNome().equalsIgnoreCase(nomeMusica));
    }
    
    private void salvarMusica(String nomeMusica, Album album, Artista artista) {
        Musica musica = new Musica(nomeMusica, album, artista);
        musicaRepository.save(musica);
        System.out.println("Música " + nomeMusica + " salva com sucesso!");
    }
}
