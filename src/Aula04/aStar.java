package Aula04;

import java.util.*;

public class aStar {
    public static void aStar(int[] arrayPuzzle) {
        int[] objetivo = {1, 2, 3, 4, 5, 6, 7, 8, 0};


        PriorityQueue<Map.Entry<Integer, Integer>> fila = new PriorityQueue<>(Map.Entry.comparingByKey());

        do {
            ArrayList<Integer> MovimentosPossiveis = verificaMovimentos(arrayPuzzle);


            for (int i = 0; i < MovimentosPossiveis.size(); i++) {
                int indiceZero = indexZero(arrayPuzzle);
                int indiceMovimento = MovimentosPossiveis.get(i);

                int numeroTrocado = arrayPuzzle[indiceMovimento];
                arrayPuzzle[indiceMovimento] = 0;
                arrayPuzzle[indiceZero] = numeroTrocado;

                int heuristica = HeuristicaManhattan.calculaHeuristica(arrayPuzzle);
                fila.add(new AbstractMap.SimpleEntry<>(heuristica, numeroTrocado));

                // Desfaz a troca e testa o próximo
                arrayPuzzle[indiceZero] = 0;
                arrayPuzzle[indiceMovimento] = numeroTrocado;

            }
        } while (arrayPuzzle != objetivo);



    }

    public static int indexZero(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<Integer> verificaMovimentos(int[] arrayPuzzle) {
        ArrayList<Integer> MovimentosPossiveis = new ArrayList<>();
        int indiceZero = indexZero(arrayPuzzle);

        for (int i = 0; i < arrayPuzzle.length; i++) {
            int linhaItem = i / 3, colunaItem = i % 3;
            int linhaZero = indiceZero / 3, colunaZero = indiceZero % 3;

            if (Math.abs(linhaItem - linhaZero) + Math.abs(colunaItem - colunaZero) == 1) {
                MovimentosPossiveis.add(i);
            }
        }

        return MovimentosPossiveis;
    }

    public static PriorityQueue<Map.Entry<Integer, Integer>> retornaProxMov() {
        PriorityQueue<Map.Entry<Integer, Integer>> fila = new PriorityQueue<Map.Entry<Integer, Integer>>();

        return fila;
    }
}


