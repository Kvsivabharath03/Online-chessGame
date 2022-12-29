package mainPackage.coins;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mainPackage.board.Holders;

public class Knight extends Coin {

    //constructor
    //=============================================================================================//
    public Knight(int x, int y, boolean isBlack) {
        this.x = x;
        this.y = y;
        this.isblack = isBlack; //to fix the color
        display();              //to display the coin
    }

    //to display the coin
    //=============================================================================================//
    @Override
    protected void display() {
        Text text = new Text("Kn");
        text.setFont(new Font("Verdana", 18));
        if (isblack)
            this.getChildren().addAll(new Rectangle(30, 25, Color.BROWN), text);
        else
            this.getChildren().addAll(new Rectangle(30, 25, Color.AQUA), text);
    }

    /*
     * this does not check if there is a coin in the way
     * it just checks is the square to be moved is in the board and
     * there no coin of the same side
     */
    //=============================================================================================//
    @Override
    public void disMoves(Holders holder[][]) {
        if (!isblack) {
            if (y - 2 >= 0 && x - 1 >= 0 && !holder[x - 1][y - 2].isCoin) {//
                holder[x - 1][y - 2].changeColor(Color.web("F58ED3FF"));
                holder[x - 1][y - 2].isMoovable = true;
            }
            if (y - 2 >= 0 && x + 1 < 8 && !holder[x + 1][y - 2].isCoin) {//
                holder[x + 1][y - 2].changeColor(Color.web("F58ED3FF"));
                holder[x + 1][y - 2].isMoovable = true;
            }
            if (y + 2 < 8 && x + 1 < 8 && !holder[x + 1][y + 2].isCoin) {//
                holder[x + 1][y + 2].changeColor(Color.web("F58ED3FF"));
                holder[x + 1][y + 2].isMoovable = true;
            }
            if (y + 2 < 8 && x - 1 >= 0 && !holder[x - 1][y + 2].isCoin) {//
                holder[x - 1][y + 2].changeColor(Color.web("F58ED3FF"));
                holder[x - 1][y + 2].isMoovable = true;
            }
            if (x - 2 >= 0 && y - 1 >= 0 && !holder[x - 2][y - 1].isCoin) {//
                holder[x - 2][y - 1].changeColor(Color.web("F58ED3FF"));
                holder[x - 2][y - 1].isMoovable = true;
            }
            if (x - 2 >= 0 && y + 1 < 8 && !holder[x - 2][y + 1].isCoin) {//
                holder[x - 2][y + 1].changeColor(Color.web("F58ED3FF"));
                holder[x - 2][y + 1].isMoovable = true;
            }
            if (x + 2 < 8 && y - 1 >= 0 && !holder[x + 2][y - 1].isCoin) {
                holder[x + 2][y - 1].changeColor(Color.web("F58ED3FF"));
                holder[x + 2][y - 1].isMoovable = true;
            }
            if (x + 2 < 8 && y + 1 < 8 && !holder[x + 2][y + 1].isCoin) {
                holder[x + 2][y + 1].changeColor(Color.web("F58ED3FF"));
                holder[x + 2][y + 1].isMoovable = true;
            }

        }
    }
}
