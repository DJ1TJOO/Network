package me.DJ1TJOO.client.state.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.DJ1TJOO.client.Game;
import me.DJ1TJOO.client.libs.gui.Button;
import me.DJ1TJOO.client.libs.gui.Element;
import me.DJ1TJOO.client.libs.gui.Gui;
import me.DJ1TJOO.client.libs.gui.Input;
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
		this.guiMain = new Gui(0, 0, 50, game.getWidth(), game.getHeight() - 50, Color.BLACK, null, 5, "guiMain", this);
		this.guiIp = new Gui(guiMain.getElememtId() + 1, 0, 50, game.getWidth(), game.getHeight() - 50, Color.BLACK, null, 5, "guiIp", this);

		selected = guiMain.getId() + 1;
		Font font = new Font("Arial", Font.PLAIN, 50);
		guiMain.addElement(new Button(Location.CENTER, Location.TOP, Color.WHITE, guiMain, "Start", font, 5, () -> setCurrentGui(guiIp)));
		guiMain.addElement(new Button(Location.CENTER, Location.TOP, Color.WHITE, guiMain, "Quit", font, 5, () -> game.exit()));

		setCurrentGui(guiMain);
		
		guiIp.setId(guiMain.getElememtId());
		guiIp.setElememtId(guiIp.getId() + 1);
		guiIp.addElement(new Input(Location.CENTER, Location.TOP, 5, Color.WHITE, guiIp, Input.InputType.IP4, font));
		guiIp.addElement(new Button(Location.CENTER, Location.TOP, Color.WHITE, guiIp, "Join", font, 5, () -> start()));
	}

	public void start() {
		Input input = (Input) guiIp.getElement(guiIp.getId() + 1);
		if(input != null) {
			game.getStateManager().gameState.setHost(input.getText());
			game.getStateManager().setState(game.getStateManager().gameState);
		}
	}

	public void tick() {
		currentGui.tick();
		for (Element element : currentGui.getElements()) {
			if(element.getId() == selected) {
				element.setSelected(true);
				element.setColor(Color.BLUE);
			} else {
				element.setSelected(false);
				element.setColor(Color.WHITE);
			}
		}
	}

	public void render(Graphics g) {
		currentGui.render(g);
	}

	public Gui getGuiMain() {
		return guiMain;
	}

	public void setGuiMain(Gui guiMain) {
		this.guiMain = guiMain;
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
		this.selected = currentGui.getId() + 1;
		this.currentGui = currentGui;
	}
}
