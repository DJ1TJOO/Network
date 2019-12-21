package me.DJ1TJOO.client.state.game;

import java.awt.event.KeyEvent;

import me.DJ1TJOO.client.SocketConnector;
import me.DJ1TJOO.client.state.KeyInput;
import me.DJ1TJOO.server.Package;

public class GameKeyInput extends KeyInput {

	SocketConnector client;
	private GameState gameState;
	
	public GameKeyInput(SocketConnector client, GameState gameState) {
		this.client = client;
		this.gameState = gameState;
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
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameState.getGame().getStateManager().setState(gameState.getGame().getStateManager().menuState);
			gameState.getGame().disconnect();
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

	public SocketConnector getClient() {
		return client;
	}

	public void setClient(SocketConnector client) {
		this.client = client;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
}
