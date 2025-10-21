package br.com.desafios.ScreenSoundMusic.service;

import java.util.Comparator;
import java.util.List;

import br.com.desafios.ScreenSoundMusic.model.Album;
import br.com.desafios.ScreenSoundMusic.model.Artista;
import br.com.desafios.ScreenSoundMusic.model.Tipo;
import br.com.desafios.ScreenSoundMusic.repository.ArtistaRepository;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class ArtistaServices {
    // Services
    ConsultaComIA consultaComIA = new ConsultaComIA();
    InputValidator inputValidator;

    // Repository
    private final ArtistaRepository artistaRepository;

    // Constructors
    public ArtistaServices(InputValidator inputValidator, ArtistaRepository artistaRepository) {
        this.inputValidator = inputValidator;
        this.artistaRepository = artistaRepository;
    }

    // Public Methods
    public void cadastrarArtista() {
        boolean resposta;
        do {
            System.out.println("--- Cadastro de Artista ---");
            String nomeArtista = inputValidator.lerTextoVazio("Nome do Artista: ");
            if (existeArtista(nomeArtista)) {
                System.out.println("Artista já cadastrado!");
                return;
            }
            Tipo tipo = solicitarTipoArtista();
            salvarArtista(nomeArtista, tipo);
            resposta = inputValidator.confirmar("Deseja Adionar outro artista? (S/N)");
        } while (resposta);
    }

    public void listarArtistas() {
        System.out.println("--- Artistas ---");
        listaDeArtistas().stream()
                .sorted(Comparator.comparing(Artista::getNome))
                .forEach(System.out::println);
    }

    public Artista selecionarArtista() {
        while (true) {
            System.out.println("Lista de Artistas:");
            listaDeArtistas().forEach(a -> System.out.println(a.getNome()));
            String nomeArtista = inputValidator.lerTextoVazio("Digite o nome do Artista:");
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

    public void buscarInformacoesArtista() {
        System.out.println("--- Busca de Informaçoes do Artista ---");
        String nomeArtista = inputValidator.lerTextoVazio("Nome do Artista: ");
        try {
            String jsonInformacoes = consultaComIA.informacoesArtista(nomeArtista);
            String informacoes = converteInformacoes(jsonInformacoes, nomeArtista);
            System.out.println(informacoes);

        } catch (Exception e) {
            System.out.println("Erro ao buscar informações do artista: " + e.getMessage());
        }
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

    private Tipo solicitarTipoArtista() {
        Tipo tipo;
        do {
            String opcaoTipoArtista = inputValidator.lerTextoVazio("Selecione o tipo do Artista (Solo, Banda, Dupla):").toUpperCase();
            tipo = switch (opcaoTipoArtista) {
                case "SOLO" -> Tipo.SOLO;
                case "BANDA" -> Tipo.BANDA;
                case "DUPLA" -> Tipo.DUPLA;
                default -> null;
            };
            if (tipo == null) System.out.println("Opção inválida!");
        } while (tipo == null);
        return tipo;
    }

    private String converteInformacoes(String jsonInformacoes, String nome) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(jsonInformacoes);
            String content = rootNode.get("choices")
                    .get(0)
                    .get("message")
                    .get("content")
                    .toString();
            return "Informaçoes sobre " + nome + ":\n" + content.replaceAll("\"", "") + "\n";
        } catch (Exception e) {
            System.out.println("Erro ao converter informações do artista: " + e.getMessage());
            return null;
        }
    }
}
