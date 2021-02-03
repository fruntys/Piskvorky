package cz.osu.kip.TTT.services;

public class BoardService {
    private Board board;

    public BoardService() {
        this.board = new Board();
    }

    public void move(int row, int col, String s){
        this.board.makeMoveOnBoard(row, col, s);
    }

    public void printBoard(){
        board.print();
        System.out.println();
    }

    public Board getBoard() {
        return board;
    }
}
