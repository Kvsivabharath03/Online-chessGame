package ServerPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    //the data field
    //=============================================================================================//
    ServerSocket server1;
    Socket socket1;
    Socket socket2;
    DataInputStream input1;
    DataInputStream input2;
    DataOutputStream output1;
    DataOutputStream output2;
    Thread serverTh1;

    /*
     * it starts the server thread
     * first accepts connection and provide io streams
     * do the same to the next user
     * endlessly receive message from the user1 and send to user2
     * similarly receive message from the user2 and send to user1
     */
    //=============================================================================================//
    public MyServer() {
        serverTh1 = new Thread(() -> {
            //the input thread
            try {
                server1 = new ServerSocket(8080);
                socket1 = server1.accept();
                output1 = new DataOutputStream(socket1.getOutputStream());
                input1 = new DataInputStream(socket1.getInputStream());

                socket2 = server1.accept();
                output2 = new DataOutputStream(socket2.getOutputStream());
                input2 = new DataInputStream(socket2.getInputStream());
                while (true) {
                    Thread.sleep(100);
                    output2.writeInt(input1.readInt());
                    output2.writeInt(input1.readInt());
                    output2.writeInt(input1.readInt());
                    output2.writeInt(input1.readInt());
                    //the opponent move
                    output1.writeInt(input2.readInt());
                    output1.writeInt(input2.readInt());
                    output1.writeInt(input2.readInt());
                    output1.writeInt(input2.readInt());
                }

            } catch (Exception e) {
                System.out.println(e);
                System.exit(4);
            }
        });

        serverTh1.start();
    }

    //the main method
    //=============================================================================================//
    public static void main(String[] args) {
        new MyServer();
    }
}
