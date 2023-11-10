package continuedtest;

public class Square {
    private int row;
    private int col;
    private boolean hasShip;
    private Battleship battleship;
    private boolean isShot;

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.hasShip = false;
        this.isShot = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasShip() {
        return hasShip;
    }

    public Battleship getBattleship() {
        return battleship;
    }

    public void setBattleship(Battleship battleship) {
        this.battleship = battleship;
        this.hasShip = true;
    }

    public boolean isShot() {
        return isShot;
    }

    public void shoot() {
        this.isShot = true;
    }

    @Override
    public String toString() {
        if (isShot) {
            if (hasShip) {
                return " X ";
            } else {
                return " O ";
            }
        } else {
            return hasShip ? " S " : " - ";
        }
    }
}