package me.DJ1TJOO.client.libs.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public abstract class Element {

	private int id, x, y, width, height, lastWidth, lastHeight, lastX, lastY;
	private Location vertical, horizontal;
	private Gui gui;
	private Color color;
	private Boolean selected = false;
	
	public Element(int id, int x, int y, int width, int height, Color color, Gui gui) {
		this.x = x;
		this.y = y;
		this.lastX = x;
		this.lastY = y;
		this.width = width;
		this.height = height;
		this.lastWidth = width;
		this.lastHeight = height;
		this.id = id;
		this.color = color;
		this.gui = gui;
	}

	public abstract void render(Graphics g);
	public abstract void tick();
	public abstract void keyPressed(KeyEvent e);

	public boolean intersects(int v1, int width1, int v2, int width2) {
		for (int i = v1; i < v1 + width1; i++) {
			for (int j = v2; j < v2 + width2; j++) {
				if(i == j) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Gui getGui() {
		return gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
	}

	public int getLastWidth() {
		return lastWidth;
	}

	public void setLastWidth(int lastWidth) {
		this.lastWidth = lastWidth;
	}

	public int getLastHeight() {
		return lastHeight;
	}

	public void setLastHeight(int lastHeight) {
		this.lastHeight = lastHeight;
	}

	public int getLastX() {
		return lastX;
	}

	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}

	public Location getVertical() {
		return vertical;
	}

	public void setVertical(Location vertical) {
		this.vertical = vertical;
	}

	public Location getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(Location horizontal) {
		this.horizontal = horizontal;
	}

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

}
