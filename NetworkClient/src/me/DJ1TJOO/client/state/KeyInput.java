package me.DJ1TJOO.client.state;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class KeyInput extends KeyAdapter{

	private Boolean shift = false;
	
	abstract public void keyPressed(KeyEvent e);
	abstract public void keyReleased(KeyEvent e);
	
	public Boolean getShift() {
		return shift;
	}
	
	public void setShift(Boolean shift) {
		this.shift = shift;
	}
}
