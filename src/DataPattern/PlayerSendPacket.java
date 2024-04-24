package DataPattern;


import MainProgram.Player;

import java.io.Serializable;

public class PlayerSendPacket implements Serializable {
    int linePullForce;
    Player.Team gameState;

    public PlayerSendPacket(int linePullForce, Player.Team gameState){
        this.linePullForce = linePullForce;
        this.gameState = gameState;
    }

}
