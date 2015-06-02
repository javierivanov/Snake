package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;


/**
 *
 * @author javier
 */
public class Motor implements KeyListener{

    public Point culebrita;
    public Point[] viborita;
    public int largo;
    public Point comida;
    public Dimension size;
    public String last;
    private int speed = 500;
    public int psize;
    public Point cola;
    public boolean alive = true;
    public boolean pause = false;
    public Motor(Dimension size, int psize)
    {
        this.size = size;
        this.psize = psize;
        last="der";
        viborita = new Point[150];
        largo = 2;
        viborita[1] = new Point(0, 0);
        viborita[0] = new Point(psize,0);
        cola = viborita[1];
        culebrita = new Point(0, 0);
        comida = new Point(5*psize, 5*psize);
        Thread t1;
        t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                int counter=0;
                while(alive)
                {
                    
                    try{
                        Thread.sleep(speed);
                    } catch(Exception e){}
                    if (!pause)
                    {
                        if (counter++ == 4)
                        {
                            counter=0;
                            speed-=50;
                        }
                        if (speed<70) speed=70;
                        mover();
                        estaViva();
                        hayComida();
                    }
                }
            }
        });
        t1.start();
    }

    public boolean estaViva()
    {
        return false;
    }

    public void mover()
    {
        cola = new Point(viborita[largo-1]);
        for (int i=0; i < largo-1; i++)
        {
            viborita[largo-1-i] = viborita[largo-2-i];
        }
        
        if ("der".equals(last))
        {
            viborita[0] = new Point(viborita[0].x+psize, viborita[0].y);
        }
        if ("izq".equals(last))
        {
            viborita[0] = new Point(viborita[0].x-psize, viborita[0].y);
        }
        if ("aba".equals(last))
        {
            viborita[0] = new Point(viborita[0].x, viborita[0].y+psize);
        }
        if ("arr".equals(last))
        {
            viborita[0] = new Point(viborita[0].x, viborita[0].y-psize);
        }
        
    }
    
    public void nuevoElemento()
    {
        viborita[largo++] = cola;
    }
    
    public boolean hayComida()
    {
        if (viborita[0].x == comida.x && viborita[0].y == comida.y)
        {
            nuevoElemento();
            while (true)
            {
                
                comida.y = (int)(Math.random()*(size.height/psize-1))*psize;
                comida.x = (int)(Math.random()*(size.width/psize-1))*psize;
                boolean next=false;
                for (int i=0; i < largo; i++)
                {
                    if (comida.equals(viborita[i]))
                    {
                        next = true;
                    }
                }
                if (!next) break;
            }
            return true;
        }
        return false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) last = "arr";
        if (e.getKeyCode() == KeyEvent.VK_DOWN) last = "aba";
        if (e.getKeyCode() == KeyEvent.VK_LEFT) last = "izq";
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) last = "der";
        if (e.getKeyCode() == KeyEvent.VK_P) pause = !pause;
        if (e.getKeyCode() == KeyEvent.VK_Q) System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
