package DataPattern;


import Player.Player;

import java.io.Serializable;

public class PlayerStatePacket implements Serializable {
    public int linePullForce;
    public Player.Team gameState;

    public PlayerStatePacket(int linePullForce, Player.Team gameState){
        this.linePullForce = linePullForce;
        this.gameState = gameState;
    }

}
