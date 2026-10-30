import java.util.Scanner;

public class ElDespertarDelHeroe 
{
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
        System.out.println("- Ábrete paso por 4 estancias hasta vencer al Sith.");
        System.out.println("- Elige entre 3 armas (Sable, Bláster o Pica).");
        System.out.println("- Gestiona tus viales de Bacta y la Fuerza.");
        System.out.println("- Cuidado con los cofres mímico y las trampas.");
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
            
                System.out.println("Entrada inválida. Debe ser un número entero.");
                sc.nextLine();
            }
        }

        return opcion; 
    }

    // PIEZA 5: Método principal que coordina todo el juego
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        do {
            mostrarMenuPrincipal(); 
            opcion = leerEnteroValido(sc, "Selecciona una opción (1-4): ", 1, 4); 

            
            switch (opcion) {
                case 1:
                    System.out.println("\n[Próximamente: Iniciando partida...]");
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
}
