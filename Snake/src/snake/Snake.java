
package snake;

import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author javier
 */
public class Snake {

    public static void main(String[] args) {
        // TODO code application logic here
        JOptionPane.showMessageDialog(null, "Para pausar presione (P), para Salir (Q)");
        Motor m = new Motor(new Dimension(500, 500), 20);
        new Ventana(m);
    }
    
}
