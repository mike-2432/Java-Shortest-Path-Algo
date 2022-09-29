public class Main {
    public static void main(String[] args) {

        boolean run = true;
        while(run) {
            // Introduction Message //
            Message.introduction();

            // Get the board size and create a board object //
            int[] size = Message.getBoardSize();
            int rows = size[0];
            int columns = size[1];
            Board board = new Board(rows, columns);
            board.populateBoard();
            board.printBoard();

            // Option to randomize the board //
            boolean randomize = Message.randomize();
            if(randomize) {
                board.randomize();
                board.printBoard();
            } else {
                // Custom board //
                // Get start coordinates //
                int[] startLocation = Message.getCoordinates("start location", rows, columns);
                board.addElement("S", startLocation[0], startLocation[1]);
                board.printBoard();

                // Get end coordinates //
                int[] endLocation = Message.getCoordinates("end location", rows, columns);
                while(board.checkCoordinateConflict(endLocation[0], endLocation[1])) {
                    Message.coordinateAlreadyInUse();
                    endLocation = Message.getCoordinates("end location", rows, columns);
                }
                board.addElement("E", endLocation[0], endLocation[1]);
                board.printBoard();

                // Get obstacle coordinates //
                while(Message.addAnotherObstacle()) {
                    int[] obstacleLocation = Message.getCoordinates("obstacle", rows, columns);
                    while(board.checkCoordinateConflict(obstacleLocation[0], obstacleLocation[1])) {
                        Message.coordinateAlreadyInUse();
                        obstacleLocation = Message.getCoordinates("obstacle", rows, columns);
                    }
                    board.addElement("X", obstacleLocation[0], obstacleLocation[1]);
                    board.printBoard();
                }
            }

            // Algorithm //
            Message.space();

            int shortestDistance = Algorithm.shortestDistance(board.getBoard());
            Message.shortestDistanceMessage(shortestDistance);

            int connectedObstacleCount = Algorithm.connectedObstacleCount(board.getBoard());
            Message.connectedObstacleCountMessage(connectedObstacleCount);

            int largestConnectedObstacleCount = Algorithm.largestConnectedObstacleCount(board.getBoard());
            Message.largestObstacleCountMessage(largestConnectedObstacleCount);

            Message.space();

            // End //
            run = Message.repeatApplication();
        }
    }
}