package es.sanvalero;

public class Tablero {
    char casillasLibres;


    public Tablero(int tamanoTablero) {
        casillasLibres = 'L';

    }

    //Metodo para rellenar el tablero de casillas Libres (L)
    public void rellenarTableroVacio(char[][] tablero) {
        for (int i = 0; i < tablero.length; i++)
            for (int j = 0; j < tablero[0].length; j++)
                tablero[i][j] = casillasLibres;
    }


    //Metodo para imprimir situación del tablero
    public void imprimirTablero(char[][] tablero) {
        for (char[] chars : tablero) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }


}

