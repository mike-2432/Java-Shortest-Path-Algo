import java.util.Scanner;

public class Message {
    static Scanner scanner = new Scanner(System.in);

    // Introduction //
    static void introduction() {
        System.out.println("======================================================");
        System.out.println("   Welcome to the shortest path graph algorithm app");
        System.out.println("======================================================");
        System.out.println("");
    }

    // Space //
    static void space() {
        System.out.println("===============================================================");
    }

    // Get board size //
    static int[] getBoardSize() {
        System.out.print("Enter the number of rows of the matrix: ");
        while(!scanner.hasNextInt()) scanner.next();
        int rows = scanner.nextInt();
        while(rows < 2) {
            System.out.print("There needs to be at least two rows: ");
            while(!scanner.hasNextInt()) scanner.next();
            rows = scanner.nextInt();
        }

        System.out.print("Enter the number of columns of the matrix: ");
        while(!scanner.hasNextInt()) scanner.next();
        int cols = scanner.nextInt();
        while(cols < 2) {
            System.out.print("There needs to be at least two columns: ");
            while(!scanner.hasNextInt()) scanner.next();
            cols = scanner.nextInt();
        }

        int[] size = { rows, cols };
        return size;
    }

    // Option to randomize //
    static boolean randomize() {
        System.out.print("Do you want to randomize the board? Type: true / false: ");
        while(!scanner.hasNextBoolean()) scanner.next();
        boolean randomize = scanner.nextBoolean();
        return randomize;
    }

    // Get Coordinates //
    static int[] getCoordinates(String text, int maxRow, int maxCol) {
        System.out.print("Enter the row of the "+text+", starting from 0: ");
        while(!scanner.hasNextInt()) scanner.next();
        int row = scanner.nextInt();
        while(row > maxRow-1) {
            System.out.println("The number lies outside of the matrix, please try again.");
            System.out.print("Enter the row of the "+text+", starting from 0: ");
            while(!scanner.hasNextInt()) scanner.next();
            row = scanner.nextInt();
        }

        System.out.print("Enter the col of the "+text+", starting from 0: ");
        while(!scanner.hasNextInt()) scanner.next();
        int col = scanner.nextInt();
        while(col > maxCol-1) {
            System.out.println("The number lies outside of the matrix, please try again.");
            System.out.print("Enter the col of the "+text+", starting from 0: ");
            while(!scanner.hasNextInt()) scanner.next();
            col = scanner.nextInt();
        }

        int[] coordinates = { row, col };
        return coordinates;
    }

    // Coordinate is already in use //
    static void coordinateAlreadyInUse() {
        System.out.println("The coordinate you picked seems to be already in use...");
        System.out.println("Please try again.");
    }

    // Add another obstacle //
    static boolean addAnotherObstacle() {
        System.out.print("Do you want to add an obstacle? Type: true / false: ");
        while(!scanner.hasNextBoolean()) scanner.next();
        boolean anotherObstacle = scanner.nextBoolean();
        return anotherObstacle;
    }

    // Repeat application //
    static boolean repeatApplication() {
        System.out.println("");
        System.out.print("Would you like to restart the application? Type: true / false: ");
        while(!scanner.hasNextBoolean()) scanner.next();
        boolean repeat = scanner.nextBoolean();
        if(repeat) {
            for(int i=0; i<10; i++) {
                System.out.println("");
            }
        }
        return repeat;
    }

    // Shortest Path //
    static void shortestDistanceMessage(int count) {
        if(count >= 0) {
            System.out.println("The shortest path takes "+count+" step(s).");
        } else {
            System.out.println("There is no path possible");
        }
    }

    // Connected Obstacles //
    static void connectedObstacleCountMessage(int count) {
        System.out.println("");
        System.out.println("The amount of connected obstacles, or 'Islands', is "+count+".");
    }

    // Largest Obstacle Island //
    static void largestObstacleCountMessage(int count) {
        System.out.println("");
        System.out.println("The largest group of connected obstacles contains "+count+" obstacles.");
    }
}
