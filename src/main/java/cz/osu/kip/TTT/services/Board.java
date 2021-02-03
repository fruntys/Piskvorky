package cz.osu.kip.TTT.services;

public class Board {
    private String cells[][];

    public Board() {
        this.cells = new String[15][15];
        fillBoardWithSpaces();
    }

    public void fillBoardWithSpaces(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = " ";
            }
        }
    }

    public void makeMoveOnBoard(int row, int col, String s){
        this.cells[row][col] = s;
    }

    public void print() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print("[" + cells[i][j] + "]");
            }
            System.out.println();
        }
    }

    public String[][] getCells() {
        return cells;
    }

    public String getCell(int row, int col){
        return cells[row][col];
    }
}
