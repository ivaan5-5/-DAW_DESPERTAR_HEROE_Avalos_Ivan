import java.util.Scanner;

public class ElDespertarDelHeroe {

    static final int VIDA_INICIAL = 100;
    static final int INICIO_DE_BACTAS = 3;
    static final int VIDA_CURADA_BACTA = 25;
    static final int INVENTARIO_CAPACIDAD = 3;
    static final int DANYO_MIN_HEROE = 10;
    static final int DANYO_MAX_HEROE = 20;

    static void mostrarMenuPrincipal() {
        System.out.println("\n==========================================");
        System.out.println("   STAR WARS: EL DESPERTAR DEL HÉROE");
        System.out.println("==========================================");
        System.out.println("1. Nueva Partida");
        System.out.println("2. Consultar Estado");
        System.out.println("3. Ayuda");
        System.out.println("4. Salir");
        System.out.println("==========================================");
    }

    static void mostrarAyuda() {
        System.out.println("\n-------------------------------------------");
        System.out.println("          MECÁNICAS DEL JUEGO");
        System.out.println("-------------------------------------------");
        System.out.println("- Ábrete paso por 3 rivales hasta vencer.");
        System.out.println("- Elige entre 3 armas (Sable, Bláster o Pica).");
        System.out.println("- Cada poción de Bacta restaura " + VIDA_CURADA_BACTA + " HP.");
        System.out.println("- Tu ataque causa entre " + DANYO_MIN_HEROE + " y " + DANYO_MAX_HEROE + " de daño.");
        System.out.println("-------------------------------------------\n");
    }

    static int leerEnteroValido(Scanner sc, String mensaje, int min, int max) {
        int opcion = -1;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); 

                if (opcion >= min && opcion <= max) {
                    valido = true; 
                } else {
                    System.out.println("Opción fuera de rango (" + min + " - " + max + "). Inténtalo de nuevo.");
                }
            } else {
                System.out.println("Debes seleccionar un número entero.");
                sc.nextLine();
            }
        }

        return opcion; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean salir = false;


        String nombreJugador = "";
        String armaElegidaNombre = "";
        int vidaActual = VIDA_INICIAL;

        int[] inventarioBactas = new int[INVENTARIO_CAPACIDAD];
        int numBactasDisponibles = 0;
        boolean personajeCreado = false;

        do {
            mostrarMenuPrincipal(); 
            opcion = leerEnteroValido(sc, "Selecciona una opción (1-4): ", 1, 4); 

            switch (opcion) {
                case 1:
                    nombreJugador = pedirNombrePersonaje(sc);
                    int armaElegida = elegirArma(sc);
                    
                    if (armaElegida == 1) {
                        armaElegidaNombre = "Sable de Luz";
                    } else if (armaElegida == 2) {
                        armaElegidaNombre = "Bláster Pesado";
                    } else {
                        armaElegidaNombre = "Pica de Fuerza";
                    }

                    for (int i = 0; i < INICIO_DE_BACTAS; i++) {
                        inventarioBactas[i] = VIDA_CURADA_BACTA;
                    }
                    numBactasDisponibles = INICIO_DE_BACTAS;
                    vidaActual = VIDA_INICIAL;
                    personajeCreado = true;

                    System.out.println("\n-------------------------------------------");
                    System.out.println(" ¡Que la Fuerza te acompañe, " + nombreJugador + "!");
                    System.out.println(" Tu aventura comienza con un " + armaElegidaNombre + ".");
                    System.out.println("-------------------------------------------\n");
                    break;

                case 2:
                    if (!personajeCreado) {
                        System.out.println("\n Debes iniciar una partida");
                    } else {
                        mostrarEstadoPadawan(nombreJugador, armaElegidaNombre, vidaActual, numBactasDisponibles);
                    }
                    break;

                case 3:
                    mostrarAyuda(); 
                    break;

                case 4:
                    System.out.println("\nQue la Fuerza te acompañe. ¡Hasta pronto!");
                    salir = true; 
                    break;
            }
        } while (!salir);

        sc.close(); 
    }

    static int elegirArma(Scanner sc) {
        System.out.println("\nSelecciona tu arma inicial:");
        System.out.println("1. Sable de Luz    [Daño: 10-20]");
        System.out.println("2. Bláster Pesado  [Daño: 10-20]");
        System.out.println("3. Pica de Fuerza  [Daño: 10-20]");

        return leerEnteroValido(sc, "Elige tu arma (1-3): ", 1, 3);
    }

    static String pedirNombrePersonaje(Scanner sc) {
        String nombre = "";
        
        while (nombre.trim().isEmpty()) {
            System.out.print("\nIntroduce el nombre de tu Padawan: ");
            nombre = sc.nextLine();
            
            if (nombre.trim().isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }
        }
        
        return nombre.trim();
    }

    static void mostrarEstadoPadawan(String nombre, String arma, int vida, int numBactas) {
        System.out.println("          ESTADO DEL PADAWAN              ");
        System.out.println(" Nombre:          " + nombre);
        System.out.println(" Arma Equipada:   " + arma);
        System.out.println(" Puntos de Vida:  " + vida + " / " + VIDA_INICIAL + " HP");
        System.out.println(" Viales de Bacta: " + numBactas + " / " + INVENTARIO_CAPACIDAD);
    }

static boolean simularCombate(Scanner sc, String nombreHeroe, int vidaHeroe, int[] inventarioBactas, int numBactas, String nombreEnemigo, int vidaEnemigo)
 {
    System.out.println("\n");
    System.out.println(" ¡UNO DE LOS CAZARRECOMPENSAS DE GREEDO TE ATACA" + "!");
    System.out.println("\n");

    boolean combateActivo = true;
    boolean victoria = false;

    if (vidaHeroe > 0 && combateActivo) 
    {
        victoria = true;
    }

    return victoria;
}
 }
    
    