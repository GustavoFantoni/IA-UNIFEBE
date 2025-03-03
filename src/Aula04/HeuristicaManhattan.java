package Aula04;

public class HeuristicaManhattan {
    private int[] arrayPuzzle;

    HeuristicaManhattan(int[] array) {
        this.arrayPuzzle = array.clone(); // garante que o array original não seja modificado
    }

    public int[] getArrayPuzzle() {
        return arrayPuzzle;
    }

    public static int calculaHeuristica(int[] array) {
        int heuristica = 0;
       for (int i = 0; i < array.length; i++) {
           if (array[i] != 0) {
               int valorIndice = array[i] -1; // -1 é pra descobrir qual a posição correta para o valor atual
               int linhaAtual = i / 3, colunaAtual = i % 3;
               int linhaObj = valorIndice / 3, colunaObj = valorIndice % 3;
               heuristica += Math.abs(linhaAtual - linhaObj) + Math.abs(colunaAtual - colunaObj);
           }
       }
        return heuristica;
    }
}
