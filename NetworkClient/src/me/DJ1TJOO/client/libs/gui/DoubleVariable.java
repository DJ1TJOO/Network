package me.DJ1TJOO.client.libs.gui;

public class DoubleVariable<A, B> {

	private A variable1;
	private B variable2;
	
	public DoubleVariable(A variable1, B variable2) {
		this.variable1 = variable1;
		this.variable2 = variable2;
	}

	public A getVariable1() {
		return variable1;
	}
	
	public void setVariable1(A variable1) {
		this.variable1 = variable1;
	}
	
	public B getVariable2() {
		return variable2;
	}
	
	public void setVariable2(B  variable2) {
		this.variable2 = variable2;
	}
	
	public void setVariables(A variable1, B  variable2) {
		this.variable2 = variable2;
		this.variable1 = variable1;
	}
	
}
