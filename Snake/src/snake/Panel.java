
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Panel extends JPanel{
    private Motor m;
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
                        Thread.sleep(10);
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

    /*
    public void pintarViborita(Graphics g)
    {
        g.setColor(Color.yellow);
        for (int i=0; i < m.largo; i++)
        {
            g.fillRect(m.viborita[i].x, m.viborita[i].y, m.psize, m.psize);
        }
    }
    */

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        pintarFondo(g);
        pintarComida(g);
        pintarPunto(g);
        //pintarViborita(g);
    }
}
