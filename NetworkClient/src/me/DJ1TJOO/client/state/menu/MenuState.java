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

	private Gui guiMain;
	private Gui guiIp;
	private Gui currentGui;
	private Game game;
	
	private int selected;
	
	public MenuState(Game game) {
		super("menu");
		this.setKeyInput(new MenuKeyInput(this));
		this.setMouseInput(new MenuMouseInput(this));
		this.setGame(game);
	}

	public void init() {
		this.guiMain = new Gui(0, 0, 50, game.getWidth(), game.getHeight() - 50, Color.BLACK, null, 5);
		this.guiIp = new Gui(guiMain.getElememtId() + 1, 0, 0, game.getWidth(), game.getHeight(), Color.BLACK, null, 5);

		selected = guiMain.getId() + 1;
		Font font = new Font("Arial", Font.PLAIN, 50);
		guiMain.addElement(new Button(Location.CENTER, Location.TOP, Color.WHITE, guiMain, "Start", font, 5, () -> setCurrentGui(guiIp)));
		guiMain.addElement(new Button(Location.CENTER, Location.TOP, Color.WHITE, guiMain, "Quit", font, 5, () -> game.exit()));

		setCurrentGui(guiMain);
		
		guiIp.setId(guiMain.getElememtId() + 1);
		guiIp.setElememtId(guiIp.getId() + 1);
		guiIp.addElement(new Button(Location.CENTER, Location.CENTER, Color.WHITE, guiIp, "Join", font, 5, () -> start()));
	}

	public void start() {
		game.getStateManager().setState(game.getStateManager().gameState);
	}

	public void tick() {
		for (Element element : currentGui.getElements()) {
			if(element.getId() == selected) {
				element.setColor(Color.BLUE);
			} else {
				element.setColor(Color.WHITE);
			}
		}
	}

	public void render(Graphics g) {
		guiMain.render(g);
		g.setColor(Color.green);
	}

	public Gui getGui() {
		return guiMain;
	}

	public void setGui(Gui gui) {
		this.guiMain = gui;
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

	public Gui getGuiIp() {
		return guiIp;
	}

	public void setGuiIp(Gui guiIp) {
		this.guiIp = guiIp;
	}

	public Gui getCurrentGui() {
		return currentGui;
	}

	public void setCurrentGui(Gui currentGui) {
		this.currentGui = currentGui;
	}
}
