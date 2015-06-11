package snake;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author javier
 */
public class LogicGame implements KeyListener{
    private String last;
    private Point cola;
    private boolean moved;
    private final Core core;
    
    public LogicGame(Core core)
    {
        this.core = core;
        init();
    }

    public void init()
    {
        this.moved=true;
        this.last="der";
        Point[] mapa = new Point[20];
        for (int i=0; i < mapa.length; i++)
        {
            mapa[i] = new Point((int)(Math.random()*(core.size.width/core.psize-1))*core.psize, 
                    (int)(Math.random()*(core.size.height/core.psize-1))*core.psize);
        }
        
        this.core.player1 = new GameState(new Point[1000], 2, new Point(5*core.psize, 5*core.psize), 0, 60, true, false, mapa);
        this.core.player1.viborita[1] = new Point(0, 0);
        this.core.player1.viborita[0] = new Point(core.psize,0);
        this.cola = this.core.player1.viborita[1];
        
        Thread t;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(core.player1.alive)
                {
                    try{
                        Thread.sleep(core.player1.speed);
                    } catch(Exception e){}
                    if (!core.player1.pause)
                    {
                        mover();
                        moved = true;
                        core.player1.alive = estaViva();
                        hayComida();
                    }
                }
            }
        });
        t.start();
    }
    
    
    public boolean estaViva()
    {
        for (int i=1; i < core.player1.largo; i++)
        {
            if (core.player1.viborita[0].equals(core.player1.viborita[i]))
            {
                return false;
            }
        }
        for (Point mapa : core.player1.mapa) {
            if (mapa.equals(core.player1.viborita[0])) {
                return false;
            }
        }
        return true;
    }

    public void mover()
    {
        cola = new Point(core.player1.viborita[core.player1.largo-1]);
        for (int i=0; i < core.player1.largo-1; i++)
        {
            core.player1.viborita[core.player1.largo-1-i] = core.player1.viborita[core.player1.largo-2-i];
        }
        
        if ("der".equals(last))
        {
            if (core.player1.viborita[0].x == core.size.width-core.psize)
            {
                core.player1.viborita[0] = new Point(0, core.player1.viborita[0].y);
            } else {
                core.player1.viborita[0] = new Point(core.player1.viborita[0].x+core.psize, core.player1.viborita[0].y);
            }
        }
        if ("izq".equals(last))
        {
            if (core.player1.viborita[0].x == 0){
                core.player1.viborita[0] = new Point(core.size.width-core.psize, core.player1.viborita[0].y);
            } else {
                core.player1.viborita[0] = new Point(core.player1.viborita[0].x-core.psize, core.player1.viborita[0].y);
            }
        }
        if ("aba".equals(last))
        {
            if (core.player1.viborita[0].y == core.size.height-core.psize){
                core.player1.viborita[0] = new Point(core.player1.viborita[0].x, 0);
            } else {
                core.player1.viborita[0] = new Point(core.player1.viborita[0].x, core.player1.viborita[0].y+core.psize);
            }
        }
        if ("arr".equals(last))
        {
            if (core.player1.viborita[0].y == 0){
                core.player1.viborita[0] = new Point(core.player1.viborita[0].x, core.size.height-core.psize);
            } else {
                core.player1.viborita[0] = new Point(core.player1.viborita[0].x, core.player1.viborita[0].y-core.psize);
            }
            
        }
        
    }
    
    public void nuevoElemento()
    {
        core.player1.viborita[core.player1.largo++] = cola;
    }
    
    public boolean hayComida()
    {
        if (core.player1.viborita[0].x == core.player1.comida.x && core.player1.viborita[0].y == core.player1.comida.y)
        {
            nuevoElemento();
            core.player1.score+=core.player1.largo*Math.pow(1.5, 120.0/core.player1.speed);
            while (true)
            {
                core.player1.comida.y = (int)(Math.random()*(core.size.height/core.psize-1))*core.psize;
                core.player1.comida.x = (int)(Math.random()*(core.size.width/core.psize-1))*core.psize;
                boolean next=false;
                for (int i=0; i < core.player1.largo; i++)
                {
                    if (core.player1.comida.equals(core.player1.viborita[i]))
                    {
                        next = true;
                    }
                }
                for (Point mapa : core.player1.mapa) {
                    if (core.player1.comida.equals(mapa)) {
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
            if (core.player1.speed > 20){
                core.player1.speed-=10;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_N) {
            if (core.player1.speed < 60){
                core.player1.speed+=10;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_P) core.player1.pause = !core.player1.pause;
        if (e.getKeyCode() == KeyEvent.VK_Q) System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
