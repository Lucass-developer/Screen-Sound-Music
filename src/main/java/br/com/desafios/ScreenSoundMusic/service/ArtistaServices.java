package br.com.desafios.ScreenSoundMusic.service;

import java.util.List;
import java.util.Scanner;

import br.com.desafios.ScreenSoundMusic.model.Album;
import br.com.desafios.ScreenSoundMusic.model.Artista;
import br.com.desafios.ScreenSoundMusic.model.Tipo;
import br.com.desafios.ScreenSoundMusic.repository.ArtistaRepository;

public class ArtistaServices {
    // Repository
    private final ArtistaRepository artistaRepository;

    // Constructors
    public ArtistaServices(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }
  
    // Public Methods
    public void cadastrarArtista(Scanner scanner) {
        String resposta;
        do {
            System.out.println("--- Cadastro de Artista ---");
        System.out.print("Nome do Artista: ");
        String nomeArtista = scanner.nextLine();
        if (existeArtista(nomeArtista)) {
            System.out.println("Artista já cadastrado!");
            return;
        }
        Tipo tipo = solicitarTipoArtista(scanner);
        salvarArtista(nomeArtista, tipo);
        System.out.println("Deseja Adionar outro artista? (S/N)");
        resposta = scanner.nextLine();
        } while (!resposta.equalsIgnoreCase("N"));
    }

    public void listarArtistas() {
        System.out.println("--- Artistas ---");
        listaDeArtistas().forEach(System.out::println);
    }

    public Artista selecionarArtista(Scanner scanner) {
        while (true) {
            System.out.println("Lista de Artistas:");
            listaDeArtistas().forEach(a -> System.out.println(a.getNome()));
            System.out.println("Digite o nome do Artista:");
            String nomeArtista = scanner.nextLine().toLowerCase();
            try {
                Artista artista = artistaRepository.findByNomeILIKE(nomeArtista);
                if (artista != null) {
                    return artista;
                } else {
                    System.out.println("Artista não encontrado!");
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar artista: " + e.getMessage());
            }
        }
    }

    public List<Album> listaDeAlbums(Artista artista) {
        return artistaRepository.findAlbumsByArtista(artista);
    }

    public void buscarInformacoesArtista(Scanner scanner) {

    }

    // Private Methods
    private List<Artista> listaDeArtistas() {
        return artistaRepository.findAll();
    }

    private Boolean existeArtista(String nomeArtista) {
        return listaDeArtistas().stream()
                .anyMatch(artista -> artista.getNome().toLowerCase().equals(nomeArtista.toLowerCase()));
    }

    private void salvarArtista(String nome, Tipo tipo) {
       try {
            Artista artista = new Artista(nome, tipo);
            artistaRepository.save(artista);
            System.out.println("Artista cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar artista: " + e.getMessage());
        }
    }

    private Tipo solicitarTipoArtista(Scanner scanner) {
        Tipo tipo;
        do {
            System.out.println("Selecione o tipo do Artista (Solo, Banda, Dupla):");
            String opcaoTipoArtista = scanner.nextLine().toUpperCase();
            tipo = switch (opcaoTipoArtista) {
                case "SOLO" -> Tipo.SOLO;
                case "BANDA" -> Tipo.BANDA;
                case "DUPLA" -> Tipo.DUPLA;
                default -> null;
            };
            if (tipo == null) {
                System.out.println("Opção inválida!");
            }
        } while (tipo == null);
        return tipo;
    }
}
