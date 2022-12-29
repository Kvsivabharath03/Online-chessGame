package mainPackage.coins;

import javafx.scene.layout.StackPane;
import mainPackage.board.Holders;

public abstract class Coin extends StackPane {
    //data segment
    //=============================================================================================//
    protected int x;   //the position of the coin in the board
    protected int y;   //x - column y - row

    //=============================================================================================//
    public boolean isblack = true;
    public boolean isKing = false;

    //to make moves
    //=============================================================================================//
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //=============================================================================================//
    protected abstract void display();

    public abstract void disMoves(Holders holders[][]);
}
