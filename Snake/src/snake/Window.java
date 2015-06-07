
package snake;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author javier
 */
public class Window extends JFrame{
    private Core core;
    public Window(Core core)
    {
        super("Snake!");
        this.core = core;
        setSize(core.size.width*2, (core.size.height+20));
        setLayout(new GridLayout(1,2));
        add(new Panel(core, core.player1));
        add(new Panel(core, core.player1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(core.lg);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }
}
