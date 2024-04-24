package DataPattern;

import java.io.Serializable;

public class GameSendPacket implements Serializable {
    public int line;

    public GameSendPacket(int line){
        this.line = line;
    }
}
