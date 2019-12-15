package me.DJ1TJOO.client.state.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.DJ1TJOO.client.Game;
import me.DJ1TJOO.client.libs.gui.Button;
import me.DJ1TJOO.client.libs.gui.Element;
import me.DJ1TJOO.client.libs.gui.Gui;
import me.DJ1TJOO.client.libs.gui.Location;
import me.DJ1TJOO.client.state.State;

public class MenuState extends State {

	private Gui gui;
	private Game game;
	
	private int selected;
	
	public MenuState(Game game) {
		super("menu");
		this.setKeyInput(new MenuKeyInput(this));
		this.setMouseInput(new MenuMouseInput(this));
		this.setGame(game);
	}

	public void init() {
		this.gui = new Gui(0, 0, 50, game.getWidth(), game.getHeight() - 50, Color.BLACK, null, 5);
		
		selected = gui.getId() + 1;
		Font font = new Font("Arial", Font.PLAIN, 50);
		gui.addElement(new Button(Location.CENTER, Location.TOP, Color.WHITE, gui, "Start", font, 5, () -> start()));
		gui.addElement(new Button(Location.CENTER, Location.TOP, Color.WHITE, gui, "Quit", font, 5, () -> game.exit()));
	}

	public void start() {
		game.getStateManager().setState(game.getStateManager().gameState);
	}

	public void tick() {
		for (Element element : gui.getElements()) {
			if(element.getId() == selected) {
				element.setColor(Color.BLUE);
			} else {
				element.setColor(Color.WHITE);
			}
		}
	}

	public void render(Graphics g) {
		gui.render(g);
		g.setColor(Color.green);
	}

	public Gui getGui() {
		return gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

}
