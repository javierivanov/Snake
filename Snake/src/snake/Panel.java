
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Panel extends JPanel{
    private final Motor m;
    public DeadAnimation dA;
    public Dimension size;
    public BufferedImage pasto;
    public BufferedImage comida;
    public BufferedImage arbol;
    public Panel(Motor m, Dimension size)
    {
        this.m = m;
        this.size = size;
        setSize(size.width, size.height+20);
        init();
    }
    
    private void init(){
        try {
            pasto = ImageIO.read(getClass().getResource("iu.png"));
            comida = ImageIO.read(getClass().getResource("comida.gif"));
            arbol = ImageIO.read(getClass().getResource("hole.png"));
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        for (Point mapa : m.mapa) {
            //g.fillRect(mapa.x, mapa.y, m.psize, m.psize);
            g.drawImage(arbol, mapa.x, mapa.y, null);
        }
    }

    public void pintarFondo(Graphics g)
    {
        /*
        g.setColor(Color.black);
        g.fillRect(0,0 , getSize().width, getSize().height);
        g.setColor(Color.gray);

        for (int i=m.psize; i < size.width; i+=m.psize)
        {
        g.drawLine(i, 0, i, size.height);
        }
        for (int i=m.psize; i < size.height; i+=m.psize)
        {
        g.drawLine(0, i, size.width, i);
        }
        */

        //System.out.println(img);
        for (int i=0; i <= (m.size.width/pasto.getWidth());i++)
        {
            for (int j=0; j <= (m.size.height/pasto.getHeight()); j++)
            {
                g.drawImage(pasto, i*pasto.getWidth(), j*pasto.getHeight(), null);
            }
        }
    }

    public void pintarComida(Graphics g)
    {
        //g.setColor(Color.red);
        //g.fillRect(m.comida.x, m.comida.y, m.psize, m.psize);
        g.drawImage(comida, m.comida.x, m.comida.y, null);
    }

    
    public void pintarViborita(Graphics g)
    {
        g.setColor(Color.green);
        g.fillRect(m.viborita[0].x, m.viborita[0].y, m.psize, m.psize);
        for (int i=1; i < m.largo; i++)
        {
            g.setColor(new Color(200-i,255-i*3, 255-i*2));
            g.fillRect(m.viborita[i].x, m.viborita[i].y, m.psize, m.psize);
        }
    }
    
    public void pintarBarra(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(0, size.height, size.width, getSize().height);
        g.setColor(Color.white);
        g.drawString("Duudee, your speeed is: " + (float)(120.0/m.speed) + ", and your score is:" + m.score, 5, getSize().height-5);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        pintarFondo(g);
        pintarComida(g);
        pintarMapa(g);
        pintarViborita(g);
        pintarBarra(g);
        if (!m.alive)
        {
            if (dA == null)
            {
                dA = new DeadAnimation(getSize(), m);
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
    public final Dimension dim;
    public final Motor m2;
    public DeadAnimation(Dimension size, Motor m)
    {
        this.m2 = m;
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
                m2.init();
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
