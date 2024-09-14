public class Juego {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame () {
        String [][] map = planoDeJuego();
        int [] playerCoordinates = playerLocation(map);
        assert playerCoordinates != null;
        System.out.println(playerCoordinates[0] + " " + playerCoordinates[1]);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println(playerMovement(map, playerCoordinates, "w"));
        System.out.println(map[playerCoordinates[0]][playerCoordinates[1]+1]);
        System.out.println(playerMovement(map, playerCoordinates, "a"));
        System.out.println(playerMovement(map, playerCoordinates, "s"));
        System.out.println(playerMovement(map, playerCoordinates, "d"));
        System.out.println(playerMovement(map, playerCoordinates, "x"));
    }

    public static String [][] planoDeJuego() {
        String [][] matrix = new String[10][10];
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

    public static int [] playerLocation (String [][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals("P")) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static boolean playerMovement (String [][] map, int[] playerCoordinates, String direction) {
        if (direction.equals("w") && !"#".equals(map[playerCoordinates[0]][playerCoordinates[1] + 1])) {
            return true;
        } else if (direction.equals("s") && !"#".equals(map[playerCoordinates[0]][playerCoordinates[1] - 1])) {
            return true;
        } else if (direction.equals("a") && !"#".equals(map[playerCoordinates[0] - 1][playerCoordinates[1]])) {
            return true;
        } else if (direction.equals("d") && !"#".equals(map[playerCoordinates[0] + 1][playerCoordinates[1]])) {
            return true;
        } else {
            return false;
        }
    }

}
