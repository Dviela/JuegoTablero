package es.sanvalero;

import java.util.Random;
import java.util.Scanner;

public class Jugador {
    int filaJugador;
    int columnaJugador;
    int filaMeta;
    int columnaMeta;
    int filaEnemigos;
    int columnaEnemigos;
    char jugador;
    char enemigo;
    char meta;
    byte tamanoTablero;
    int numeroEnemigos;
    int vidasJugador;
    boolean cambioTurno;

    Random generadorNumerosAleatorios = new Random();
    Scanner teclado = new Scanner(System.in);

    public Jugador(char jugador, char enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        tamanoTablero = 6;
        filaJugador = 0;
        columnaJugador = 0;
        filaEnemigos = 1;
        columnaEnemigos = 1;
        numeroEnemigos = 8;
        vidasJugador = 3;
        filaMeta = 2;
        columnaMeta = 2;
        meta = 'O';
        cambioTurno = true;

    }


    //Metodo para rellenar el tablero con enemigos (E)
    public void generarEnemigos(char[][] tablero, int numeroEnemigos) {
        for (int i = 0; i < numeroEnemigos; i++) {
            filaEnemigos = generadorNumerosAleatorios.nextInt(tamanoTablero);
            columnaEnemigos = generadorNumerosAleatorios.nextInt(tamanoTablero);
            if (tablero[filaEnemigos][columnaEnemigos] == enemigo) {  //Si coincide con otro enemigo anterior repite
                tablero[filaEnemigos][columnaEnemigos] = enemigo;
                filaEnemigos = generadorNumerosAleatorios.nextInt(tamanoTablero);
                columnaEnemigos = generadorNumerosAleatorios.nextInt(tamanoTablero);
                tablero[filaEnemigos][columnaEnemigos] = enemigo;
            } else {
                tablero[filaEnemigos][columnaEnemigos] = enemigo;
            }

        }
    }

    //Metodo para ubicar la casilla de meta (O)
    public void colocarMeta(char[][] tablero, int tamanoTablero) {
        filaMeta = generadorNumerosAleatorios.nextInt(tamanoTablero);
        columnaMeta = generadorNumerosAleatorios.nextInt(tamanoTablero);
        if (tablero[filaMeta][columnaMeta] == enemigo) {  //Si coincide con la casilla de un enemigo repite
            tablero[filaMeta][columnaMeta] = enemigo;
            filaMeta = generadorNumerosAleatorios.nextInt(tamanoTablero);
            columnaMeta = generadorNumerosAleatorios.nextInt(tamanoTablero);
            tablero[filaMeta][columnaMeta] = meta;
        } else {
            tablero[filaMeta][columnaMeta] = meta;
        }

    }

    //Metodo para copiar la ubicacion de la meta de un tablero a otro
    public void copiarMeta(char[][] tablero) {
        tablero[filaMeta][columnaMeta] = meta;
    }


    //Metodo ubicar jugador en tablero
    public void colocarJugador(char[][] tablero) {
        filaJugador = generadorNumerosAleatorios.nextInt(tamanoTablero);
        columnaJugador = generadorNumerosAleatorios.nextInt(tamanoTablero);
        if (tablero[filaJugador][columnaJugador] == enemigo) {  //Si coincide con la casilla de un enemigo repite
            tablero[filaJugador][columnaJugador] = enemigo;
            filaJugador = generadorNumerosAleatorios.nextInt(tamanoTablero);
            columnaJugador = generadorNumerosAleatorios.nextInt(tamanoTablero);
            tablero[filaJugador][columnaJugador] = jugador;
        } else if (tablero[filaJugador][columnaJugador] == meta) { //Si aparece encima de la meta repite
            tablero[filaJugador][columnaJugador] = meta;
            filaJugador = generadorNumerosAleatorios.nextInt(tamanoTablero);
            columnaJugador = generadorNumerosAleatorios.nextInt(tamanoTablero);
            tablero[filaJugador][columnaJugador] = jugador;

        } else {
            tablero[filaJugador][columnaJugador] = jugador;
        }

    }

    //Metodo para copiar la ubicacion del jugador de un tablero a otro
    public void copiarJugador(char[][] tablero) {
        tablero[filaJugador][columnaJugador] = jugador;
    }

    //Reduce una vida si toca un enemigo
    public void gestionVidas(char[][] tablero) {
        if (tablero[filaJugador][columnaJugador] == enemigo) {
            vidasJugador--;
            System.out.println("HAS DADO CON UN ENEMIGO \n Te quedan " + vidasJugador + " vidas");
        }
        tablero[filaJugador][columnaJugador] = jugador;
    }

