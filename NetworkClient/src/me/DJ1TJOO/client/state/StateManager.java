package me.DJ1TJOO.client.state;

import java.awt.Graphics;

import me.DJ1TJOO.client.Game;
import me.DJ1TJOO.client.state.game.GameState;
import me.DJ1TJOO.client.state.menu.MenuState;

public class StateManager {

	private Game game;
	private State currentState;
	
	public MenuState menuState;
	public GameState gameState;
	
	public StateManager(Game game) {
		this.game = game;
		
		this.menuState = new MenuState(game);
		this.gameState = new GameState(game);
		this.setState(menuState);
	}
	
	public void setState(State state) {
		if(this.currentState != null) {
			this.unloadState(currentState);
		}
		state.init();
		this.currentState = state;
		this.getGame().addKeyListener(state.getKeyInput());
		this.getGame().addMouseListener(state.getMouseInput());
		this.getGame().addMouseMotionListener(state.getMouseInput());
	}
	
	public void unloadState(State state) {
		this.currentState = state;
		this.getGame().removeKeyListener(state.getKeyInput());
		this.getGame().removeMouseListener(state.getMouseInput());
		this.getGame().removeMouseMotionListener(state.getMouseInput());
	}

	public void tick() {
		currentState.tick();
	}
	
	public void render(Graphics g) {
		currentState.render(g);
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
}
