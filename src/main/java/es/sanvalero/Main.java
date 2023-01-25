package es.sanvalero;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        int numeroEnemigos = 8;
        boolean seguirJugando = true;
        int tamanoTablero = 6;
        boolean trucos = false;
        Scanner teclado = new Scanner(System.in);

        Jugador bart = new Jugador('B', 'K');
        Jugador homer = new Jugador('H', 'F');

        // Jugador elige el tamaño del tablero.
        System.out.print("Elige el tamaño del tablero. Escribe un número entero aquí: ");
        tamanoTablero = teclado.nextByte();
        // Jugador elige nivel de dificultad.
        System.out.print("Elige nivel de dificultad(1/2/3): ");
        int dificultad = teclado.nextInt();

        switch (dificultad) {
            case 1 -> {
                numeroEnemigos = 5; //El nivel facil usa solo 5 enemigos
                System.out.println("Nivel Facil");
            }
            case 2 -> {
                System.out.println("Nivel Medio"); //El nivel medio los 8 enemigos que tiene por defecto
            }
            case 3 -> {
                numeroEnemigos = 10; //El nivel 10 aumenta los enemigos a 3
                System.out.println("Nivel Dificil");
            }
            default -> System.out.println("Aplicado nivel por defecto"); //En caso de meter un número que no sea se jugara en nivel medio
        }

        char[][] tableroB = new char[tamanoTablero][tamanoTablero]; //Tablero Real con enemigos Bart
        char[][] tableroH = new char[tamanoTablero][tamanoTablero]; //Tablero Real con enemigos Homer
        char[][] tableroVisibleB = new char[tamanoTablero][tamanoTablero]; //Tablero en el que no se ven enemigos Bart
        char[][] tableroVisibleH = new char[tamanoTablero][tamanoTablero]; //Tablero en el que no se ven enemigos Homer

        Tablero tableroBart = new Tablero(tamanoTablero);
        Tablero tableroHomer = new Tablero(tamanoTablero);


        //Rellena tableros con casillas libres (L)
        tableroBart.rellenarTableroVacio(tableroB);
        tableroHomer.rellenarTableroVacio(tableroH);

        tableroBart.rellenarTableroVacio(tableroVisibleB);
        tableroHomer.rellenarTableroVacio(tableroVisibleH);


        //Coloca los enemigos
        bart.generarEnemigos(tableroB, numeroEnemigos, tamanoTablero);
        homer.generarEnemigos(tableroH, numeroEnemigos, tamanoTablero);

        //Ubica la meta en los tableros (O)
        bart.colocarMeta(tableroB, tamanoTablero);
        homer.colocarMeta(tableroH, tamanoTablero);

        //Copia ubicacion de la meta en el tablero visible
        bart.copiarMeta(tableroVisibleB);
        homer.copiarMeta(tableroVisibleH);

        //Posiciona a los jugadores en su respectivo tablero
        bart.colocarJugador(tableroB, tamanoTablero);
        homer.colocarJugador(tableroH, tamanoTablero);

        //Posicionar Pociones (P)
        bart.colocarPociones(tableroB, tableroVisibleB, tamanoTablero);
        homer.colocarPociones(tableroH, tableroVisibleH, tamanoTablero);


        //Copia ubicacion de los jugadores en el tablero visible
        bart.copiarJugador(tableroVisibleB);
        homer.copiarJugador(tableroVisibleH);

        //Movimientos de los jugadores

        do {

            //Turno Bart
            trucos = bart.modoTrucos();
            if (trucos) {
                System.out.println("Modo TRUCO \n ");
                tableroBart.imprimirTablero(tableroB); //Imprimir el tablero real de Bart. Con enemigos visibles

            } else {
                tableroBart.imprimirTablero(tableroVisibleB);  // Impresión de tablero antes de mover
            }

            bart.movimientoJugador(tableroB, bart, tableroBart, tableroVisibleB, tamanoTablero);
            if (bart.alcanzarMeta(tableroB) || bart.jugadorMuerto()) {  //Si Bart muere o llega a la meta acaba el juego
                seguirJugando = false;
                continue;
            }

            //Turno Homer
            trucos = homer.modoTrucos();
            if (trucos) {
                System.out.println("Modo TRUCO \n ");
                tableroHomer.imprimirTablero(tableroH); //Imprimir el tablero real de Homer. Con enemigos visibles
            } else {
                tableroHomer.imprimirTablero(tableroVisibleH);  // Impresión de tablero antes de mover
            }
            homer.movimientoJugador(tableroH, homer, tableroHomer, tableroVisibleH, tamanoTablero);
            if (homer.alcanzarMeta(tableroH) || homer.jugadorMuerto()) { //Si Homer muere o llega a la meta acaba el juego
                seguirJugando = false;
            }
            //Las vidas saltan a otra ubicación
            bart.colocarPociones(tableroB, tableroVisibleB, tamanoTablero);
            homer.colocarPociones(tableroH, tableroVisibleH, tamanoTablero);


        } while (seguirJugando);
        System.out.println("¡FIN DEL JUEGO!");

    }
}
