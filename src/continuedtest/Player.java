package continuedtest;

import java.util.Scanner;

public class Player{
    private String name;
    private Board board;
    private int score;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return score == SmallBattleship.TOTAL_PERMISSIBLE_SHIPS +
                MediumBattleship.TOTAL_PERMISSIBLE_SHIPS +
                LargeBattleship.TOTAL_PERMISSIBLE_SHIPS;
    }

    public void takeTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + name + ", it's your turn!");

        // Display the current state of the board
        System.out.println(board.toString());

        // Take user input for the guess
        System.out.print("Enter your guess (row col): ");
        int guessRow = scanner.nextInt();
        int guessCol = scanner.nextInt();

        Square square = board.getSquare(guessRow, guessCol);

        // Check if the square has already been shot
        if (square.isShot()) {
            System.out.println("You have already shot at this square. Try again.");
            takeTurn();
            return;
        }

        square.shoot();

        // Check if the square has a battleship
        if (square.hasShip()) {
            Battleship battleship = square.getBattleship();
            battleship.hit();

            System.out.println("Hit! " + battleship.getSize() + "-unit battleship damaged.");

            if (battleship.isSunk()) {
                System.out.println("You sunk a battleship! +1 point");
                score++;
            }
        } else {
            System.out.println("Miss! No battleship at this location.");
        }
    }
}