    //Comprueba si le quedan vidas al jugador
    public boolean jugadorMuerto() {
        if (vidasJugador <= 0) {
            System.out.println("Has muerto");
            return true;
        } else {
            return false;
        }

    }

    //Finaliza el juego al llegar a la meta
    public boolean alcanzarMeta(char[][] tablero) {
        if (tablero[filaJugador][columnaJugador] == tablero[filaMeta][columnaMeta]) {
            System.out.println("Has llegado a la meta \n HAS GANADO");
            return true;
        } else {
            return false;
        }

    }

    //Comprueba que no te salgas de los limites y en ese caso aparezcas por el otro lado
    public void limitesTablero(char[][] tablero, int maximoTablero) {
        // if (filaJugador < 0 || filaJugador > 5|| columnaJugador < 0 || columnaJugador>5)
        if (filaJugador < 0) {
            filaJugador = maximoTablero - 1;
        } else if (filaJugador > maximoTablero - 1) {
            filaJugador = 0;
        } else if (columnaJugador < 0) {
            columnaJugador = maximoTablero - 1;
        } else if (columnaJugador > maximoTablero - 1) {
            columnaJugador = 0;
        }
    }

    //Menú para realizar movimientos
    public void movimientoJugador(char[][] tablero, Jugador jugador, Tablero objetoTablero, char[][] tableroCopia) {
        String jugadorActual = "Bart";
        int nCasillas;

        do {

                System.out.print(" Numero de casillas que quieres mover (1-3):  ");
                nCasillas = Integer.parseInt(teclado.nextLine());
                if(nCasillas != 1 && nCasillas != 2 && nCasillas != 3){
                    System.out.println("Número Incorrecto. Solo puedes mover de 1 a 3 casillas");
                    continue;
                }



            System.out.print(" Dirección del movimiento (W/S/A/D):  ");
            String direccionMovimiento = teclado.nextLine();

            switch (direccionMovimiento) {
                case "w" -> {
                    if (jugador.filaJugador == 0 && jugador.columnaJugador == 5) {  //Evita los movimientos de desplazamiento desde la esquina superior derecha
                        System.out.println("Movimiento ilegal. Pierdes tu turno");
                        jugador.filaJugador = 0;
                        break;
                    }
                    tablero[jugador.filaJugador][jugador.columnaJugador] = objetoTablero.casillasLibres;
                    jugador.filaJugador -= nCasillas;
                    jugador.limitesTablero(tablero, tamanoTablero);
                    jugador.gestionVidas(tablero);

                }
                case "s" -> {
                    tablero[jugador.filaJugador][jugador.columnaJugador] = objetoTablero.casillasLibres;
                    jugador.filaJugador += nCasillas;
                    jugador.limitesTablero(tablero, tamanoTablero);
                    jugador.gestionVidas(tablero);


                }
                case "a" -> {
                    if (jugador.filaJugador == 5 && jugador.columnaJugador == 0) {  //Evita el movimiento de desplazamiento desde la esquina inferior izquierda
                        System.out.println("Movimiento ilegal. Pierdes tu turno");
                        jugador.columnaJugador = 0;
                        break;
                    }
                    tablero[jugador.filaJugador][jugador.columnaJugador] = objetoTablero.casillasLibres;
                    jugador.columnaJugador -= nCasillas;
                    jugador.limitesTablero(tablero, tamanoTablero);
                    jugador.gestionVidas(tablero);

                }
                case "d" -> {
                    tablero[jugador.filaJugador][jugador.columnaJugador] = objetoTablero.casillasLibres;
                    jugador.columnaJugador += nCasillas;
                    jugador.limitesTablero(tablero, tamanoTablero);
                    jugador.gestionVidas(tablero);

                }
                default -> System.out.println("Tecla incorrecta");
            }

            cambioTurno = false;

            // objetoTablero.imprimirTablero(tablero); //Activar para ver tablero real con enemigos despues de jugar el turno
            objetoTablero.rellenarTableroVacio(tableroCopia);
            jugador.copiarMeta(tableroCopia);
            jugador.copiarJugador(tableroCopia);

            objetoTablero.imprimirTablero(tableroCopia); // Impresión de tablero despues de mover

        } while (cambioTurno);
        System.out.println("FIN DEL TURNO");

    }


}





