package mainPackage.running;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mainPackage.board.Board;

import java.io.*;
import java.net.Socket;

public class Main extends Application {
    /*
     * data field has the board to display the socket to connect the server
     * input thread and io streams
     * and the status like gameover, won
     * message to save the message
     * and the game thread
     */
    //=============================================================================================//
    private Board board = new Board();
    private Socket socket;
    private Thread inputThread;
    private DataInputStream input;
    private DataOutputStream output;
    public static boolean gameover = false;
    public static boolean won = false;
    private Message message = new Message();
    private Thread gameTh;
    Stage stage = new Stage();

    //the init method connects socket to server and get the io streams
    //=============================================================================================//
    @Override
    public void init() {
        try {
            socket = new Socket("localhost", 8080);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * the main running method the start method of the application class
     * set the scene and the stage features
     * start game thread and give the moves to play and call network method of network actions
     * the game thread just checks the game over condition endlessly and call endgame if game ends
     */
    //=============================================================================================//
    @Override
    public void start(Stage stage1) {
        //the chess board
        Scene scene = new Scene(board);
        board.main = this;
        stage.setTitle("CHESS BOARD");
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.setWidth(656);
        stage.setHeight(680);
        stage.setResizable(false);
        stage.show();

        gameTh = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                if (gameover) {
                    endGame(scene);
                    break;
                }
            }
        });
        gameTh.start();
        givePlay(scene);//to give the event handling to the board
        network();
    }

    /*
     * it starts the input thread
     * the thread read the input from the server and send to the board to move the opponent coin
     * this happens endlessly
     */
    //=============================================================================================//
    private void network() {
        //this is where the game thread is defined and running of the game happens
        inputThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                try {
                    int cx = input.readInt();
                    int cy = input.readInt();
                    int selectedx = input.readInt();
                    int selectedy = input.readInt();

                    Platform.runLater(() ->
                            board.moveCoin(7 - cx, 7 - cy,
                                    7 - selectedx, 7 - selectedy)
                    );
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });
        inputThread.start();
    }

    //this write the message to the output stream
    //=============================================================================================//
    public void sendMessage() {
        try {
            output.writeInt(message.cx);
            output.writeInt(message.cy);
            output.writeInt(message.selectedx);
            output.writeInt(message.selectedy);
            output.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //this just display whether you win or lose after the game ends
    //=============================================================================================//
    private void endGame(Scene scene) {
        Platform.runLater(() -> {
            Text text = new Text((won) ? "You Win :)" : "You lose :(");
            text.setFill(Color.CORAL);
            StackPane pane = new StackPane(text);
            pane.setMaxWidth(300);
            pane.setMaxHeight(300);
            text.setFont(new Font("Verdana", 45));
            Scene scene1 = new Scene(new StackPane(board, pane));
            stage.setScene(scene1);
            scene.setOnKeyPressed((keyEvent) -> {
            });
        });
    }

    //it handles the keyboard inputs
    //=============================================================================================//
    public void givePlay(Scene scene) {
        //to handle key events
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP -> board.moveCursor('U');

                case DOWN -> board.moveCursor('D');

                case LEFT -> board.moveCursor('L');

                case RIGHT -> board.moveCursor('R');

                case ENTER -> board.cursorAction(message);

            }
        });
    }


    //=============================================================================================//
    @Override
    public void stop() {
        System.exit(5);
    }

    //main function
    //=============================================================================================//
    public static void main(String[] args) {
        launch();
    }

}