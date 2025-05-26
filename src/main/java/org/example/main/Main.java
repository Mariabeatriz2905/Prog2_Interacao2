package org.example.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * Cria a instância menu
         */
        MenuInterativo menu = new MenuInterativo();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pretende a criação automática de um conjunto de objetos predefinidos no código ou a criação de objetos a partir de informação carregada a partir de um ficheiro?");
        System.out.println("1. Objetos predefinidos no código");
        System.out.println("2. Objetos a partir de um ficheiro");
        int escolha = scanner.nextInt();
        while (escolha != 1 && escolha != 2){
            System.out.println("Numero inválido. Introduza uma opção válida");
            escolha = scanner.nextInt();
        }

        switch (escolha){
            case 1:
                menu.createTestObjects();
                break;
            case 2:
                menu.ficheirosTexto();
                break;
        }

        menu.menu();
    }
}
