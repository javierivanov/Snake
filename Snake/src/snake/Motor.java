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
    //public ArrayList<Point> viborita;
    public Point comida;
    public Dimension size;
    public String last;
    private int speed = 500;
    public Motor(Dimension size)
    {
        this.size = size;
        last="der";
        //viborita = new ArrayList<>();
        //viborita.add(new Point(0,0));
        culebrita = new Point(0, 0);
        comida = new Point(5*20, 5*20);
        Thread t1 = new Thread(new Runnable() {

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
                    if (last == "der") moverDer();
                    if (last == "izq") moverIzq();
                    if (last == "aba") moverAba();
                    if (last == "arr") moverArr();
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
            comida.y = (int)(Math.random()*size.height/20-1)*20;
            comida.x = (int)(Math.random()*size.width/20-1)*20;
            return true;
        }
        return false;
    }
    
    public void moverIzq()
    {
        culebrita.x-=20;
    }
    
    public void moverDer()
    {
        
        culebrita.x+=20;
    }
    
    public void moverArr()
    {
        culebrita.y-=20;
    }
    
    public void moverAba()
    {
        culebrita.y+=20;
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
