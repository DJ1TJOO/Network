package me.DJ1TJOO.client.libs.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Gui extends Element {
	
	private List<Element> elements;
	private int elememtId;
	private int elementOffset;
	
	public Gui(int id, int x, int y, int width, int height, Color color, Gui gui, int elementOffset) {
		super(id, x, y, width, height, color, gui);
		this.elements = new ArrayList<Element>();
		this.elememtId = id + 1;
		this.elementOffset = elementOffset;
	}
	
	public void render(Graphics g) {
		for (Element element : elements) {
			element.render(g);
		}
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
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
	
}
