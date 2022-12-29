package mainPackage.board;


import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mainPackage.coins.*;
import mainPackage.running.Main;
import mainPackage.running.Message;

public class Board extends GridPane {
    //data field
    /*
     * the cx and cy holds the position of the cursor in the board always
     * selected x and y are the position of the coin that is selected and to make a move
     * the main is the main class that runs the application
     * then the other objects in the board are declared
     */
    private int cx = 4;
    private int cy = 6;
    private int selectedx = 0;
    private int selectedy = 0;
    public Main main;

    private Rectangle cursor = new Rectangle(80, 80, Color.web("6AADFFE4"));
    private Holders[][] holder;//holder[column][row]
    private Soldier[] bSoldiers, wSoldiers;
    private Rook[] bRook, wRook;
    private Bishop[] bBishop, wBishop;
    private Knight[] bKnight, wKnight;
    private Queen bQueen, wQueen;
    private King bKing, wKing;

    //constructor
    /*
     * the constructor first initializes the objects
     * the color of the squares are fixed and board is set the holder are given position
     * the method to fix coins and cursor is called
     */
    //=============================================================================================//
    public Board() {
        //the 64 squares on the chess board
        holder = new Holders[8][8];
        //coins
        bSoldiers = new Soldier[8];
        wSoldiers = new Soldier[8];
        bRook = new Rook[2];
        wRook = new Rook[2];
        bKnight = new Knight[2];
        wKnight = new Knight[2];
        bBishop = new Bishop[2];
        wBishop = new Bishop[2];

        //to set the color of each square
        for (int i = 0; i < 8; i += 2) {
            for (int j = 0; j < 8; j++) {
                holder[i + 1][j] = new Holders((j % 2 == 0) ? Color.WHITE : Color.BLACK);//decide the color of the square
                holder[i][j] = new Holders((j % 2 == 0) ? Color.BLACK : Color.WHITE);//decide the color of the square
                holder[i][j].setY(j);//set the positions
                holder[i + 1][j].setY(j);
                holder[i][j].setX(i);
                holder[i + 1][j].setX(i + 1);
                this.add(holder[i][j].getHolder(), i, j);//to set the square in the correct position
                this.add(holder[i + 1][j].getHolder(), i + 1, j);
            }
        }
        //to set all the coins in the position
        setCoins();
        setCursor();
    }

    //to set the cursor in positon
    //=============================================================================================//
    private void setCursor() {
        holder[cx][cy].addCursor(cursor);
    }
    //=============================================================================================//


    //to set the coins in the squares
    /*
     * first the black coins are set and then the white coins
     * in each block the soldiers are set first
     * then the other coins are placed in the holder
     */
    //=============================================================================================//
    private void setCoins() {
        //black coins
        int j = 1;
        //black soldiers
        for (int i = 0; i < 8; ++i) {
            bSoldiers[i] = new Soldier(i, j, true);
            holder[i][j].setCoin(bSoldiers[i]);
        }
        //rook
        bRook[0] = new Rook(0, 0, true);
        holder[0][0].setCoin(bRook[0]);
        bKnight[0] = new Knight(1, 0, true);
        holder[1][0].setCoin(bKnight[0]);
        bBishop[0] = new Bishop(2, 0, true);
        holder[2][0].setCoin(bBishop[0]);
        bKing = new King(3, 0, true);
        holder[3][0].setCoin(bKing);
        bQueen = new Queen(4, 0, true);
        holder[4][0].setCoin(bQueen);
        bBishop[1] = new Bishop(5, 0, true);
        holder[5][0].setCoin(bBishop[1]);
        bKnight[1] = new Knight(6, 0, true);
        holder[6][0].setCoin(bKnight[1]);
        bRook[1] = new Rook(7, 0, true);
        holder[7][0].setCoin(bRook[1]);


        //white coins
        j = 6;
        //white soldiers
        for (int i = 0; i < 8; ++i) {
            wSoldiers[i] = new Soldier(i, j, false);
            holder[i][j].setCoin(wSoldiers[i]);
        }
        wRook[0] = new Rook(0, 7, false);
        holder[0][7].setCoin(wRook[0]);
        wKnight[0] = new Knight(1, 7, false);
        holder[1][7].setCoin(wKnight[0]);
        wBishop[0] = new Bishop(2, 7, false);
        holder[2][7].setCoin(wBishop[0]);
        wKing = new King(4, 7, false);
        holder[4][7].setCoin(wKing);
        wQueen = new Queen(3, 7, false);
        holder[3][7].setCoin(wQueen);
        wBishop[1] = new Bishop(5, 7, false);
        holder[5][7].setCoin(wBishop[1]);
        wKnight[1] = new Knight(6, 7, false);
        holder[6][7].setCoin(wKnight[1]);
        wRook[1] = new Rook(7, 7, false);
        holder[7][7].setCoin(wRook[1]);
    }

