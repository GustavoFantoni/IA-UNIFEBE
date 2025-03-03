package Aula04;

public class Main {
    public static void main(String[] args) {

        int[] estadoInicial = converterStringParaArray("451203786");

        long inicio = System.currentTimeMillis();

        Aula04.aStar.aStar(estadoInicial);

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
