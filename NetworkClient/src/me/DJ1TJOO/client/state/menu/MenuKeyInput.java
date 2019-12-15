package me.DJ1TJOO.client.state.menu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import me.DJ1TJOO.client.libs.gui.Button;
import me.DJ1TJOO.client.libs.gui.Element;

public class MenuKeyInput extends KeyAdapter {

	private MenuState menuState; 
	
	public MenuKeyInput(MenuState menuState) {
		this.setMenuState(menuState);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			menuState.setSelected(menuState.getSelected()+1);
			if(menuState.getSelected() > menuState.getGui().getElements().size() + menuState.getGui().getId()) {
				menuState.setSelected(menuState.getGui().getId() + 1);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_TAB) {
			menuState.setSelected(menuState.getSelected()-1);
			if(menuState.getSelected() <= menuState.getGui().getId()) {
				menuState.setSelected(menuState.getGui().getId() + menuState.getGui().getElements().size());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			for (Element element : menuState.getGui().getElements()) {
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
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public MenuState getMenuState() {
		return menuState;
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
	}
	
}