    //move the cursor
    /*
     * here the direction to be moved is sent are argument
     * based on the input the cursor is moved up,down,left and right
     * just the cursor's position is changed
     */
    //=============================================================================================//
    public void moveCursor(char direction) {
        switch (direction) {
            case 'U' -> {
                holder[cx][cy].removeCursor();
                cy = (cy - 1 >= 0) ? cy - 1 : cy;
                setCursor();
            }

            case 'D' -> {
                holder[cx][cy].removeCursor();
                cy = (cy + 1 <= 7) ? cy + 1 : cy;
                setCursor();
            }
            case 'L' -> {
                holder[cx][cy].removeCursor();
                cx = (cx - 1 >= 0) ? cx - 1 : cx;
                setCursor();
            }
            case 'R' -> {
                holder[cx][cy].removeCursor();
                cx = (cx + 1 <= 7) ? cx + 1 : cx;
                setCursor();
            }
        }
    }

    //to the square is selected
    /*
     * if the enter key is pressed
     * it checks three condition
     * there is a coin then set as selected and display the moves
     * that is one of the movable square to move the coin
     * save this move to the message and send to the opponent machine
     * disable the input to wait of the opponent move
     */
    //=============================================================================================//
    public void cursorAction(Message message) {
        if (holder[cx][cy].isCoin) {
            selectedx = cx;
            selectedy = cy;
            clearBoard();
            holder[cx][cy].myCoin.disMoves(holder);
        } else if (holder[cx][cy].isMoovable) {
            moveCoin(cx, cy, selectedx, selectedy);
            message.cx = cx;
            message.cy = cy;
            message.selectedx = selectedx;
            message.selectedy = selectedy;
            message.hasMessage = true;
            Scene scene = this.getScene();
            scene.setOnKeyPressed(keyEvent -> {//to deactivate the input to wait for the opponent to play
            });
            main.sendMessage();
        } else {
            clearBoard();
        }
    }

    /*
     * first checks if the place to be moved is a king then is our king or the opponent's king
     * either way the game ends
     * if it is our king then we lose the game since our king is dead
     * if the opponent's king is dead then we win
     * the coin is moved and the board is cleared
     * the ability to move the coin is given
     */
    //=============================================================================================//
    public void moveCoin(int cx, int cy, int selectedx, int selectedy) {
        if (holder[cx][cy].isKing) {
            Main.gameover = true;
            if (holder[cx][cy].isOpponent) {
                Main.won = true;
            }
        }
        holder[cx][cy].removeCoin();
        holder[selectedx][selectedy].setCoinPos(cx, cy);//to set the current position of the coin
        holder[cx][cy].setCoin(holder[selectedx][selectedy].removeCoin());
        clearBoard();
        main.givePlay(this.getScene());
    }

    //to clear the color of the board
    //=============================================================================================//
    private void clearBoard() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                holder[i][j].changeColor(((i + j) % 2 == 0) ? Color.BLACK : Color.WHITE);
                holder[i][j].isMoovable = false;
            }
        }
        setCursor();
    }
    //=============================================================================================//
}
