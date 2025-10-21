package br.com.desafios.ScreenSoundMusic.service;

import java.util.Scanner;

public class InputValidator {
    private final Scanner scanner;

    // Constructor
    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    // Public Methods
    public int lerInteiro(String menssagem) {
        while(true) {
            System.out.println(menssagem);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }
        }
    }

    public int lerInteiroComLimite(String menssagem, int min, int max) {
        while(true) {
            int numero = lerInteiro(menssagem);
            if(numero >= min && numero <= max) return numero;
            System.out.println("Entrada inválida. Por favor, digite um número entre " + min + " e " + max + ".");
        }
    }

    public String lerTextoVazio(String menssagem) {
        while(true) {
            System.out.println(menssagem);
            String texto = scanner.nextLine();
            if(!texto.isEmpty()) return texto;
            System.out.println("Entrada inválida. Por favor, digite um texto.");
        }
    }

    public boolean confirmar(String menssagem) {
        while(true) {
            System.out.println(menssagem);
            String resposta = scanner.nextLine().trim().toUpperCase();
            if (resposta.equals("S")) return true;
            if (resposta.equals("N")) return false;
            System.out.println("Entrada inválida. Por favor, digite 'S' ou 'N'.");
        }
    }
}
