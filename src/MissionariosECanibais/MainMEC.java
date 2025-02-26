package MissionariosECanibais;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class MainMEC {
    public static void main(String[] args) {

        int[] estado = {3,3,0};

        int[][] movimentos = {{1,0}, {2,0}, {0,1}, {0,2}, {1,1}}; // movimentos válidos

        //System.out.println("Estado inicial: " + estado[0] + ", " + estado[1] + ", " + estado[2] + "");
        tentativa(estado, movimentos);

        //testarFuncaoEhValido(); // Serve somente para visualizar todas as combinações possíveis válidas e inválidas. Não faz parte da atividade, mas fiz para debugar



    }

    // Testa todas as possíveis jogadassempre verificando se é válido
    public static void tentativa(int[] estado, int[][] movimentos) {

        // Decidi implementar busca e largura para verificar todas as possibilidades, e não repetir estados
        Queue<int[]> fila = new LinkedList<>(); // BFS - controle de estados que precisam ser explorados
        Set<String> visitados = new HashSet<>(); // evita repetir estados que ja foram, aumentando a eficiência
        Map<String, String> rastrearCaminho = new HashMap<>(); // Guarda o caminho percorrido, para no final apresentar

        // inicio colocando o estado inicial na fila
        fila.add(estado); // adiciono o estado inicial
        visitados.add(Arrays.toString(estado)); // Estado inicial
        rastrearCaminho.put(Arrays.toString(estado), null); // Estado atual não tem anterior, é o root

        while (!fila.isEmpty()) { // Enquanto existem estados possíveis, o loop roda
            int[] atual = fila.poll(); // removo o próximo estado e faço a análise até encontrar o objetivo

            if (atual[0] == 0 && atual[1] == 0 && atual[2] == 1) {
                System.out.println("Objetivo alcançado! \n Array final: ");
                mostrarCaminho(rastrearCaminho, atual); // Alteração: passe 'atual' em vez de 'estado'
                break;
            }


            for (int[] tent : movimentos) {
                int[] novoEstado = mover(atual, tent);
                if (novoEstado != null && ehValido(novoEstado)) {
                    if(!visitados.contains(Arrays.toString(novoEstado))) {
                        // se o estado é válido e ele não é um estado repetido, ele é adicionado
                        fila.add(novoEstado); // passa a ser um item que futuramente será analisado
                        visitados.add(Arrays.toString(novoEstado));
                        rastrearCaminho.put(Arrays.toString(novoEstado), Arrays.toString(atual)); // Armazena o estado anterior do novo estado gerado
                    }
                }
            }


        }
    }


    public static void mostrarCaminho(Map<String, String> RastreioCaminho, int[] estadoFinal) {
        String estadoAtual = Arrays.toString(estadoFinal);  // Converte o estado final em string, para poder usar como chave no mapa. Inicia no estado final , a resposta
        LinkedList<String> caminho = new LinkedList<>();   // Lista que vai armazenar os estados no caminho, na ordem reversa

        while (estadoAtual != null) {  // Enquanto houver um estado anterior para percorrer
            caminho.addFirst(estadoAtual);  // Adiciona o estado atual no início da lista, para ir colocando tudo na ordem correta (inicio -> fim)
            estadoAtual = RastreioCaminho.get(estadoAtual);  // Obtém o estado anterior (do mapa)
        }

        // Imprime todos os estados no caminho, começando do inicial
        for (String estado : caminho) {
            System.out.println("Estado: " + estado);  // Exibe o estado
        }
    }




//    public static void respostaFormatada(int[] estado) {
//        String estadoBarco = (estado[2] == 0) ? "lado inicial" : "lado final";
//        System.out.println("Lado inicial: \nMissionários: " + estado[0] + " Canibais: " + estado[1] + " o barco está no " + estadoBarco);
//        System.out.println("---------------------------------------------------------------------------");
//    }



    // Recebe estado e movimento, faz as mudanças de acordo com o movimento recebido e devolve o novo estado
    public static int[] mover(int[] estado, int[] movimento) {
        int m = estado[0];
        int c = estado[1];
        int b = estado[2];

        int direcaoMovimento = (b == 0) ? -1 : 1;
        // positivo = lado oposto - negativo lado inicial

        // Movimentos possíveis:

        // 0 + 0 * 1 = 0   -> Barco vai para o lado final sem mudança. Válido
        // 0 + 0 * -1 = 0  -> Barco volta para o lado inicial sem mudança. Válido
        // 0 + 1 * 1 = 1   -> Barco vai para o lado final 1 missionário. Válido
        // 0 + 1 * -1 = -1 -> Barco volta para o lado inicial mas sem missionário. Inválido
        // 0 + 2 * 1 = 2   -> Barco vai para o lado final 2 missionários. Válido
        // 0 + 2 * -1 = -2 -> Barco volta para o lado inicial mas sem missionários. Inválido


        // 1 + 0 * 1 = 1   -> Barco vai para o lado final sem mudança. Válido
        // 1 + 0 * -1 = 1  -> Barco volta para o lado inicial sem mudança. Válido
        // 1 + 1 * 1 = 2   -> Barco vai para o lado final 1 missionário. Válido
        // 1 + 1 * -1 = 0  -> Barco volta para o lado inicial 1 missionário. Válido
        // 1 + 2 * 1 = 3   -> Barco vai para o lado final 2 missionários. Válido
        // 1 + 2 * -1 = -1 -> Barco volta para o lado inicial mas sem missionários. Inválido


        // 2 + 0 * 1 = 2   -> Barco vai para o lado final sem mudança. Válido
        // 2 + 0 * -1 = 2  -> Barco volta para o lado inicial sem mudança. Válido
        // 2 + 1 * 1 = 3   -> Barco vai para o lado final 1 missionário. Válido
        // 2 + 1 * -1 = 1  -> Barco volta para o lado inicial 1 missionário. Válido
        // 2 + 2 * 1 = 4   -> Barco vai para o lado final mas não há 4 missionários. Inválido
        // 2 + 2 * -1 = 0  -> Barco volta para o lado inicial 2 missionários. Válido


        // 3 + 0 * 1 = 3   -> Barco vai para o lado final sem mudança. Válido
        // 3 + 0 * -1 = 3  -> Barco volta para o lado inicial sem mudança. Válido
        // 3 + 1 * 1 = 4   -> Barco vai para o lado final mas não há 4 missionários. Inválido
        // 3 + 1 * -1 = 2  -> Barco volta para o lado inicial 1 missionário. Válido
        // 3 + 2 * 1 = 5   -> Barco vai para o lado final mas não há 5 missionários. Inválido
        // 3 + 2 * -1 = 1  -> Barco volta para o lado inicial 2 missionários. Válido



        int estM = m + (movimento[0] * direcaoMovimento);
        int estC = c + (movimento[1] * direcaoMovimento);
        int estB = (b == 0)?1:0;

        if (estM < 0 || estC < 0 || estM > 3 || estC > 3) {
            return null;
        }
        int[] novoEst = {estM , estC, estB};
        return novoEst;
    }

    // Verifica se o estado está de acordo com as regras do jogo
    public static boolean ehValido (int[] estado) {
        int missionarios = estado[0];
        int canibais = estado[1];

        if ((missionarios < canibais && missionarios > 0) ||           // Quebra a regra pq missionários é menor, ou inferior a 0
                ((3 - missionarios) < (3 - canibais) && (3 - missionarios) > 0)) {  // Se no lado oposto os missionários forem menos que os canibais, a condição é inválida ex: lado A = 2missionarios  1canibal (válido) MAS Lado B = 1missionario 2canibais
            // usei 3 - qtd pessoa pra identificar o lado oposto
            return false;
        }
        return true;
    }

    // apenas para debug
    public static void testarFuncaoEhValido() {
        for(int i = 0; i <= 3 ; i++) {
            for (int j = 0; j <= 3; j++) {
                if ((i < j && i > 0) || ((3-i) < (3-j) && (3 - i > 0))) {
                    System.out.println("Missionarios: " + i + " Canibais: " + j + " INVÁLIDO");
                } else {
                    System.out.println("Missionarios: " + i + " Canibais: " + j + "----VÁLIDO----");
                }
            }
        }
    }
}
