
package snake;

import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author javier
 */
public class Core {
    public GameLogic lg;
    public Agent agent;
    public GameState player1, player2;
    public Panel p1, p2;
    public Window window;
    public Dimension size;
    public int psize;
    
    public Core(Dimension dim, int psize)
    {
        JOptionPane.showMessageDialog(null, "Para pausar presione (P), para Salir (Q)");
        this.size = dim;
        this.psize = psize;
        lg = new GameLogic(this);
        agent = new Agent(this);
        window = new Window(this);
        window.setVisible(true);
    }
}
