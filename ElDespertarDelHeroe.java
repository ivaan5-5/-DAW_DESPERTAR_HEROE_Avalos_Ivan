import java.util.Scanner;

public class ElDespertarDelHeroe 
{
    static final int Vida_Inicial = 100;
    static final int Inicio_de_Bactas = 3;
    static final int Vida_Curada_Bacta = 25;
    static final int Inventario = 3;
    static void mostrarMenuPrincipal() 
    {
        System.out.println("\n==========================================");
        System.out.println("   STAR WARS: EL DESPERTAR DEL HÉROE");
        System.out.println("==========================================");
        System.out.println("1. Nueva Partida");
        System.out.println("2. Consultar Ranking");
        System.out.println("3. Ayuda");
        System.out.println("4. Salir");
        System.out.println("==========================================");
    }

    static void mostrarAyuda() 
    {
        System.out.println("\n-------------------------------------------");
        System.out.println("          MECÁNICAS DEL JUEGO");
        System.out.println("-------------------------------------------");
        System.out.println("- Ábrete paso por 4 rivales hasta vencer al Sith.");
        System.out.println("- Elige entre 3 armas (Sable, Bláster o Pica).");
        System.out.println("- Gestiona tus viales de Bacta y la Fuerza.");
        System.out.println("- Cuidado con los cofres mímico y las trampas.");
        System.out.println("-------------------------------------------\n");
    }


    static int leerEnteroValido(Scanner sc, String mensaje, int min, int max) {
        int opcion = -1;
        boolean valido = false;

        while (!valido) 
            {
            System.out.print(mensaje);

            
            if (sc.hasNextInt()) 
                {
                opcion = sc.nextInt();
                sc.nextLine(); 

                if (opcion >= min && opcion <= max) {
                    valido = true; 
                } else {
                    System.out.println("Opción fuera de rango (" + min + " - " + max + "). Inténtalo de nuevo.");
                }
            } else {
            
                System.out.println("Debes seleccionar un numero entero");
                sc.nextLine();
            }
        }

        return opcion; 
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        do {
            mostrarMenuPrincipal(); 
            opcion = leerEnteroValido(sc, "Selecciona una opción (1-4): ", 1, 4); 

            
        
            switch (opcion) 
            {
                case 1:
                    String nombreJugador = pedirNombrePersonaje(sc);
                    int armaElegida = elegirArma(sc);
                    System.out.println(" ¡Que la Fuerza te acompañe, " + nombreJugador + "!");
                    break;
                case 2:
                    System.out.println("\n[Próximamente: Mostrando ranking de Padawans...]");
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

    static int elegirArma(Scanner sc) 
    {
        System.out.println("Selecciona tu arma inicial");
        System.out.println("1. Sable de Luz    [Daño: 15 | Defensa: 10]");
        System.out.println("2. Bláster Pesado  [Daño: 20 | Defensa: 5]");
        System.out.println("3. Pica de Fuerza  [Daño: 11 | Defensa: 15]");


        return leerEnteroValido(sc, "Elige tu arma (1-3): ", 1, 3);
    }

    
    static String pedirNombrePersonaje(Scanner sc) 
    {
        String nombre = "";
        
        while (nombre.trim().isEmpty()) {
            System.out.print("\nIntroduce el nombre de tu Padawan: ");
            nombre = sc.nextLine();
            
            if (nombre.trim().isEmpty()) {
                System.out.println("El nombre no puede estar vacío. Inténtalo de nuevo.");
            }
        }
        
        return nombre;
    }
}

    