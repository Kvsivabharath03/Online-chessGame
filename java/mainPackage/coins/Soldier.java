package mainPackage.coins;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mainPackage.board.Holders;

public class Soldier extends Coin {

    //constructor
    //=============================================================================================//
    public Soldier(int x, int y, boolean isBlack) {
        this.x = x;
        this.y = y;
        this.isblack = isBlack; //to fix the color
        display();              //to display the coin
    }

    //to display the coin
    //=============================================================================================//
    @Override
    protected void display() {
        Text text = new Text("S");
        text.setFont(new Font("Verdana", 18));
        if (isblack)
            this.getChildren().addAll(new Rectangle(25, 35, Color.BROWN), text);
        else
            this.getChildren().addAll(new Rectangle(25, 35, Color.AQUA), text);
    }

    /*
     * check first square front if not a coin of end of the board can be moved
     * if first is movable then check the second the same
     * check the left and right front squares if there are opponent coin to kill
     */
    //=============================================================================================//
    @Override
    public void disMoves(Holders holder[][]) {
        if (!isblack) {
            if ((y - 1 >= 0) && !holder[x][y - 1].isCoin && !holder[x][y - 1].isOpponent) {
                holder[x][y - 1].changeColor(Color.web("F58ED3FF"));
                holder[x][y - 1].isMoovable = true;
                if ((y - 2 >= 0) && !holder[x][y - 2].isCoin && !holder[x][y - 2].isOpponent) {
                    holder[x][y - 2].changeColor(Color.web("F58ED3FF"));
                    holder[x][y - 2].isMoovable = true;
                }
            }
            if ((y - 1 >= 0) && (x - 1 >= 0) && holder[x - 1][y - 1].isOpponent) {
                holder[x - 1][y - 1].changeColor(Color.web("F58ED3FF"));
                holder[x - 1][y - 1].isMoovable = true;
            }
            if ((y - 1 >= 0) && (x + 1 < 8) && holder[x + 1][y - 1].isOpponent) {
                holder[x + 1][y - 1].changeColor(Color.web("F58ED3FF"));
                holder[x + 1][y - 1].isMoovable = true;
            }
        }

    }

}
