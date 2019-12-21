package me.DJ1TJOO.client.state.menu;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import me.DJ1TJOO.client.libs.gui.Button;
import me.DJ1TJOO.client.libs.gui.Element;
import me.DJ1TJOO.client.libs.gui.Gui;

public class MenuMouseInput extends MouseAdapter {

	private MenuState menuState; 
	
	public MenuMouseInput(MenuState menuState) {
		this.setMenuState(menuState);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();
		
		Gui gui = menuState.getCurrentGui();
		for (Element element : gui.getElements()) {
			Rectangle rect = new Rectangle(element.getLastX() + gui.getX(), element.getLastY() + gui.getY(), element.getLastWidth(), element.getLastHeight());
			if(rect.contains(new Point(mX, mY))){
				menuState.setSelected(element.getId());
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();
		
		Gui gui = menuState.getCurrentGui();
		for (Element element : gui.getElements()) {
			Rectangle rect = new Rectangle(element.getLastX() + gui.getX(), element.getLastY() + gui.getY(), element.getLastWidth(), element.getLastHeight());
			if(rect.contains(new Point(mX, mY))){
				if(element.getId() == menuState.getSelected()) {
					if(element instanceof Button) {
						Button b = (Button) element;
						b.getAction().run();
					}
				}
			}
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	public MenuState getMenuState() {
		return menuState;
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
	}
	
}
