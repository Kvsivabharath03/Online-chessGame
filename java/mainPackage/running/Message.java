package mainPackage.running;

import java.io.Serializable;

//this holds the message
public class Message implements Serializable {
    public int cx = 0;
    public int cy = 0;
    public int selectedx = 0;
    public int selectedy = 0;
    public boolean hasMessage = false;
}
