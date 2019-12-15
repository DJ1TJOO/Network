package me.DJ1TJOO.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import me.DJ1TJOO.server.Package;

public class SocketConnector {

	InetAddress host = null;
	private Game game;
    
	public SocketConnector(Game game, String host) {
		try {
			this.host = InetAddress.getByName(host);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setGame(game);
		Package pack = createSocket(new Package(0));
		if(pack == null) {
			game.setConnected(false);
		}
		if(pack.getId() == 0) {
			game.setId((Integer) pack.getArgs()[0]);
			game.setConnected(true);
			//System.err.println("Client id: " + game.getId());
		}
	}
	
	public Package createSocket(Package pack) {
		try {
	    	@SuppressWarnings("resource")
			Socket socket = new Socket(host, 2345);
	        //write to socket using ObjectOutputStream
	    	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	        oos.writeObject(pack);
	        //System.out.println("Sending request to Socket Server");
	        
	        //read the server response message
	        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	        Object object = ois.readObject();
	        if(object instanceof Package) {
	        	Package packR = (Package) object;
		        return packR;
	        } else {
		        String message = (String) object;
		        System.out.println("Message: " + message);
	        }
	        
	        //close resources
	        ois.close();
	        oos.close();
	        Thread.sleep(100);
		        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
