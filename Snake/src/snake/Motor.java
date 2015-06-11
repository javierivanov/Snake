package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author javier
 */
public class Motor implements KeyListener{

    public Point[] viborita;
    public int largo;
    public Point comida;
    public Dimension size;
    public String last;
    public int speed = 100;
    public int psize;
    public Point cola;
    public boolean alive;
    public boolean pause;
    private boolean moved;
    public int score;
    public Point[] mapa;
    public Motor(Dimension size, int psize)
    {
        this.size = size;
        this.psize = psize;
        init();
    }

    public void init()
    {
        this.score = 0;
        this.alive=true;
        this.pause=false;
        this.moved=true;
        this.last="der";
        this.viborita = new Point[1000];
        this.largo = 2;
        this.viborita[1] = new Point(0, 0);
        this.viborita[0] = new Point(psize,0);
        this.cola = viborita[1];
        this.comida = new Point(5*psize, 5*psize);
        
        this.mapa = new Point[20];
        
        for (int i=0; i < this.mapa.length; i++)
        {
            this.mapa[i] = new Point((int)(Math.random()*(size.width/psize-1))*psize,
                    (int)(Math.random()*(size.height/psize-1))*psize);
        }
        
        Thread t;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(alive)
                {
                    try{
                        Thread.sleep(speed);
                    } catch(Exception e){}
                    if (!pause)
                    {
                        mover();
                        moved = true;
                        alive = estaViva();
                        hayComida();
                    }
                }
            }
        });
        t.start();
    }
    
    
    public boolean estaViva()
    {
      if (viborita[0].x < 0 || viborita[0].y < 0)
        {
            return false;
        }
        
        for (int i=1; i < largo; i++)
        {
            if (viborita[0].x == viborita[i].x && viborita[0].y == viborita[i].y)
            {
                return false;
            }
        }
        
        for (int i=0; i < mapa.length; i++)
        {
            if (viborita[0].x == mapa[i].x && viborita[0].y == mapa[i].y)
            {
                return false;
            }
        }
        return true;
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
            if (viborita[0].x == size.width-psize){
                viborita[0] = new Point(0, viborita[0].y);
            } else {
                viborita[0] = new Point(viborita[0].x+psize, viborita[0].y);
            }
        }
        if ("izq".equals(last))
        {
            if (viborita[0].x == 0){
                viborita[0] = new Point(size.width-psize, viborita[0].y);
            } else {
                viborita[0] = new Point(viborita[0].x-psize, viborita[0].y);
            }
        }
        if ("aba".equals(last))
        {
            if (viborita[0].y == size.height-psize){
                viborita[0] = new Point(viborita[0].x, 0);
            } else {
                viborita[0] = new Point(viborita[0].x, viborita[0].y+psize);
            }
        }
        if ("arr".equals(last))
        {
            if (viborita[0].y == 0){
                viborita[0] = new Point(viborita[0].x, size.height-psize);
            } else {
                viborita[0] = new Point(viborita[0].x, viborita[0].y-psize);
            }
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
            score+=largo*Math.pow(1.5, 120.0/speed);
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
                for (Point m: this.mapa)
                {
                    if (m.equals(comida))
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
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (moved)
                if (!"aba".equals(last)){
                    last = "arr";
                    moved = false;
                }
                
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (moved)
                if (!"arr".equals(last)) {
                    last = "aba";
                    moved = false;
                }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (moved)
                if (!"der".equals(last)) {
                    last = "izq";
                    moved = false;
                }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (moved)
                if(!"izq".equals(last)) {
                    last = "der";
                    moved = false;
                }
        }
        if (e.getKeyCode() == KeyEvent.VK_M){
            if (speed > 20){
                speed-=10;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_N) {
            if (speed < 60){
                speed+=10;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_P) pause = !pause;
        if (e.getKeyCode() == KeyEvent.VK_Q) System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
