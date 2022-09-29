import java.util.Random;

public class Board {

    private int row;
    private int col;
    private String[][] board;

    public Board(int row, int col) {
        this.row = row;
        this.col = col;
        this.board = new String[row][col];
    }

    public String[][] getBoard() {
        return board;
    }


    // Methods //
    public void populateBoard() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = "[ ]";
            }
        }
    }

    public void printBoard() {
        System.out.println("");
        for(int r=0; r<board.length; r++) {
            for(int c=0; c<board[0].length; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println();
        }
        System.out.println("");
    }

    public void randomize() {
        Random rand = new Random();
        board[rand.nextInt(row)][rand.nextInt(col)] = "[S]";

        boolean conflict = true;
        while(conflict) {
            int tryRow = rand.nextInt(row);
            int tryCol = rand.nextInt(col);
            if (board[tryRow][tryCol].contains("[ ]")) {
                board[tryRow][tryCol] = "[E]";
                conflict = false;
            }
        }

        for(int obstacle = 0; obstacle<(row*col)/3; obstacle++) {
            conflict = true;
            while(conflict) {
                int tryRow = rand.nextInt(row);
                int tryCol = rand.nextInt(col);
                if(board[tryRow][tryCol].contains("[ ]")) {
                    board[tryRow][tryCol] = "[X]";
                    conflict = false;
                }
            }
        }
    }

    public void addElement(String element, int row, int col) {
        board[row][col] = "["+element+"]";
    }

    public boolean checkCoordinateConflict(int row, int col) {
        if(board[row][col] != "[ ]") return true;
        return false;
    }
}
