package me.DJ1TJOO.client.state.menu;

import java.awt.event.KeyEvent;

import me.DJ1TJOO.client.libs.gui.Button;
import me.DJ1TJOO.client.libs.gui.Element;
import me.DJ1TJOO.client.state.KeyInput;

public class MenuKeyInput extends KeyInput {

	private MenuState menuState; 
	
	public MenuKeyInput(MenuState menuState) {
		this.setMenuState(menuState);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			setShift(true);
		}
		menuState.getCurrentGui().keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			setShift(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			menuState.setSelected(menuState.getSelected()+1);
			if(menuState.getSelected() > menuState.getCurrentGui().getElements().size() + menuState.getCurrentGui().getId()) {
				menuState.setSelected(menuState.getCurrentGui().getId() + 1);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_TAB) {
			menuState.setSelected(menuState.getSelected()-1);
			if(menuState.getSelected() <= menuState.getCurrentGui().getId()) {
				menuState.setSelected(menuState.getCurrentGui().getId() + menuState.getCurrentGui().getElements().size());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			for (Element element : menuState.getCurrentGui().getElements()) {
				if(element.getId() == menuState.getSelected()) {
					if(element instanceof Button) {
						Button b = (Button) element;
						b.getAction().run();
					}
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(menuState.getCurrentGui().getName().equals("guiIp")) {
				menuState.setCurrentGui(menuState.getGuiMain());
			}
		}
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
