package Player;

import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class PlayerMain {
    public static void main(String args[]){
        //Player.Team team = i < (float)amountOfPlayers/2 ? Player.Team.left : Player.Team.right;
        Socket clientSocket = new Socket();
        try {
            createUI();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("Stworzono gracza " + i + " gra on w druzynie " + team);
    }
}
