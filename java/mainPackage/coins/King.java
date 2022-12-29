package mainPackage.coins;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mainPackage.board.Holders;

public class King extends Coin {
    //constructor
    //=============================================================================================//
    public King(int x, int y, boolean isBlack) {
        this.x = x;
        this.y = y;
        this.isblack = isBlack;//to fix the color
        this.isKing = true;
        display();              //to display the coin
    }

    //to display the coin
    //=============================================================================================//
    @Override
    protected void display() {
        Text text = new Text("K");
        text.setFont(new Font("Verdana", 18));
        if (isblack)
            this.getChildren().addAll(new Rectangle(25, 35, Color.BROWN), text);
        else
            this.getChildren().addAll(new Rectangle(25, 35, Color.AQUA), text);
    }

    /*
     * this check one step all the side
     * it do not select the square only if there is our coin
     */
    //=============================================================================================//
    @Override
    public void disMoves(Holders holder[][]) {
        if (!isblack) {
            if (y - 1 >= 0 && !holder[x][y - 1].isCoin) {
                holder[x][y - 1].changeColor(Color.web("F58ED3FF"));
                holder[x][y - 1].isMoovable = true;
            }
            if (y + 1 < 8 && !holder[x][y + 1].isCoin) {
                holder[x][y + 1].changeColor(Color.web("F58ED3FF"));
                holder[x][y + 1].isMoovable = true;
            }
            if (x + 1 < 8 && !holder[x + 1][y].isCoin) {
                holder[x + 1][y].changeColor(Color.web("F58ED3FF"));
                holder[x + 1][y].isMoovable = true;
            }
            if (x - 1 >= 0 && !holder[x - 1][y].isCoin) {
                holder[x - 1][y].changeColor(Color.web("F58ED3FF"));
                holder[x - 1][y].isMoovable = true;
            }
            if (x + 1 < 8 && y - 1 >= 0 && !holder[x + 1][y - 1].isCoin) {
                holder[x + 1][y - 1].changeColor(Color.web("F58ED3FF"));
                holder[x + 1][y - 1].isMoovable = true;
            }
            if (x - 1 >= 0 && y - 1 >= 0 && !holder[x - 1][y - 1].isCoin) {
                holder[x - 1][y - 1].changeColor(Color.web("F58ED3FF"));
                holder[x - 1][y - 1].isMoovable = true;
            }
            if (x + 1 < 8 && y + 1 < 8 && !holder[x + 1][y + 1].isCoin) {
                holder[x + 1][y + 1].changeColor(Color.web("F58ED3FF"));
                holder[x + 1][y + 1].isMoovable = true;
            }
            if (x - 1 >= 0 && y + 1 < 8 && !holder[x - 1][y + 1].isCoin) {
                holder[x - 1][y + 1].changeColor(Color.web("F58ED3FF"));
                holder[x - 1][y + 1].isMoovable = true;
            }

        }
    }
}
