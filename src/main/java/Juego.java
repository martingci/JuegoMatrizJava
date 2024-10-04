import java.util.Scanner;

public class Juego {
    public static void main(String[] args) {
        startGame();
    }

    public static String[][] mapGame() {
        String[][] matrix = new String[10][10];
        matrix[0] = new String[]{"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"};
        matrix[1] = new String[]{"#", "P", ".", ".", ".", "E", ".", ".", "C", "#"};
        matrix[2] = new String[]{"#", ".", ".", "#", ".", ".", ".", ".", ".", "#"};
        matrix[3] = new String[]{"#", ".", ".", ".", ".", ".", ".", "E", ".", "#"};
        matrix[4] = new String[]{"#", ".", ".", "#", ".", ".", ".", ".", ".", "#"};
        matrix[5] = new String[]{"#", ".", ".", ".", ".", ".", ".", ".", ".", "#"};
        matrix[6] = new String[]{"#", "E", ".", "#", "#", "#", "#", "#", ".", "#"};
        matrix[7] = new String[]{"#", ".", ".", "E", ".", ".", "C", "#", ".", "#"};
        matrix[8] = new String[]{"#", "C", ".", ".", ".", "E", ".", "#", "X", "#"};
        matrix[9] = new String[]{"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"};
        return matrix;
    }

    public static Scanner scanner() {
        return new Scanner(System.in);
    }

    public static void startGame() {
        String[][] map = mapGame();
        int[] player = playerStartStats();
        while (true) {
            mostrarPlano(map);
            System.out.println("-------------------------");
            String userInput = obtenerInput("    W \n A S D para moverse");
            map = realizarMovimiento(map, player, userInput);

        }
    }

    public static int leerOpcionLimitada(String mensaje, int min, int max){
        System.out.print(mensaje);
        int opcion;
        while (true) {
            try {
                opcion = scanner().nextInt();
                if ((opcion >= min) && (opcion <= max)) {
                    break;
                } else {
                    System.out.print("Opción sale del rango. Inténtelo nuevamente: ");
                }
            } catch(Exception InputMismatchException){
                System.out.print("Entrada no válida. Ingrese un número: ");
            }
        }
        return opcion;
    }

    public static boolean gestordeSituaciones (int[][] ubicaciones, String[][] map) {
        switch (map[ubicaciones[1][1]][ubicaciones[1][0]]) {
            case "#":
                return false;
            case "C":
                int opcion = leerOpcionLimitada("Desea abrir el cofre, 1 para abrilo, 0 para rechazarlo", 0,1);
                if (opcion == 1) {
                    if ((int)(Math.random() * 2) == 1) {
                        System.out.println("Felicidades, haz encontrado");
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
        }
        return true;
    }
    
    public static int [][] ubicacionActualyFutura (int[] playerStats, String direction) {
        int xInicial = playerStats[0];
        int yInicial = playerStats[1];
        int xFutura = xInicial;
        int yFutura = yInicial;
        switch(direction) {
            case "w":
                yFutura--;
                break;
            case "s":
                yFutura++;
                break;
            case "a":
                xFutura--;
                break;
            case "d":
                xFutura++;
                break;
        }
        return new int[][]{{xInicial, yInicial}, {xFutura, yFutura}};
    }

    public static String[][] realizarMovimiento(String[][] map, int[] playerStats, String direction) {
        int [][] ubicaciones = ubicacionActualyFutura(playerStats, direction);
        if (gestordeSituaciones(ubicaciones, map)) {
            map[ubicaciones[0][1]][ubicaciones[0][0]] = ".";
            map[ubicaciones[1][1]][ubicaciones[1][0]] = "P";
            playerStats[0] = ubicaciones[1][0];
            playerStats[1] = ubicaciones[1][1];
        }
        return map;
    }

    public static void mostrarPlano(String[][] matrix) {
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string + "   ");
            }
            System.out.println();
        }
    }

    public static int[] playerStartStats() {
        int[] player = new int[4];
        player[0] = 1; // Coordenada en x
        player[1] = 1; // Coordenada en y
        player[2] = 100;
        player[3] = 15;
        return player;
    }

    public static String obtenerInput(String mensaje) {
        System.out.print(mensaje + " :");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}