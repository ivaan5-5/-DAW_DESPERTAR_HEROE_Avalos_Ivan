import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class ElDespertarDelHeroe 
{

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
        System.out.println("2. Consultar Ranking");
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
        int[] vidaActual = {VIDA_INICIAL};

        int[] inventarioBactas = new int[INVENTARIO_CAPACIDAD];
        int[] numBactasDisponibles = {0};
        String[] nombresRanking = new String[RANKING_CAPACIDAD];
        int[] puntosRanking = new int[RANKING_CAPACIDAD];
        int[] numRegistrosRanking = {0};
        boolean[] xmlValidoParaGuardar = {true};
        cargarRankingXML(nombresRanking, puntosRanking, numRegistrosRanking, xmlValidoParaGuardar);

        do 
        {
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

                    for (int i = 0; i < INICIO_DE_BACTAS; i++) 
                    {
                        inventarioBactas[i] = VIDA_CURADA_BACTA;
                    }
                    numBactasDisponibles [0] = INICIO_DE_BACTAS;
                    vidaActual [0 ] = VIDA_INICIAL;
                    int puntuacionPartida = 0;

                    System.out.println("\n-------------------------------------------");
                    System.out.println(" ¡Que la Fuerza te acompañe, " + nombreJugador + "!");
                    System.out.println(" Tu aventura comienza con un " + armaElegidaNombre + ".");
                    System.out.println("-------------------------------------------\n");
                    if (simularCombate(sc, nombreJugador, armaElegidaNombre, vidaActual, inventarioBactas, numBactasDisponibles, "Cazarrecompensas de Greedo", 40)) {
                        puntuacionPartida += 100;
                        if (simularCombate(sc, nombreJugador, armaElegidaNombre, vidaActual, inventarioBactas, numBactasDisponibles, "Soldado de Asalto Imperial", 60)) {
                            puntuacionPartida += 100;
                            if (simularCombate(sc, nombreJugador, armaElegidaNombre, vidaActual, inventarioBactas, numBactasDisponibles, "Inquisidor Sith", 80)) {
                                puntuacionPartida += 100;
                                puntuacionPartida += vidaActual[0]; // Bonificación por vida restante al vencer
                                System.out.println(" ¡VICTORIA, " + nombreJugador.toUpperCase() + "!");
                                System.out.println(" Has completado tu entrenamiento (+ " + vidaActual[0] + " pts de bonificación por vida restante).");
                            }
                        }
                    }

                    System.out.println("Puntuación final: " + puntuacionPartida + " puntos.");
                    registrarEnRanking(nombresRanking, puntosRanking, numRegistrosRanking, nombreJugador, puntuacionPartida);
                    if (xmlValidoParaGuardar[0]) {
                        guardarRankingXML(nombresRanking, puntosRanking, numRegistrosRanking[0]);
                    }
                    break;

                case 2:
                    mostrarRanking(nombresRanking, puntosRanking, numRegistrosRanking[0]);
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

    static void mostrarEstadoPadawan(String nombre, String arma, int vida, int numBactas) 
    {
        System.out.println("          ESTADO DEL PADAWAN              ");
        System.out.println(" Nombre:          " + nombre);
        System.out.println(" Arma Equipada:   " + arma);
        System.out.println(" Puntos de Vida:  " + vida + " / " + VIDA_INICIAL + " HP");
        System.out.println(" Viales de Bacta: " + numBactas + " / " + INVENTARIO_CAPACIDAD);
    }

static boolean simularCombate(Scanner sc, String nombreHeroe, String armaHeroe, int[] vidaHeroe, int[] inventarioBactas, int[] numBactas, String nombreEnemigo, int vidaEnemigo) 
{
        System.out.println("\n");
        System.out.println(" ¡UN ENEMIGO APARECE: " + nombreEnemigo.toUpperCase() + "!");
        System.out.println("\n");

        boolean combateActivo = true;
        boolean victoria = false;

        while (combateActivo && vidaHeroe[0] > 0 && vidaEnemigo > 0) 
            {
            System.out.println("\n--- TU TURNO ---");
            System.out.println("Tu Vida: " + vidaHeroe[0] + " HP | Vida de " + nombreEnemigo + ": " + vidaEnemigo + " HP");
            System.out.println("1. Atacar");
            System.out.println("2. Usar vial de Bacta (" + numBactas[0] + " disponibles)");
            System.out.println("3. Consultar Inventario");
            System.out.println("4. Huir");

            int accion = leerEnteroValido(sc, "Elige tu acción (1-4): ", 1, 4);

            switch (accion) 
            {
                case 1:
                    int probabilidadFallo = 0;
                    if (armaHeroe.equals("Bláster Pesado")) {
                        probabilidadFallo = 35; // 35% de fallo
                    } else if (armaHeroe.equals("Pica de Fuerza")) {
                        probabilidadFallo = 15; // 15% de fallo
                    }

                    int tiradaAcierto = (int) (Math.random() * 100) + 1;

                    if (tiradaAcierto <= probabilidadFallo) {
                        System.out.println("\n¡Has fallado el ataque con tu " + armaHeroe + "!");
                    } else {
                        int danyoHeroe = (int) (Math.random() * (DANYO_MAX_HEROE - DANYO_MIN_HEROE + 1)) + DANYO_MIN_HEROE;
                        vidaEnemigo -= danyoHeroe;
                        System.out.println("\nAtacas a " + nombreEnemigo + " con tu " + armaHeroe + " y le causas " + danyoHeroe + " de daño.");
                    }

                    if (vidaEnemigo <= 0) {
                        victoria = true;
                        combateActivo = false;
                        System.out.println("\n¡Has vencido a " + nombreEnemigo + "!");
                    } else {
                        int danyoEnemigo = (int) (Math.random() * 11) + 5;
                        vidaHeroe[0] -= danyoEnemigo;
                        System.out.println(nombreEnemigo + " contraataca y te quita " + danyoEnemigo + " HP.");
                        if (vidaHeroe[0] <= 0) {
                            vidaHeroe[0] = 0;
                            combateActivo = false;
                            System.out.println("\nHas caído en combate...");
                        }
                    }
                    break;

                case 2:
                    if (vidaHeroe[0] >= VIDA_INICIAL) 
                    {
                        System.out.println("\n[!] Ya tienes la vida al máximo (" + VIDA_INICIAL + " HP). No necesitas usar un vial de Bacta.");
                    }
                    else if (numBactas[0] > 0) 
                        {
                        for (int i = 0; i < INVENTARIO_CAPACIDAD; i++) 
                        {
                            if (inventarioBactas[i] > 0) 
                            {
                                int curacion = inventarioBactas[i];
                                inventarioBactas[i] = 0;
                                numBactas[0]--;
                                
                                vidaHeroe[0] += curacion;
                                if (vidaHeroe[0] > VIDA_INICIAL) {
                                    vidaHeroe[0] = VIDA_INICIAL;
                                }
                                System.out.println("\n Usas un vial de Bacta y recuperas " + curacion + " HP. Vida actual: " + vidaHeroe[0] + " HP.");
                                break;
                            }
                        }
                    
                    int danyoEnemigo = (int) (Math.random() * 11) + 5;
                        vidaHeroe[0] -= danyoEnemigo;
                        System.out.println(nombreEnemigo + " aprovecha tu distracción y te quita " + danyoEnemigo + " HP.");
                        if (vidaHeroe[0] <= 0) 
                            {
                            vidaHeroe[0] = 0;
                            combateActivo = false;
                            System.out.println("\n No has soportado la fuerza del lado oscuro...");
                            }

                    } else 
                        {
                        System.out.println("\n[!] No te quedan viales de Bacta en el inventario.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- INVENTARIO DE BACTAS ---");
                    for (int i = 0; i < INVENTARIO_CAPACIDAD; i++) {
                        String estadoHueco = (inventarioBactas[i] > 0) ? inventarioBactas[i] + " HP" : "Vacío";
                        System.out.println("Ranura " + (i + 1) + ": " + estadoHueco);
                    }
                    break;

                case 4:
                    System.out.println("\nHas decidido huir del combate...");
                    combateActivo = false;
                    break;
            }
        }

        return victoria;
    }
 
    
 static final int RANKING_CAPACIDAD = 10;
 static void registrarEnRanking(String[] nombres, int[] puntos, int[] numRegistros, String nuevoNombre, int nuevaPuntuacion) 
 {
        int total = numRegistros[0];

        if (total == RANKING_CAPACIDAD && nuevaPuntuacion <= puntos[total - 1]) 
        {
            System.out.println("Tu puntuación no entra en el Top Jedi " + RANKING_CAPACIDAD + ".");
            return;
        }

        int pos = 0;
        while (pos < total && puntos[pos] >= nuevaPuntuacion) 
        {
            pos++;
        }

        int limiteDesplazamiento = (total < RANKING_CAPACIDAD) ? total : RANKING_CAPACIDAD - 1;
        for (int i = limiteDesplazamiento; i > pos; i--) {
            nombres[i] = nombres[i - 1];
            puntos[i] = puntos[i - 1];
        }

        nombres[pos] = nuevoNombre;
        puntos[pos] = nuevaPuntuacion;

        if (total < RANKING_CAPACIDAD) 
        {
            numRegistros[0]++;
        }

        System.out.println("¡Has sido el jedi #" + (pos + 1) + " del Ranking!");
    }

    static void mostrarRanking(String[] nombres, int[] puntos, int numRegistros) {
        System.out.println("\n");
        System.out.println("      CLASIFICACIÓN JEDI (TOP 10)");
        System.out.println("\n");

        if (numRegistros == 0) 
        {
            System.out.println("  El ranking está vacío. ¡Sé el primero en jugar!");
        } else {
            for (int i = 0; i < numRegistros; i++) {
                System.out.println("  #" + (i + 1) + " - " + nombres[i] + " : " + puntos[i] + " pts");
            }
        }
        System.out.println("\n");
    }

}
    