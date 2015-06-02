
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Panel extends JPanel{
    private Motor m;
    public DeadAnimation dA;
    public Panel(Motor m, Dimension size)
    {
        this.m = m;
        setSize(size);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    repaint();
                    try{
                        Thread.sleep(20);
                    } catch(Exception ex)
                    {}
                }
            }
        });
        t1.start();
    }

    public void pintarFondo(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0,0 , getSize().width, getSize().height);
        g.setColor(Color.white);
        for (int i=m.psize; i < getSize().width; i+=m.psize)
        {
            g.drawLine(i, 0, i, getSize().height);
        }
        for (int i=m.psize; i < getSize().height; i+=m.psize)
        {
            g.drawLine(0, i, getSize().width, i);
        }
    }

    public void pintarPunto(Graphics g)
    {
        g.setColor(Color.green);
        g.fillRect(m.culebrita.x, m.culebrita.y, m.psize, m.psize);
    }
    public void pintarComida(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(m.comida.x, m.comida.y, m.psize, m.psize);
    }

    
    public void pintarViborita(Graphics g)
    {
        
        for (int i=0; i < m.largo; i++)
        {
            
            g.setColor(new Color(255-i/2,255-i*3, 255-i*2));
            g.fillRect(m.viborita[i].x, m.viborita[i].y, m.psize, m.psize);
        }
    }


    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        pintarFondo(g);
        pintarComida(g);
        //pintarPunto(g);
        pintarViborita(g);
        if (!m.alive)
        {
            if (dA == null)
            {
                dA = new DeadAnimation(getSize());
            }
            dA.pintarMuerte(g);
        }
        if (m.pause)
        {
            g.setColor(new Color(70, 100, 100, 200));
            g.fillRect(0, 0, m.size.width, m.size.height);
        }
    }
}


class DeadAnimation
{
    public int dead=0;
    public Dimension dim;
    public DeadAnimation(Dimension size)
    {
        dim = size;
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (dead < size.height)
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
