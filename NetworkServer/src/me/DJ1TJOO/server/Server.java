package me.DJ1TJOO.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {
	
	private Integer currentClient = 0;
	private List<Client> clients = new ArrayList<Client>();

	private boolean running;
	private Thread thread;
	
	public static void main(String[] args) {
		new Server();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		running = false;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			//frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				//frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		//System.err.println("d");
		for (Client client : clients) {
			client.tick();
		}
	}

	public Server() {
		start();
		try {
			
			//create the socket server object
			ServerSocket server = new ServerSocket(2345);
			
	        //keep listens indefinitely until receives 'exit' call or program terminates
	        while(true){
	            //System.out.println("Waiting for the client request");
	            
	            //creating socket and waiting for client connection
	            Socket socket = server.accept();
	            
	            //read from socket to ObjectInputStream object
	            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

	            //create ObjectOutputStream object
	            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	            Object object = ois.readObject();
	            if(object instanceof Package) {
	            	Package pack = (Package) object;
	            	switch (pack.getId()) {
					case 0: //request new client / exit
						if(pack.getArgs().length > 0) {
							if((Integer)pack.getArgs()[0] == 1) {
								for (Client client2 : clients) {
									if(client2.getId().equals((Integer)pack.getArgs()[1])) {
										clients.remove(client2);
							            oos.writeObject(new Package(0, 1));
										break;
									}
								}
							}
						} else {
							currentClient++;
							clients.add(new Client(currentClient, 0, 0));
							System.err.println("New client: " + currentClient);
							//write object to Socket
				            oos.writeObject(new Package(0, currentClient));
						}
						
						break;
					case 1: //request all clients states
						//System.err.println("Sending all clients to: " + pack.getArgs()[0]);
						//write object to Socket
			            oos.writeObject(new Package(1, clients));
						break;
					case 2: //request move
						Client client = null;
						for (Client client2 : clients) {
							if(client2.getId().equals((Integer)pack.getArgs()[0])) {
								client = client2;
							}
						}
						if(client == null) {
							//System.err.println("Sending error to: " + pack.getArgs()[0]);
							//write object to Socket
				            oos.writeObject(new Package(2, 0));
						} else {
							switch ((Integer) pack.getArgs()[1]) {
							case 0:
								if((Integer)pack.getArgs()[2] == 1) {
									client.setUp(true);
								} else {
									client.setUp(false);
								}
								break;
							case 1:
								if((Integer)pack.getArgs()[2] == 1) {
									client.setLeft(true);
								} else {
									client.setLeft(false);
								}
								break;
							case 2:
								if((Integer)pack.getArgs()[2] == 1) {
									client.setRight(true);
								} else {
									client.setRight(false);
								}
								break;
							case 3:
								if((Integer)pack.getArgs()[2] == 1) {
									client.setDown(true);
								} else {
									client.setDown(false);
								}
								break;

							default:
								break;
							}
							//System.err.println("Sending ok to: " + pack.getArgs()[0]);
							//write object to Socket
				            oos.writeObject(new Package(2, 1));
						}
						break;

					default:
						break;
					}

		            //close resources
		            ois.close();
		            oos.close();
		            socket.close();
	            } else {
		            //convert ObjectInputStream object to String
		            String message = (String) object;
		            System.out.println("Message Received: " + message);
		            
		            //write object to Socket
		            oos.writeObject("Hi Client "+message);

		            //close resources
		            ois.close();
		            oos.close();
		            socket.close();
		            
		            //terminate the server if client sends exit request
		            if(message.equalsIgnoreCase("exit")) break;
	            }
	        }
	        System.out.println("Shutting down Socket server!!");
	        
	        //close the ServerSocket object
	        server.close();
	        }catch (Exception e) {
				e.printStackTrace();
			}
	}
}
