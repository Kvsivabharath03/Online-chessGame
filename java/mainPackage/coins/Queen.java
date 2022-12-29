package mainPackage.coins;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mainPackage.board.Holders;

public class Queen extends Coin {
    //constructor
    //=============================================================================================//
    public Queen(int x, int y, boolean isBlack) {
        this.x = x;
        this.y = y;
        this.isblack = isBlack; //to fix the color
        display();              //to display the coin
    }
    //=============================================================================================//

    //to display the coin
    //=============================================================================================//
    @Override
    protected void display() {
        Text text = new Text("Q");
        text.setFont(new Font("Verdana", 18));
        if (isblack)
            this.getChildren().addAll(new Rectangle(25, 35, Color.BROWN), text);
        else
            this.getChildren().addAll(new Rectangle(25, 35, Color.AQUA), text);
    }

    // this is just combining the moves of the rook and the bishop
    //=============================================================================================//
    @Override
    public void disMoves(Holders holder[][]) {
        if (!isblack) {
            for (int i = x + 1; i < 8 && !holder[i][y].isCoin; ++i) {
                holder[i][y].changeColor(Color.web("F58ED3FF"));
                holder[i][y].isMoovable = true;
                if (holder[i][y].isOpponent)
                    break;
            }
            for (int i = y + 1; i < 8 && !holder[x][i].isCoin; ++i) {
                holder[x][i].changeColor(Color.web("F58ED3FF"));
                holder[x][i].isMoovable = true;
                if (holder[x][i].isOpponent)
                    break;
            }
            for (int i = x - 1; i >= 0 && !holder[i][y].isCoin; --i) {
                holder[i][y].changeColor(Color.web("F58ED3FF"));
                holder[i][y].isMoovable = true;
                if (holder[i][y].isOpponent)
                    break;
            }
            for (int i = y - 1; i >= 0 && !holder[x][i].isCoin; --i) {
                holder[x][i].changeColor(Color.web("F58ED3FF"));
                holder[x][i].isMoovable = true;
                if (holder[x][i].isOpponent)
                    break;
            }
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
