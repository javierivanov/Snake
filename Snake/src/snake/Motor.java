package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


/**
 *
 * @author javier
 */
public class Motor implements KeyListener{

    public Point culebrita;
    //public Point[] viborita;
    //public int largo;
    public Point comida;
    public Dimension size;
    public String last;
    private int speed = 500;
    public int psize = 20;
    public Motor(Dimension size)
    {
        this.size = size;
        last="der";
        //vivorita = new Point[20];
        //largo = 1;
        //vivorita = new Point(0,0);
        culebrita = new Point(0, 0);
        comida = new Point(5*psize, 5*psize);
        Thread t1;
        t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                int counter=0;
                while(true)
                {
                    if (counter++ == 4)
                    {
                        counter=0;
                        speed-=10;
                    }
                    if (speed<70) speed=70;
                    try{
                        Thread.sleep(speed);
                    } catch(Exception e){}
                    if ("der".equals(last)) moverDer();
                    if ("izq".equals(last)) moverIzq();
                    if ("aba".equals(last)) moverAba();
                    if ("arr".equals(last)) moverArr();
                    hayComida();
                }
            }
        });
        t1.start();
    }

    public boolean estaViva()
    {
        return true;
    }

    public boolean hayComida()
    {
        if (culebrita.x == comida.x && culebrita.y == comida.y)
        {
            comida.y = (int)(Math.random()*size.height/psize)*psize;
            comida.x = (int)(Math.random()*size.width/psize)*psize;
            return true;
        }
        return false;
    }

    public void moverIzq()
    {
        culebrita.x-=psize;
    }

    public void moverDer()
    {

        culebrita.x+=psize;
    }

    public void moverArr()
    {
        culebrita.y-=psize;
    }

    public void moverAba()
    {
        culebrita.y+=psize;
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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
