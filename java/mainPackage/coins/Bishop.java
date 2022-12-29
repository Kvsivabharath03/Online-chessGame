package mainPackage.coins;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mainPackage.board.Holders;

public class Bishop extends Coin {
    //constructor
    //=============================================================================================//
    public Bishop(int x, int y, boolean isBlack) {
        this.x = x;
        this.y = y;
        this.isblack = isBlack; //to fix the color
        display();              //to display the coin
    }

    //to display the coin
    //=============================================================================================//
    @Override
    protected void display() {
        Text text = new Text("B");
        text.setFont(new Font("Verdana", 18));
        if (isblack)
            this.getChildren().addAll(new Rectangle(25, 35, Color.BROWN), text);
        else
            this.getChildren().addAll(new Rectangle(25, 35, Color.AQUA), text);
    }

    /*
     * this displays the moves of the coin
     * the left and right cross
     * this stops if there is an opponent or the end of the board or there is our coin
     */
    //=============================================================================================//
    @Override
    public void disMoves(Holders holder[][]) {
        if (!isblack) {
            for (int i = x + 1, j = y + 1; i < 8 && j < 8 && !holder[i][j].isCoin; ++i, ++j) {
                holder[i][j].changeColor(Color.web("F58ED3FF"));
                holder[i][j].isMoovable = true;
                if (holder[i][j].isOpponent)
                    break;
            }
            for (int i = x + 1, j = y - 1; i < 8 && j >= 0 && !holder[i][j].isCoin; ++i, --j) {
                holder[i][j].changeColor(Color.web("F58ED3FF"));
                holder[i][j].isMoovable = true;
                if (holder[i][j].isOpponent)
                    break;
            }
            for (int i = x - 1, j = y + 1; i >= 0 && j < 8 && !holder[i][j].isCoin; --i, ++j) {
                holder[i][j].changeColor(Color.web("F58ED3FF"));
                holder[i][j].isMoovable = true;
                if (holder[i][j].isOpponent)
                    break;
            }
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0 && !holder[i][j].isCoin; --i, --j) {
                holder[i][j].changeColor(Color.web("F58ED3FF"));
                holder[i][j].isMoovable = true;
                if (holder[i][j].isOpponent)
                    break;
            }
        }

    }
}
