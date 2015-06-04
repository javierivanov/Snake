
package snake;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author javier
 */

public class GameState implements Serializable{
    public Point[] viborita;
    public int largo;
    public Point comida;
    public int score;
    public int speed;
    public boolean alive;
    public boolean pause;

    public GameState(Point[] viborita, int largo, Point comida, int score, int speed, boolean alive, boolean pause) {
        this.viborita = viborita;
        this.largo = largo;
        this.comida = comida;
        this.score = score;
        this.speed = speed;
        this.alive = alive;
        this.pause = pause;
    }
    
}
