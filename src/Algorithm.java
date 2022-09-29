import java.util.*;

public class Algorithm {

    // =============================================== //
    // Algorithm that calculates the shortest distance //
    static int shortestDistance(String[][] board) {
        Queue<CoordinateDistance> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Find starting location and add it to the queue
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if(board[r][c].contains("[S]")) {
                    queue.add(new CoordinateDistance(r, c, 0));
                }
            }
        }

        // Loop while the queue is not empty
        while(queue.size() > 0) {
            CoordinateDistance current = queue.remove();

            // Return the distance if the destination is reached
            if(board[current.row][current.col].contains("[E]")) return current.distance;

            // Moving up
            if(current.row > 0 && checkIfPossible(board,current.row-1, current.col, visited)) {
                String visitedPos = current.row-1 +","+ current.col;
                visited.add(visitedPos);
                queue.add(new CoordinateDistance(current.row-1, current.col, current.distance+1));
            }

            // Moving Down
            if(current.row < board.length-1 && checkIfPossible(board,current.row+1, current.col, visited)) {
                String visitedPos = current.row+1 +","+ current.col;
                visited.add(visitedPos);
                queue.add(new CoordinateDistance(current.row+1, current.col, current.distance+1));
            }

            // Moving Right
            if(current.col > 0 && checkIfPossible(board, current.row, current.col-1, visited)) {
                String visitedPos = current.row +","+ (current.col-1);
                visited.add(visitedPos);
                queue.add(new CoordinateDistance(current.row, current.col-1, current.distance+1));
            }

            // Moving Left
            if(current.col < board[0].length-1 && checkIfPossible(board, current.row, current.col+1, visited)) {
                String visitedPos = current.row +","+ (current.col+1);
                visited.add(visitedPos);
                queue.add(new CoordinateDistance(current.row, current.col+1, current.distance+1));
            }
        }
        return -1;
    }
    static boolean checkIfPossible(String board[][], int row, int col, Set<String> visited) {
        if(visited.contains(row+","+col)) return false;
        if(board[row][col].contains("[X]")) return false;
        return true;
    }



    // =========================================================== //
    // Algorithm that calculates the amount of connected obstacles //
    static int connectedObstacleCount(String[][] board) {
        Set<String> visited = new HashSet<>();
        int count = 0;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if(board[r][c].contains("[X]") && !(visited.contains(r+","+c))) {
                    count++;
                    exploreObstacleIsland(board, r, c, visited);
                }
            }
        }
        return count;
    }
    static void exploreObstacleIsland(String[][] board, int r, int c, Set<String> visited) {
        if(r < 0 || r >= board.length || c < 0 || c >= board[0].length) return;
        if(!board[r][c].equals("[X]")) return;
        if(visited.contains(r+","+c)) return;
        visited.add(r+","+c);

        exploreObstacleIsland(board, r-1, c, visited);
        exploreObstacleIsland(board, r+1, c, visited);
        exploreObstacleIsland(board, r, c-1, visited);
        exploreObstacleIsland(board, r, c+1, visited);
    }



    // ================================================================== //
    // Algorithm that calculates the largest group of connected obstacles //
    static int largestConnectedObstacleCount(String[][] board) {
        Set<String> visited = new HashSet<>();
        int currentBiggest = 0;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                int size = exploreObstacleIslandSize(board, r, c, visited);
                if(size > currentBiggest) {
                    currentBiggest = size;
                }
            }
        }
        return currentBiggest;
    }
    static int exploreObstacleIslandSize(String[][] board, int r, int c, Set<String> visited) {
        if(r < 0 || r >= board.length || c < 0 || c >= board[0].length) return 0;
        if(!board[r][c].equals("[X]")) return 0;
        if(visited.contains(r+","+c)) return 0;
        visited.add(r+","+c);

        return 1 + exploreObstacleIslandSize(board, r+1, c, visited)
                + exploreObstacleIslandSize(board, r, c+1, visited)
                + exploreObstacleIslandSize(board, r-1, c, visited)
                + exploreObstacleIslandSize(board, r, c-1, visited);
    }
}

