
package snake;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author javier
 */
public class Ventana extends JFrame{
    Motor m;
    public Ventana(Motor m)
    {
        super("Snake!");
        setSize(m.size.width, m.size.height+20);
        setLayout(null);
        add(new Panel(m, m.size));
        this.m = m;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(m);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }
}
