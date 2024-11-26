package com.example.a2048;

import java.util.Random;

public class Logica {
    private int[][] board;
    private Random random;

    public Logica() {
        board = new int[4][4];
        random = new Random();
        addRandomTile();
        addRandomTile();
    }

    private void addRandomTile() {
        int x, y;
        do {
            x = random.nextInt(4);
            y = random.nextInt(4);
        } while (board[x][y] != 0);
        board[x][y] = random.nextInt(10) == 0 ? 4 : 2;
    }

    public int[][] getBoard() {
        return board;
    }

    public void moveLeft() {
        boolean needAddTile = false;
        for (int i = 0; i < 4; i++) {
            int[] newRow = new int[4];
            int pos = 0;
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    if (pos > 0 && newRow[pos - 1] == board[i][j]) {
                        newRow[pos - 1] *= 2;
                        needAddTile = true;
                    } else {
                        newRow[pos++] = board[i][j];
                    }
                }
            }
            if (!needAddTile) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] != newRow[j]) {
                        needAddTile = true;
                        break;
                    }
                }
            }
            board[i] = newRow;
        }
        if (needAddTile) {
            addRandomTile();
        }
    }

    public void moveRight() {
        rotateBoard();
        rotateBoard();
        moveLeft();
        rotateBoard();
        rotateBoard();
    }

    public void moveUp() {
        rotateBoard();
        rotateBoard();
        rotateBoard();
        moveLeft();
        rotateBoard();
    }

    public void moveDown() {
        rotateBoard();
        moveLeft();
        rotateBoard();
        rotateBoard();
        rotateBoard();
    }

    private void rotateBoard() {
        int[][] newBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newBoard[j][3 - i] = board[i][j];
            }
        }
        board = newBoard;
    }
}