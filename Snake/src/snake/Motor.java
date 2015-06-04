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
    public GameState localPlayer;
    public Dimension size;
    public String last;
    public int psize;
    public Point cola;
    private boolean moved;
    
    public Motor(Dimension size, int psize)
    {
        this.size = size;
        this.psize = psize;
        init();
    }

    public void init()
    {
        this.moved=true;
        this.last="der";
        this.localPlayer = new GameState(new Point[1000], 2, new Point(5*psize, 5*psize), 0, 60, true, false);
        this.localPlayer.viborita[1] = new Point(0, 0);
        this.localPlayer.viborita[0] = new Point(psize,0);
        this.cola = this.localPlayer.viborita[1];
        
        Thread t;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(localPlayer.alive)
                {
                    try{
                        Thread.sleep(localPlayer.speed);
                    } catch(Exception e){}
                    if (!localPlayer.pause)
                    {
                        mover();
                        moved = true;
                        localPlayer.alive = estaViva();
                        hayComida();
                    }
                }
            }
        });
        t.start();
    }
    
    
    public boolean estaViva()
    {
        for (int i=1; i < localPlayer.largo; i++)
        {
            if (localPlayer.viborita[0].equals(localPlayer.viborita[i]))
            {
                return false;
            }
        }
        return true;
    }

    public void mover()
    {
        cola = new Point(localPlayer.viborita[localPlayer.largo-1]);
        for (int i=0; i < localPlayer.largo-1; i++)
        {
            localPlayer.viborita[localPlayer.largo-1-i] = localPlayer.viborita[localPlayer.largo-2-i];
        }
        
        if ("der".equals(last))
        {
            if (localPlayer.viborita[0].x == size.width-psize)
            {
                localPlayer.viborita[0] = new Point(0, localPlayer.viborita[0].y);
            } else {
                localPlayer.viborita[0] = new Point(localPlayer.viborita[0].x+psize, localPlayer.viborita[0].y);
            }
        }
        if ("izq".equals(last))
        {
            if (localPlayer.viborita[0].x == 0){
                localPlayer.viborita[0] = new Point(size.width-psize, localPlayer.viborita[0].y);
            } else {
                localPlayer.viborita[0] = new Point(localPlayer.viborita[0].x-psize, localPlayer.viborita[0].y);
            }
        }
        if ("aba".equals(last))
        {
            if (localPlayer.viborita[0].y == size.height-psize){
                localPlayer.viborita[0] = new Point(localPlayer.viborita[0].x, 0);
            } else {
                localPlayer.viborita[0] = new Point(localPlayer.viborita[0].x, localPlayer.viborita[0].y+psize);
            }
        }
        if ("arr".equals(last))
        {
            if (localPlayer.viborita[0].y == 0){
                localPlayer.viborita[0] = new Point(localPlayer.viborita[0].x, size.height-psize);
            } else {
                localPlayer.viborita[0] = new Point(localPlayer.viborita[0].x, localPlayer.viborita[0].y-psize);
            }
            
        }
        
    }
    
    public void nuevoElemento()
    {
        localPlayer.viborita[localPlayer.largo++] = cola;
    }
    
    public boolean hayComida()
    {
        if (localPlayer.viborita[0].x == localPlayer.comida.x && localPlayer.viborita[0].y == localPlayer.comida.y)
        {
            nuevoElemento();
            localPlayer.score+=localPlayer.largo*Math.pow(1.5, 120.0/localPlayer.speed);
            while (true)
            {
                localPlayer.comida.y = (int)(Math.random()*(size.height/psize-1))*psize;
                localPlayer.comida.x = (int)(Math.random()*(size.width/psize-1))*psize;
                boolean next=false;
                for (int i=0; i < localPlayer.largo; i++)
                {
                    if (localPlayer.comida.equals(localPlayer.viborita[i]))
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
            if (localPlayer.speed > 20){
                localPlayer.speed-=10;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_N) {
            if (localPlayer.speed < 60){
                localPlayer.speed+=10;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_P) localPlayer.pause = !localPlayer.pause;
        if (e.getKeyCode() == KeyEvent.VK_Q) System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
