package ex2p1_jonatanvallecillo;

public class Montacarga {
    char[][] mapa;
    int fila;
    int columna;
    boolean montacarga;
    boolean entregar;

    public Montacarga(char[][] mapa, int fila, int columna, boolean montacarga, boolean entregar) {
        this.mapa = mapa;
        this.fila = fila;
        this.columna = columna;
        this.montacarga = montacarga;
        this.entregar = entregar;
    }

    public Montacarga() {
    }

    public char[][] getMapa() {
        return mapa;
    }

    public void setMapa(char[][] mapa) {
        this.mapa = mapa;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean getMontacarga() {
        return montacarga;
    }

    public void setMontacarga(boolean montacarga) {
        this.montacarga = montacarga;
    }

    public boolean getEntregar() {
        return entregar;
    }

    public void setEntregar(boolean entregar) {
        this.entregar = entregar;
    }
}
