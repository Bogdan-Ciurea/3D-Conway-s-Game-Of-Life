package renderer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import renderer.input.Mouse;
import renderer.input.Space;
import renderer.entity.WorldManager;

// The Display class is responsible for creating the window, importing the Mouse and Space class
// and calling all the necesary functions
// This class is the main class that has to be called in order for the program to work

public class Main extends Canvas implements Runnable{
    public static final long serialVersionUID = 1L;

    private Thread thread;
    private JFrame frame;
    private static String title = "3D Game of life";
    private static boolean running = false;

    private WorldManager world;

    private Mouse mouse;
    private Space space;

    public Main(){
        this.frame = new JFrame();

        Dimension size = new Dimension(Reference.WORLD_WIDTH, Reference.WORLD_HEIGHT);
        this.setPreferredSize(size);

        this.world = new WorldManager();

        this.mouse = new Mouse();
        this.space = new Space();

        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);
        this.addKeyListener(this.space);
    }

    public static void main(String args[]){
        Main display = new Main();
        display.frame.setTitle(title);
        display.frame.add(display);
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setResizable(false);
        display.frame.setVisible(true);

        display.start();
    }

    public synchronized void start(){
        running = true;
        this.thread = new Thread(this, "Display");
        this.thread.start();
    } 

    public synchronized void stop(){
        running = false;
        try{
            this.thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }  
    }


    @Override
    public void run(){
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60;
        double delta = 0;
        int frames = 0;

        this.world.init();

        while(running){
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / ns;
            lastTime = currentTime;
            while(delta >= 1){
                update();
                delta --;
                render();
                frames ++;
            }

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                this.frame.setTitle(title + " | " + frames + "fps");
                frames = 0;
            }
        }

        stop();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, Reference.WORLD_WIDTH, Reference.WORLD_HEIGHT);

        this.world.render(g);

        g.dispose();
        bs.show();
    }
    
    private void update(){
        this.world.update(this.mouse, this.space);
    }
    
}
