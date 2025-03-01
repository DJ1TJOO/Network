package me.DJ1TJOO.server;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.List;

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id, x, y, width, height;
	private Float velX, velY;
	private Boolean up, down, left, right;

	public Client(Integer id, Integer x, Integer y, Integer width, Integer height) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		velX = 0f;
		velY = 0f;
		up = false;
		down = false;
		left = false;
		right = false;
	}

	public void tick(List<Client> clients) {
		x = (int)(x + velX);
		y = (int)(y + velY);
		
		//System.err.println(x + "+" + y);
		if(up && down)
			setVelY(0f);
		else if(up)
			setVelY(getVelY() - 1.2f);
		else if(down)
			setVelY(getVelY() + 1.2f);
		else
			setVelY(0f);
		
		if(left && right)
			setVelX(0f);
		else if(left)
			setVelX(getVelX() - 1.2f);
		else if(right)
			setVelX(getVelX() + 1.2f);
		else
			setVelX(0f);
			
		velX = clamp(velX, -4, 4);
		velY = clamp(velY, -4, 4);
		
		collision(clients);
		//System.err.println(x + "/" + y);
	}
	
	private void collision(List<Client> clients) {
		for (Client client : clients) {
			if(client.getId() == getId()) {
				continue;
			}
			if(getBoundsX().intersects(client.getBoundsX())) {
				if(client.getVelX() < 0) {
					if(x < client.getX() + client.getWidth()/2)x = client.getX() - width;
				} else if(client.getVelX() > 0) {
					if(x > client.getX() + client.getWidth()/2)x = client.getX() + client.getWidth();
				}
				
				if (velX > 0) {
					velX = 0f;
					x = client.getX() - width;
				} else if (velX < 0) {
					velX = 0f;
					x = client.getX() + client.getWidth();
				}
			}
			if(getBoundsY().intersects(client.getBoundsY())) {
				if(client.getVelY() < 0) {
					if(y > client.getY() + client.getHeight()/2)y = client.getY() - height;
				} else if(client.getVelY() > 0) {
					if(y > client.getY() + client.getHeight()/2)y = client.getY() + client.getHeight();
				}
				
				if (velY > 0) {
					velY = 0f;
					y = client.getY() - height;
				} else if (velY < 0) {
					velY = 0f;
					y = client.getY() + client.getHeight();
				}
			}
		}
	}

	private Float clamp(Float f, int min, int max) {
		return Math.max(min, Math.min(max, f));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Float getVelX() {
		return velX;
	}

	public void setVelX(Float velX) {
		this.velX = velX;
	}

	public Float getVelY() {
		return velY;
	}

	public void setVelY(Float velY) {
		this.velY = velY;
	}

	public Boolean getUp() {
		return up;
	}

	public void setUp(Boolean up) {
		this.up = up;
	}

	public Boolean getDown() {
		return down;
	}

	public void setDown(Boolean down) {
		this.down = down;
	}

	public Boolean getLeft() {
		return left;
	}

	public void setLeft(Boolean left) {
		this.left = left;
	}

	public Boolean getRight() {
		return right;
	}

	public void setRight(Boolean right) {
		this.right = right;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
	
	public Rectangle getBoundsX() {
		return new Rectangle((int)(x+velX),y,(int)(width + velX/2),height);
	}
	
	public Rectangle getBoundsY() {
		return new Rectangle(x,(int)(y+velY),width,(int)(height + velY/2));
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", velX="
				+ velX + ", velY=" + velY + ", up=" + up + ", down=" + down + ", left=" + left + ", right=" + right
				+ "]";
	}
	
}
