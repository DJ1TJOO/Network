package me.DJ1TJOO.client.libs.gui;

public class TrippleVariable<A, B, C> {

	private A variable1;
	private B variable2;
	private C variable3;
	
	public TrippleVariable(A variable1, B variable2, C variable3) {
		this.variable1 = variable1;
		this.variable2 = variable2;
		this.variable3 = variable3;
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
	
	public void setVariables(A variable1, B  variable2, C variable3) {
		this.variable1 = variable1;
		this.variable2 = variable2;
		this.variable3 = variable3;
	}

	public C getVariable3() {
		return variable3;
	}

	public void setVariable3(C variable3) {
		this.variable3 = variable3;
	}
	
}
