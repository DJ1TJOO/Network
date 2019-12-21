package me.DJ1TJOO.client.libs.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import me.DJ1TJOO.client.state.State;

public class Gui extends Element {
	
	private List<Element> elements;
	private int elememtId;
	private int elementOffset;
	private String name;
	private State state;
	
	public Gui(int id, int x, int y, int width, int height, Color color, Gui gui, int elementOffset, String name, State state) {
		super(id, x, y, width, height, color, gui);
		this.elements = new ArrayList<Element>();
		this.elememtId = id + 1;
		this.elementOffset = elementOffset;
		this.name = name;
		this.state = state;
	}

	public void tick() {
		for (Element element : elements) {
			element.tick();
		}
	}
	
	public void render(Graphics g) {
		Color beforeColor = g.getColor();
		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(beforeColor);
		for (Element element : elements) {
			element.render(g);
		}
	}

	public void keyPressed(KeyEvent e) {
		for (Element element : elements) {
			element.keyPressed(e);
		}
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	
	public Element getElement(int id) {
		for (Element element : elements) {
			if(element.getId() == id) {
				return element;
			}
		}
		return null;
	}

	public void addElement(Element element) {
		if(!elements.contains(element)) {
			element.setId(elememtId);
			elements.add(element);
			elememtId++;
		}
	}

	public void removeElement(Element element) {
		if(elements.contains(element))
			elements.remove(element);
	}

	public int getElememtId() {
		return elememtId;
	}

	public void setElememtId(int elememtId) {
		this.elememtId = elememtId;
	}

	public int getElementOffset() {
		return elementOffset;
	}

	public void setElementOffset(int elementOffset) {
		this.elementOffset = elementOffset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
