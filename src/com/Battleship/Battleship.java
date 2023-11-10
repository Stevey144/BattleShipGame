package com.Battleship;

import java.util.*;


public class Battleship {
    private boolean isSunk;
    private int health;
    private int size;

    public Battleship(int size) {
        this.isSunk = false;
        this.health = size;
        this.size = size;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void hit() {
        health--;

        if (health == 0) {
            isSunk = true;
        }
    }

    public int getSize() {
        return size;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create the board
        Board board = new Board(10, 10);

        // Populate the board randomly with ships
        board.populateBoardRandomly();

        // Create players
        System.out.print("Enter Player 1 name: ");
        Player player1 = new Player(scanner.nextLine(), board);

        System.out.print("Enter Player 2 name: ");
        Player player2 = new Player(scanner.nextLine(), board);

        // Play the game
        while (!player1.isGameOver() && !player2.isGameOver()) {
            player1.takeTurn();
            if (player1.isGameOver()) {
                break;
            }

            player2.takeTurn();
        }

        // Display the final state of the board
        System.out.println("\nFinal State of the Board:");
        System.out.println(board.toString());

        // Display the winner
        if (player1.getScore() > player2.getScore()) {
            System.out.println(player1.getName() + " wins!" + " point is = " + player1.getScore());
        } else if (player1.getScore() < player2.getScore()) {
            System.out.println(player2.getName() + " wins!" + " point is = " + player2.getScore());
        } else {
            System.out.println("It's a draw!");
        }
    }
}
