# A\* 8-Puzzle 

Este projeto implementa a solução do quebra-cabeça 8-puzzle utilizando o algoritmo A\* (A-star) com a heurística da distância de Manhattan.

## Sobre o Projeto

O 8-puzzle é um jogo deslizante composto por um tabuleiro 3x3 contendo números de 1 a 8 e um espaço vazio (representado por 0). O objetivo é reorganizar os números até atingir um estado desejado, movendo o espaço vazio.

Este programa recebe um estado inicial e um estado final como entrada e resolve o problema utilizando o algoritmo A\*.

## Tecnologias Utilizadas

- Java
- Algoritmo A\*
- Heurística da Distância de Manhattan

## Estrutura do Projeto

O projeto contém os seguintes arquivos:

- `Main.java` → Classe principal para entrada do usuário e execução do algoritmo.
- `HeuristicaManhattan.java` → Implementação da heurística de Manhattan.
- `aStar.java` → Implementação do algoritmo A\* para busca da solução.
- `Estado.java` → Representação do estado do tabuleiro.

## Como Executar

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/seuusuario/astar-8puzzle.git
   ```
2. **Compile os arquivos Java:**
   ```sh
   javac Astar_8puzzle/*.java
   ```
3. **Execute o programa:**
   ```sh
   java Astar_8puzzle.Main
   ```

## Uso

Após iniciar o programa, insira o estado inicial e o estado final do tabuleiro.

### Exemplo de Entrada:

```
Informe a entrada: ex: 123456780
123456780
Informe a saída: ex: 123456780
123456780
```

### Exemplo de Saída:

```
Objetivo alcançado!
Caminho até a solução:
Movimento: Inicio
1 2 3
4 5 6
7 8 0

Tempo de execução: 5ms
```

## Explicação do Código

### `Main.java`

- Lê a entrada e saída do usuário.
- Converte os valores para um array.
- Inicia a execução do algoritmo A\* e mede o tempo de execução.

### `HeuristicaManhattan.java`

- Calcula a heurística baseada na soma das distâncias de cada peça à sua posição final.

### `aStar.java`

- Implementa o algoritmo A\*, utilizando uma fila de prioridade para buscar a solução mais eficiente.
- Armazena os estados visitados para evitar repetições.
- Exibe o caminho percorrido até a solução.

### `Estado.java`

- Representa um estado do tabuleiro, armazenando a configuração atual e o custo do caminho.
- Implementa a interface `Comparable` para ordenar a fila de prioridade.

## Códigos Principais

### Conversão de String para Array (`Main.java`)

```java
public static int[] converterStringParaArray(String entrada) {
    int[] tabuleiro = new int[9];
    for (int i = 0; i < entrada.length(); i++) {
        tabuleiro[i] = Character.getNumericValue(entrada.charAt(i));
    }
    return tabuleiro;
}
```

**Explicação:** Converte a string digitada pelo usuário em um array de inteiros que representa o tabuleiro do 8-puzzle.

### Cálculo da Heurística de Manhattan (`HeuristicaManhattan.java`)

```java
public static int calculaHeuristica(int[] array) {
    int heuristica = 0;
    for (int i = 0; i < array.length; i++) {
        if (array[i] != 0) {
            int valorIndice = array[i] - 1;
            int linhaAtual = i / 3, colunaAtual = i % 3;
            int linhaObj = valorIndice / 3, colunaObj = valorIndice % 3;
            heuristica += Math.abs(linhaAtual - linhaObj) + Math.abs(colunaAtual - colunaObj);
        }
    }
    return heuristica;
}
```

**Explicação:** Calcula a heurística de Manhattan, que determina a soma das distâncias entre as posições atuais das peças e suas posições finais.

### Implementação do Algoritmo A\* (`aStar.java`)

```java
public static void aStar(int[] arrayPuzzle, int[] obj) {
    fila.add(new Estado(arrayPuzzle, 0, HeuristicaManhattan.calculaHeuristica(arrayPuzzle), null, "Inicio"));
    while (!fila.isEmpty()) {
        Estado estado = fila.poll();
        if (objAlcancado(estado.tabuleiro, obj)) {
            System.out.println("Objetivo alcançado!");
            reconstruirCaminho(estado);
            return;
        }
        executaMovimento(estado);
    }
}
```

**Explicação:** Implementa a busca A\*, utilizando uma fila de prioridade e avaliando o custo de cada estado até encontrar a solução.

### Estrutura do Estado do Tabuleiro (`Estado.java`)

```java
class Estado implements Comparable<Estado> {
    int[] tabuleiro;
    int h;
    int g;
    Estado pai;
    String movimento;

    Estado (int[] tabuleiro, int g, int h, Estado pai, String movimento) {
        this.tabuleiro = tabuleiro.clone();
        this.h = h;
        this.g = g;
        this.pai = pai;
        this.movimento = movimento;
    }

    @Override
    public int compareTo(Estado o) {
        return Integer.compare(this.h + this.g, o.h + o.g);
    }
}
```

**Explicação:** Representa um estado do tabuleiro, armazenando a configuração, os custos e o movimento realizado.

