package me.DJ1TJOO.client.state;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public abstract class State {

	private String name;
	private KeyAdapter keyInput;
	private MouseAdapter mouseInput;
	
	public State(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	abstract public void init();
	abstract public void tick();
	abstract public void render(Graphics g);

	public KeyAdapter getKeyInput() {
		return keyInput;
	}

	public void setKeyInput(KeyAdapter keyInput) {
		this.keyInput = keyInput;
	}

	public MouseAdapter getMouseInput() {
		return mouseInput;
	}

	public void setMouseInput(MouseAdapter mouseInput) {
		this.mouseInput = mouseInput;
	}
	
}
