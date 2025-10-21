package br.com.desafios.ScreenSoundMusic.service;

import java.util.Comparator;
import java.util.List;

import br.com.desafios.ScreenSoundMusic.model.Album;
import br.com.desafios.ScreenSoundMusic.model.Artista;
import br.com.desafios.ScreenSoundMusic.model.Musica;
import br.com.desafios.ScreenSoundMusic.repository.AlbumRepository;
import br.com.desafios.ScreenSoundMusic.repository.ArtistaRepository;
import br.com.desafios.ScreenSoundMusic.repository.MusicaRepository;

public class MusicaServices {
    // Services
    private final InputValidator inputValidator;
    private final MusicaRepository musicaRepository;
    private final ArtistaServices artistaServices;
    private final AlbumServices albumServices;

    // Constructors
    public MusicaServices(InputValidator inputValidator, MusicaRepository musicaRepository, ArtistaRepository artistaRepository, AlbumRepository albumRepository, ArtistaServices artistaServices, AlbumServices albumServices) {
        this.inputValidator = inputValidator;
        this.musicaRepository = musicaRepository;
        this.artistaServices = artistaServices;
        this.albumServices = albumServices;
    }

    // Public Methods
    public void cadastrarMusica() {
        boolean resposta;
        do {
            System.out.println("--- Cadastro de Música ---");
            Artista artista = artistaServices.selecionarArtista();
            Album album = albumServices.selecionarAlbum(artista);
            String nomeMusica = inputValidator.lerTextoVazio("Nome da Música: ");
            if (existeMusica(nomeMusica)) {
                System.out.println("Música já cadastrada!");
                return;
            }
            salvarMusica(nomeMusica, album, artista);
            resposta = inputValidator.confirmar("Deseja Adionar outra música? (S/N)");
        } while (resposta);
    }

    public void listarMusicas() {
        System.out.println("--- Músicas ---");
        musicaRepository.findAll().stream()
        .sorted(Comparator.comparing(Musica::getArtista, Comparator.comparing(Artista::getNome)))
        .forEach(System.out::println);
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
