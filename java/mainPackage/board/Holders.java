package mainPackage.board;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mainPackage.coins.Coin;


public class Holders {
    //data field
    /*
     * the position of the holder is x,y and square to display and pane to hold the objects in the square
     * to know the status of the square if it is a coin, opponent, king , movable
     */
    //=============================================================================================//
    public int x;
    public int y;
    private Rectangle rect;
    private StackPane stPane;
    public Coin myCoin;
    public boolean isCoin = false;
    public boolean isOpponent = false;
    public boolean isMoovable = false;
    public boolean isKing = false;

    //constructor
    //=============================================================================================//
    public Holders(Color color) {
        rect = new Rectangle(80, 80, color);
        stPane = new StackPane(rect);
    }

    //getter and setter
    //=============================================================================================//
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public StackPane getHolder() {
        return stPane;
    }

    //to set and remove the coin in the holder ; this fix the coin in the holder and sets the status
    //=============================================================================================//
    public void setCoin(Coin thisCoin) {
        myCoin = thisCoin;
        try {
            stPane.getChildren().add(1, myCoin);
            if (!myCoin.isblack)
                isCoin = true;
            else
                isOpponent = true;
            if (myCoin.isKing)
                isKing = true;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //remove the coin if there is a coin
    public Coin removeCoin() {
        Coin temp = null;
        if (isCoin || isOpponent) {
            temp = (Coin) stPane.getChildren().remove(1);
            isCoin = false;
            isOpponent = false;
            isKing = false;
        }
        return temp;
    }

    //to change the color of the square
    //=============================================================================================//
    public void changeColor(Color color) {
        rect.setFill(color);
    }

    //to set and remove cursor
    //=============================================================================================//
    public void addCursor(Rectangle cursor) {
        stPane.getChildren().remove(0);
        stPane.getChildren().add(0, cursor);
    }

    public void removeCursor() {
        stPane.getChildren().remove(0);
        stPane.getChildren().add(0, rect);
    }

    //to set the current position of the coin
    //=============================================================================================//
    public void setCoinPos(int x, int y) {
        myCoin.setX(x);
        myCoin.setY(y);
    }

}
