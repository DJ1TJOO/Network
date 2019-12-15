package me.DJ1TJOO.client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import me.DJ1TJOO.server.Package;

public class KeyInput extends KeyAdapter {

	Client client;
	
	public KeyInput(Client client) {
		this.client = client;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			client.createSocket(new Package(2, client.getGame().getId(), 0, 1));
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			client.createSocket(new Package(2, client.getGame().getId(), 1, 1));
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			client.createSocket(new Package(2, client.getGame().getId(), 2, 1));
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			client.createSocket(new Package(2, client.getGame().getId(), 3, 1));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			client.createSocket(new Package(2, client.getGame().getId(), 0, 0));
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			client.createSocket(new Package(2, client.getGame().getId(), 1, 0));
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			client.createSocket(new Package(2, client.getGame().getId(), 2, 0));
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			client.createSocket(new Package(2, client.getGame().getId(), 3, 0));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
