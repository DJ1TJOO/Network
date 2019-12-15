package me.DJ1TJOO.client.state.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import me.DJ1TJOO.client.Game;
import me.DJ1TJOO.client.state.State;
import me.DJ1TJOO.server.Client;
import me.DJ1TJOO.server.Package;

public class GameState extends State {

	private Game game;
	private List<Client> clients;
	
	public GameState(Game game) {
		super("game");
		this.game = game;
	}

	public void init() {
		game.connect("192.168.178.18");
		clients = new ArrayList<Client>();
		this.setKeyInput(new GameKeyInput(game.getClient(), this));
	}

	@SuppressWarnings("unchecked")
	public void tick() {
		Package pack = (Package) game.getClient().createSocket(new Package(1, game.getId()));
		//System.err.println(pack);
		if(pack == null) {
			game.getStateManager().setState(game.getStateManager().menuState);
			game.disconnect();
		}
		if(pack.getId() == 1) {
			setClients((List<Client>) pack.getArgs()[0]);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		for (Client client : clients) {
			g.fillRect(client.getX(), client.getY(), 20, 50);
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

}
