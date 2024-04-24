package DataPattern;

import java.io.Serializable;

public class GameSendPacket implements Serializable {
    int line;

    public GameSendPacket(int line){
        this.line = line;
    }
}
