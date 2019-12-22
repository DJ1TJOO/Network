package me.DJ1TJOO.client;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import me.DJ1TJOO.client.state.StateManager;
import me.DJ1TJOO.server.Package;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private SocketConnector client;
	private Integer id;

	private boolean running;
	private Thread thread;
	
	private StateManager stateManager;
	private boolean connected;
	
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
        frame.setFocusTraversalKeysEnabled(false);
        canvas.setFocusTraversalKeysEnabled(false);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	game.exit();	
            }
        });
		game.start();
    }
	
	public void disconnect() {
		if(isConnected()) {
        	client.createSocket(new Package(0, 1, id));
        	setConnected(false);
    	}
	}
	
	public void connect(String host) {
		this.client = new SocketConnector(this, host);
		if(!isConnected()) {
			System.err.println("not connected");
		} else {
			System.err.println("connected to " + host);
		}
	}
	
	public void exit() {
		disconnect();
        System.exit(0);
	}
	
	public synchronized void start() {
		init();
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	private void init() {
		stateManager = new StateManager(this);
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
				render();
				delta--;
			}
			if (running)
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
		stateManager.render(g);
		g.dispose();
		bs.show();
	}

	private void tick() {
		stateManager.tick();
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


//	public List<Client> getClients() {
//		return clients;
//	}
//
//
//	public void setClients(List<Client> clients) {
//		this.clients = clients;
//	}


	public boolean isConnected() {
		return connected;
	}


	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	public void setStateManager(StateManager stateManager) {
		this.stateManager = stateManager;
	}
}
