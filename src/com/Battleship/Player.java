package com.Battleship;

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
        int guessRow=0;
        int guessCol=0; 
        
        while (true) {
        
        System.out.print("Enter your guess (row): ");
        
        if (scanner.hasNextInt()) {
        	 guessRow = scanner.nextInt();  // Read the first integer
        	 break; 
        }
        else {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // Consume the invalid input to prevent an infinite loop
            
        }
        
        }
       
        while (true) {
            System.out.print("Enter your guess (col): ");
            // Try to read an integer from the user            
            if (scanner.hasNextInt()) {
            	 guessCol = scanner.nextInt(); // Read the second integer         
                // Your code to work with these integers can go here
            	 break;
            }
	         else {
	            System.out.println("The second input is not an integer.");
	            scanner.next();  // Consume the invalid input to prevent an infinite loop
	        }
	        
    } 
        Square square = null;
        try {
        	  square = board.getSquare(guessRow, guessCol);   	
        }
        catch(ArrayIndexOutOfBoundsException e) {
        	System.out.println(e.getMessage() + ": Hint row(0-9) column(0-9)");
        	return;
        }
       

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
            	score++;
                System.out.println("You sunk a battleship! +1 point added, total point is = " + getScore() + " point");
            }
        } else {
            System.out.println("Miss! No battleship at this location.");
        }
    }
}