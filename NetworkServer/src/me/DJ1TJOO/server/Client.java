package me.DJ1TJOO.server;

import java.io.Serializable;

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id, x, y;
	private Float velX, velY;
	private Boolean up, down, left, right;

	public Client(Integer id, Integer x, Integer y) {
		this.id = id;
		this.x = x;
		this.y = y;
		velX = 0f;
		velY = 0f;
		up = false;
		down = false;
		left = false;
		right = false;
	}

	public void tick() {
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
		x = (int)(x + velX);
		y = (int)(y + velY);
		//System.err.println(x + "/" + y);
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
	
}
