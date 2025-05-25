package org.example.main;

public class Main {
    public static void main(String[] args) {
        /**
         * Cria a instância menu
         */
        MenuInterativo menu = new MenuInterativo();

        menu.createTestObjects();

        menu.menu();
    }
}
