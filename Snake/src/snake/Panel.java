
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Panel extends JPanel{
    private final Core core;
    private final GameState player;
    private DeadAnimation dA;
    public Panel(Core core, GameState player)
    {
        this.core = core;
        this.player = player;
        setSize(core.size.width, core.size.height+20);
        init();
    }
    
    private void init(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    repaint();
                    try{
                        Thread.sleep(10);
                    } catch(Exception ex)
                    {}
                }
            }
        });
        t1.start();
    }
    
    public void pintarMapa(Graphics g)
    {
        g.setColor(Color.gray);
        for (Point mapa : player.mapa) {
            g.fillRect(mapa.x, mapa.y, core.psize, core.psize);
        }
    }

    public void pintarFondo(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0,0 , getSize().width, getSize().height);
        g.setColor(Color.gray);
        for (int i=core.psize; i < core.size.width; i+=core.psize)
        {
            g.drawLine(i, 0, i, core.size.height);
        }
        for (int i=core.psize; i < core.size.height; i+=core.psize)
        {
            g.drawLine(0, i, core.size.width, i);
        }
    }

    public void pintarComida(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(player.comida.x, player.comida.y, core.psize, core.psize);
    }

    
    public void pintarViborita(Graphics g)
    {
        g.setColor(Color.green);
        g.fillRect(player.viborita[0].x, player.viborita[0].y, core.psize, core.psize);
        for (int i=1; i < player.largo; i++)
        {
            g.setColor(new Color(200-i,255-i*3, 255-i*2));
            g.fillRect(player.viborita[i].x, player.viborita[i].y, core.psize, core.psize);
        }
    }
    
    public void pintarBarra(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(0, core.size.height, core.size.width, getSize().height);
        g.setColor(Color.white);
        g.drawString("Duudee, your speeed is: " + (float)(120.0/player.speed) + ", and your score is:" + player.score, 5, getSize().height-5);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        pintarFondo(g);
        pintarMapa(g);
        pintarComida(g);
        pintarViborita(g);
        pintarBarra(g);
        if (!core.player1.alive)
        {
            if (dA == null)
            {
                dA = new DeadAnimation(getSize(), core);
            }
            dA.pintarMuerte(g);
        }
        if (player.pause)
        {
            g.setColor(new Color(70, 100, 100, 200));
            g.fillRect(0, 0, core.size.width, core.size.height);
        }
    }
}


class DeadAnimation
{
    public int dead=0;
    public final Dimension dim;
    private final Core core;
    private GameState player;
    public DeadAnimation(Dimension size, Core core)
    {
        this.core = core;
        dim = size;
        init();
    }
    
    private void init(){
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (dead < dim.height)
                {
                    try{
                        Thread.sleep((int)(Math.random()*5));
                    }catch(Exception e){}
                    dead++;
                }
                JOptionPane.showMessageDialog(null, "Perdiste!!!!");
                System.exit(0);
            }
        });
        t.start();
    }
    
    public void pintarMuerte(Graphics g)
    {
        g.setColor(Color.red);
        for (int i=0; i < dead; i++)
        {
            g.fillRect(0, i, dim.width, dead);
        }
    }
}
