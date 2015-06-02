
package snake;

import javax.swing.JFrame;

/**
 *
 * @author javier
 */
public class Ventana extends JFrame{
    Motor m;
    public Ventana(Motor m)
    {
        super("Snake!");
        setSize(m.size);
        setLayout(null);
        add(new Panel(m, m.size));
        this.m = m;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(m);
        setLocationRelativeTo(null);
        //setUndecorated(true);
        setVisible(true);
    }
}
