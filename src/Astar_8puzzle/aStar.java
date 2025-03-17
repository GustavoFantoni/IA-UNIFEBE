package Astar_8puzzle;

import java.util.*;

public class aStar {

    static PriorityQueue<Estado> fila = new PriorityQueue<Estado>();


    public static void aStar(int[] arrayPuzzle, int[] obj) {

        fila.add(new Estado(arrayPuzzle,  0, HeuristicaManhattan.calculaHeuristica(arrayPuzzle), null, "Inicio"));

        while (!fila.isEmpty()) {
            Estado estado = fila.poll();

            if (objAlcancado(estado.tabuleiro, obj)) {
                System.out.println("Objetivo alcancado!");
                reconstruirCaminho(estado);
                return;
            }

            executaMovimento(estado);

        }

    }

    public static void reconstruirCaminho(Estado estadoFinal) {
        LinkedList<Map.Entry<int[], String>> caminho = new LinkedList<>();

        // Percorre os estados pais até o estado inicial e adiciona no início da lista
        Estado atual = estadoFinal;
        while (atual != null) {
            caminho.addFirst(new AbstractMap.SimpleEntry<>(atual.tabuleiro, atual.movimento));
            atual = atual.pai;
        }

        // Exibe o caminho
        System.out.println("Caminho até a solução:");
        for (Map.Entry<int[], String> passo : caminho) {
            System.out.println("Movimento: " + passo.getValue());
            imprimirTabuleiro(passo.getKey());
        }
    }


    public static void imprimirTabuleiro(int[] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            if (i % 3 == 0) System.out.println(); // Quebra de linha a cada 3 elementos
            System.out.print(tabuleiro[i] + " ");
        }
        System.out.println("\n");
    }


    public static boolean objAlcancado(int[] arrayPuzzle, int[] objetivo) {
        return Arrays.equals(arrayPuzzle, objetivo);
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

    static HashSet<String> jaVisitados = new HashSet<>();

    public static void executaMovimento(Estado estadoAtual) {

        int indiceZero = indexZero(estadoAtual.tabuleiro);
        ArrayList<Integer> MovimentosPossiveis = verificaMovimentos(estadoAtual.tabuleiro);

        for (int indiceMovimento : MovimentosPossiveis) {

            int[] novoEst = estadoAtual.tabuleiro.clone();

            String mov = direcaoMov(indiceZero, indiceMovimento);

            novoEst[indiceZero] = novoEst[indiceMovimento];
            novoEst[indiceMovimento] = 0;

            String estadoStr = Arrays.toString(novoEst);
            if (jaVisitados.contains(estadoStr)) {
                continue; // sai do loop
            }

            int novoG = estadoAtual.g + 1;
            int novoH = HeuristicaManhattan.calculaHeuristica(novoEst);

            Estado novoEstado = new Estado(novoEst, novoG, novoH, estadoAtual, mov);
            fila.add(novoEstado);

        }
    }
    private static String direcaoMov(int indZero, int indMov) {
        if (indMov == indZero - 1) {
            return "Esquerda";
        } else if (indMov == indZero + 1) {
            return "Direita";
        } else if (indMov == indZero - 3) {
            return "Cima";
        } else {
            return "Baixo";
        }
    }
}


