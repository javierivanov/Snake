
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
        setSize(m.size);
        setLayout(null);
        add(new Panel(m, m.size));
        this.m = m;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(m);
        setLocationRelativeTo(null);
<<<<<<< HEAD
        //setUndecorated(true);
=======
        setUndecorated(true);
        JOptionPane.showMessageDialog(null, "Para pausar presione (P), para Salir (Q)");
        
>>>>>>> full_snake
        setVisible(true);
    }
}
