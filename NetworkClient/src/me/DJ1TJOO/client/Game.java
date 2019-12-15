package me.DJ1TJOO.client;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import me.DJ1TJOO.server.Package;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private SocketConnector client;
	private Integer id;

	private boolean running;
	private Thread thread;
	
	private List<me.DJ1TJOO.server.Client> clients = new ArrayList<me.DJ1TJOO.server.Client>();
	
	public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        Game game = new Game();
        Canvas canvas = game;
        canvas.setSize(400, 400);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	game.client.createSocket(new Package(0, 1, game.id));
                System.exit(0);
            }
        });
		game.start();
    }


	public Game() {
		setClient(new SocketConnector(this));
		this.addKeyListener(new KeyInput(client));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		running = false;
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			//frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				//frames = 0;
			}
		}
		stop();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.white);
		for (me.DJ1TJOO.server.Client client : clients) {
			g.fillRect(client.getX(), client.getY(), 20, 50);
		}
		g.dispose();
		bs.show();
	}

	@SuppressWarnings("unchecked")
	private void tick() {
		Package pack = (Package) client.createSocket(new Package(1, id));
		//System.err.println(pack);
		if(pack == null) {
			stop();
		}
		if(pack.getId() == 1) {
			clients = (List<me.DJ1TJOO.server.Client>) pack.getArgs()[0];
		}
	}

	public SocketConnector getClient() {
		return client;
	}

	public void setClient(SocketConnector client) {
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
