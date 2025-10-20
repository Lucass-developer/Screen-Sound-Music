package br.com.desafios.ScreenSoundMusic.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaComIA {
    private final String API_URL = "https://routellm.abacus.ai/v1/chat/completions";
    
    public String informacoesArtista(String nomeArtista) throws Exception {
        String jsonBody = """
                {
                  "model": "gpt-4.1-nano",
                  "prompt": "faça um resumo simples sobre o artista %s"
                }
                """.formatted(nomeArtista);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + System.getenv("LLM_KEY"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());   
        return response.body();  
    }
}
