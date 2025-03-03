package Aula04;

import java.util.Arrays;

class Estado implements Comparable<Estado> {

    int[] tabuleiro;
    int h;
    int g;
    Estado pai;

    Estado (int[] tabuleiro, int g, int h,  Estado pai) {
        this.tabuleiro = tabuleiro.clone();
        this.h = h;
        this.g = g;
        this.pai = pai;
    }

    @Override
    public int compareTo(Estado o) {
        return Integer.compare(this.h + this.g, o.h + o.g);
    }

    @Override
    public boolean equals(Object o) {
        return Arrays.equals(this.tabuleiro, ((Estado) o).tabuleiro);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tabuleiro);
    }
}
