package com.Battleship;

import java.util.Random;

public class Board {
    private Square[][] grid;
    private int rows;
    private int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Square[rows][cols];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Square(i, j);
            }
        }
    }

    public void populateBoardRandomly() {
        Random random = new Random();

        // Populate SmallBattleships
        placeRandomBattleships(SmallBattleship.class, SmallBattleship.TOTAL_PERMISSIBLE_SHIPS, random);

        // Populate MediumBattleships
        placeRandomBattleships(MediumBattleship.class, MediumBattleship.TOTAL_PERMISSIBLE_SHIPS, random);

        // Populate LargeBattleships
        placeRandomBattleships(LargeBattleship.class, LargeBattleship.TOTAL_PERMISSIBLE_SHIPS, random);
    }

    private void placeRandomBattleships(Class<? extends Battleship> battleshipType, int totalShips, Random random) {
        for (int i = 0; i < totalShips; i++) {
            Battleship ship;
            try {
                ship = battleshipType.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            int size = ship.getSize();
            boolean isVertical = random.nextBoolean();

            int row, col;
            do {
                row = random.nextInt(rows);
                col = random.nextInt(cols);
            } while (!isValidPlacement(row, col, size, isVertical));

            for (int j = 0; j < size; j++) {
                if (isVertical) {
                    grid[row + j][col].setBattleship(ship);
                } else {
                    grid[row][col + j].setBattleship(ship);
                }
            }
        }
    }

    private boolean isValidPlacement(int row, int col, int size, boolean isVertical) {
        if (isVertical) {
            if (row + size > rows) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (grid[row + i][col].hasShip()) {
                    return false;
                }
            }
        } else {
            if (col + size > cols) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (grid[row][col + i].hasShip()) {
                    return false;
                }
            }
        }

        return true;
    }

    public Square getSquare(int row, int col) {
        return grid[row][col];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("  ");
        for (int i = 0; i < cols; i++) {
            result.append(String.format("%3d", i));
        }
        result.append("\n");

        for (int i = 0; i < rows; i++) {
            result.append((char) ('0' + i)).append(" ");
            for (int j = 0; j < cols; j++) {
                Square square = grid[i][j];
                result.append(square.isShot() ? (square.hasShip() ? " X " : " O ") : (square.hasShip() ? " S " : " - "));
            }
            result.append("\n");
        }

        return result.toString();
    }
}