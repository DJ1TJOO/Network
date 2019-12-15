package me.DJ1TJOO.server;

import java.io.Serializable;

public class Package implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Object[] args;
	
	public Package(Integer id, Object... args) {
		this.id = id;
		this.args = args;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}
