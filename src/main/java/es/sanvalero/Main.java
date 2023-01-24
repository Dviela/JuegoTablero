package es.sanvalero;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        int numeroEnemigos = 8;
        boolean seguirJugando = true;
        int vidasBart = 3;
        int tamanoTablero = 6;
        Scanner teclado = new Scanner(System.in);

        char[][] tableroB = new char[tamanoTablero][tamanoTablero]; //Tablero Real con enemigos Bart
        char[][] tableroH = new char[tamanoTablero][tamanoTablero]; //Tablero Real con enemigos Homer
        char[][] tableroVisibleB = new char[tamanoTablero][tamanoTablero]; //Tablero en el que no se ven enemigos Bart
        char[][] tableroVisibleH = new char[tamanoTablero][tamanoTablero]; //Tablero en el que no se ven enemigos Homer

        Tablero tableroBart = new Tablero(tamanoTablero);
        Tablero tableroHomer = new Tablero(tamanoTablero);

        Jugador bart = new Jugador('B', 'K');
        Jugador homer = new Jugador('H', 'F');

        //Rellena tableros con casillas libres (L)
        tableroBart.rellenarTableroVacio(tableroB);
        tableroHomer.rellenarTableroVacio(tableroH);

        tableroBart.rellenarTableroVacio(tableroVisibleB);
        tableroHomer.rellenarTableroVacio(tableroVisibleH);


        //Coloca los enemigos
        bart.generarEnemigos(tableroB, numeroEnemigos);
        homer.generarEnemigos(tableroH, numeroEnemigos);

        //Ubica la meta en los tableros (O)
        bart.colocarMeta(tableroB, tamanoTablero);
        homer.colocarMeta(tableroH, tamanoTablero);

        //Copia ubicacion de la meta en el tablero visible
        bart.copiarMeta(tableroVisibleB);
        homer.copiarMeta(tableroVisibleH);

        //Posiciona a los jugadores en su respectivo tablero
        bart.colocarJugador(tableroB);
        homer.colocarJugador(tableroH);

        //Copia ubicacion de los jugadores en el tablero visible
        bart.copiarJugador(tableroVisibleB);
        homer.copiarJugador(tableroVisibleH);

        //Movimientos de los jugadores
        do {
                //Turno Bart

            //tableroBart.imprimirTablero(tableroB); //Activar para imprimir el tablero real. Con enemigos visibles
            tableroBart.imprimirTablero(tableroVisibleB);  // Impresión de tablero antes de mover

                bart.movimientoJugador(tableroB, bart, tableroBart, tableroVisibleB);
                if (bart.alcanzarMeta(tableroB) || bart.jugadorMuerto()){  //Si Bart muere o llega a la meta acaba el juego
                    seguirJugando = false;
                    continue;
                }

                //Turno Homer

            //tableroHomer.imprimirTablero(tableroH); //Activar para imprimir el tablero real. Con enemigos visibles
            tableroHomer.imprimirTablero(tableroVisibleH);  // Impresión de tablero antes de mover
            homer.movimientoJugador(tableroH, homer, tableroHomer, tableroVisibleH);
            if (homer.alcanzarMeta(tableroH) || homer.jugadorMuerto()){ //Si Homer muere o llega a la meta acaba el juego
                seguirJugando = false;
            }


        } while (seguirJugando);
        System.out.println("¡FIN DEL JUEGO!");

    }
}
