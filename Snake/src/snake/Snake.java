
package snake;

import java.awt.Dimension;

/**
 *
 * @author javier
 */
public class Snake {

    public static void main(String[] args) {
        // TODO code application logic here
        Motor m = new Motor(new Dimension(600, 600), 20);
        new Ventana(m);
    }
    
}
