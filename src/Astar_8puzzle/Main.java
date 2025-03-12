package Astar_8puzzle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Informe a entrada: ex: 123456780... ");
        int[] estadoInicial = converterStringParaArray(input.nextLine());

        System.out.println("Informe a saída: ex: 123456780... ");
        int[] estadoFinal = converterStringParaArray(input.nextLine());

        long inicio = System.currentTimeMillis();

        Astar_8puzzle.aStar.aStar(estadoInicial, estadoFinal);

        long fim = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (fim - inicio) + "ms");
    }

    public static int[] converterStringParaArray(String entrada) {
        int[] tabuleiro = new int[9];
        for (int i = 0; i < entrada.length(); i++) {
            tabuleiro[i] = Character.getNumericValue(entrada.charAt(i));
        }
        return tabuleiro;
    }
}
