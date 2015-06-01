
package snake;

import java.awt.Dimension;

/**
 *
 * @author javier
 */
public class Snake {

    public static void main(String[] args) {
        // TODO code application logic here
        Motor m = new Motor(new Dimension(500, 500));
        new Ventana(m);
    }
    
}
