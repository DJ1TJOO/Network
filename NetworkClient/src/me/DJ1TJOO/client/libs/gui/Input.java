package me.DJ1TJOO.client.libs.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Input extends Element {

	private InputType type;
	private Font font;
	private int borderOffset;
	private String text;

	private TrippleVariable<Integer, Boolean, Integer> cursor;
	private DoubleVariable<Integer, Integer> selectedText;
	public enum InputType {
		TEXT, IP4;
	}
	
	public Input(int x, int y, int width, int height, Color color, Gui gui, InputType type, Font font) {
		super(0, x, y, width, height, color, gui);
		this.type = type;
		this.font = font;
		this.text = "";
		this.selectedText = new DoubleVariable<Integer, Integer>(0, 0);
		this.cursor = new TrippleVariable<Integer, Boolean, Integer>(50, true, 50);
	}
	
	public Input(Location horizontal, Location vertical, int width, int height, Color color, Gui gui, InputType type, Font font) {
		super(0, 0, 0, width, height, color, gui);
		this.setVertical(vertical);
		this.setHorizontal(horizontal);
		this.type = type;
		this.font = font;
		this.text = "";
		this.selectedText = new DoubleVariable<Integer, Integer>(0, 0);
		this.cursor = new TrippleVariable<Integer, Boolean, Integer>(50, true, 50);
	}
	
	public Input(int x, int y, int borderOffset, Color color, Gui gui, InputType type, Font font) {
		super(0, x, y, 0, 0, color, gui);
		this.type = type;
		this.font = font;
		this.borderOffset = borderOffset;
		this.text = "";
		this.selectedText = new DoubleVariable<Integer, Integer>(0, 0);
		this.cursor = new TrippleVariable<Integer, Boolean, Integer>(50, true, 50);
	}
	
	public Input(Location horizontal, Location vertical, int borderOffset, Color color, Gui gui, InputType type, Font font) {
		super(0, 0, 0, 0, 0, color, gui);
		this.setVertical(vertical);
		this.setHorizontal(horizontal);
		this.type = type;
		this.font = font;
		this.borderOffset = borderOffset;
		this.text = "";
		this.selectedText = new DoubleVariable<Integer, Integer>(0, 0);
		this.cursor = new TrippleVariable<Integer, Boolean, Integer>(50, true, 50);
	}

	public void render(Graphics g) {
		int x = getX();
		int y = getY();
		
		if(getX() == 0 && getY() == 0 && getVertical() != null) {
			switch (getVertical()) {
				case LEFT:
					break;
				case RIGHT:
					break;
				case TOP:
					y = getGui().getElementOffset();
					for (Element element : getGui().getElements()) {
						if(element.getId() < getId()) {
							if(element.getVertical() == getVertical()) {
								if(intersects(element.getLastX(), element.getLastWidth(), getLastX(), getLastWidth())) {
									y += element.getLastHeight() + getGui().getElementOffset();
								}
							}
						}
					}
					break;
				case BOTTOM:
					y = getGui().getElementOffset() + getGui().getHeight() - getLastHeight();
					for (Element element : getGui().getElements()) {
						if(element.getId() < getId()) {
							if(element.getVertical() == getVertical()) {
								if(intersects(element.getLastX(), element.getLastWidth(), getLastX(), getLastWidth())) {
									y -= element.getLastHeight() + getGui().getElementOffset();
								}
							}
						}
					}
					break;
				case CENTER:
					y = getGui().getHeight()/2 - getLastHeight()/2;
					for (Element element : getGui().getElements()) {
						if(element.getId() < getId()) {
							if(element.getVertical() == getVertical()) {
								if(intersects(element.getLastX(), element.getLastWidth(), getLastX(), getLastWidth())) {
									y += element.getLastHeight() + getGui().getElementOffset();
								}	
							}
						}
					}
					break;
				default:
					break;
			}
		}
		if(getX() == 0 && getY() == 0 && getHorizontal() != null) {
			switch (getHorizontal()) {
				case LEFT:
					x = getGui().getElementOffset();
					for (Element element : getGui().getElements()) {
						if(element.getId() < getId()) {
							if(element.getHorizontal() == getHorizontal()) {
								if(intersects(element.getLastY(), element.getLastHeight(), y, getLastHeight())) {
									x += element.getLastWidth() + getGui().getElementOffset();
								}
							}
						}
					}
					break;
				case RIGHT:
					x = getGui().getElementOffset() + getGui().getWidth() - getLastWidth();
					for (Element element : getGui().getElements()) {
						if(element.getId() < getId()) {
							if(element.getHorizontal() == getHorizontal()) {
								if(intersects(element.getLastY(), element.getLastHeight(), y, getLastHeight())) {
									x -= element.getLastWidth() + getGui().getElementOffset();
								}
							}
						}
					}
					break;
				case TOP:
					break;
				case BOTTOM:
					break;
				case CENTER:
					x = getGui().getWidth()/2 - getLastWidth()/2;
					for (Element element : getGui().getElements()) {
						if(element.getId() < getId()) {
							if(element.getHorizontal() == getHorizontal()) {
								if(intersects(element.getLastY(), element.getLastHeight(), y, getLastHeight())) {
									x += element.getLastWidth() + getGui().getElementOffset();
								}
							}
						}
					}
					break;
				default:
					break;
			}
		}
		
		Color beforeColor = g.getColor();
		Font beforeFont = g.getFont();
		
		g.setColor(getColor());
		g.setFont(getFont());
		
		Rectangle2D textBounds = g.getFontMetrics().getStringBounds(getText(), g);
		
		if(getWidth() == 0 && getHeight() == 0 && getBorderOffset() != 0) {
			g.drawRect(x + getGui().getX(), y + getGui().getY(), (int)(textBounds.getWidth() + 2 * borderOffset), (int)(textBounds.getHeight() + 2 * borderOffset));
			setLastWidth((int)(textBounds.getWidth() + 2 * borderOffset));
			setLastHeight((int)(textBounds.getHeight() + 2 * borderOffset));
			g.drawString(getText(), (int) (x + getGui().getX() + borderOffset), (int)(y + getGui().getY() + borderOffset + g.getFontMetrics().getAscent()));
		} else {
			g.drawRect(x + getGui().getX(), y + getGui().getY(), getWidth(), getHeight());
			setLastWidth(getWidth());
			setLastHeight(getHeight());
			g.drawString(getText(), (int) (x + getGui().getX() + getWidth()/2 - textBounds.getWidth()/2), (int)(y + getGui().getY() + getHeight()/2 - textBounds.getHeight()/2 + g.getFontMetrics().getAscent()));
		}
		
		int i = 0;
		int width = 0;
		List<Integer> widthList = new ArrayList<Integer>(); 
		for (char text : getText().toCharArray()) {
			i++;
			if((i > this.selectedText.getVariable1() && i <= this.selectedText.getVariable2() + this.selectedText.getVariable1()) || (i <= this.selectedText.getVariable1() && i > this.selectedText.getVariable2() + this.selectedText.getVariable1())) {
				Color color = new Color(getColor().getRed(), getColor().getGreen(), getColor().getBlue(), 100);
				g.setColor(color);
				g.fillRect(getGui().getX() + x + borderOffset + width, y + getGui().getY() + borderOffset, g.getFontMetrics().charWidth(text), (int)textBounds.getHeight());
				g.setColor(getColor());
			}
			widthList.add(g.getFontMetrics().charWidth(text));
			width += g.getFontMetrics().charWidth(text);
		}
		if(isSelected() && this.cursor.getVariable2()) {
			int lineOffset = 0;
			int j = 0;
			for (Integer integer : widthList) {
				j++;
				if(j <= this.selectedText.getVariable1()) {
					lineOffset += integer;
				}
			}
			g.fillRect(getGui().getX() + x + borderOffset + lineOffset, y + getGui().getY() + borderOffset, 2, (int)textBounds.getHeight());
		}
		
		setLastX(x);
		setLastY(y);
		
		g.setColor(beforeColor);
		g.setFont(beforeFont);
	}

	public void tick() {
		this.cursor.setVariable1(this.cursor.getVariable1() - 1);
		if(this.cursor.getVariable1() <= 0) {
			this.cursor.setVariable1(this.cursor.getVariable3());
			this.cursor.setVariable2(!this.cursor.getVariable2());
		}
	}

	public void keyPressed(KeyEvent e) {
		if(isSelected()) {
			switch (type) {
			case IP4:
				if(e.getKeyCode() == KeyEvent.VK_0 || e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_3
						|| e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_5 || e.getKeyCode() == KeyEvent.VK_6 
						|| e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_8 || e.getKeyCode() == KeyEvent.VK_9 
						|| e.getKeyCode() == KeyEvent.VK_NUMPAD1 || e.getKeyCode() == KeyEvent.VK_NUMPAD2 || e.getKeyCode() == KeyEvent.VK_NUMPAD3 
						|| e.getKeyCode() == KeyEvent.VK_NUMPAD4 || e.getKeyCode() == KeyEvent.VK_NUMPAD5 || e.getKeyCode() == KeyEvent.VK_NUMPAD6 
						|| e.getKeyCode() == KeyEvent.VK_NUMPAD7 || e.getKeyCode() == KeyEvent.VK_NUMPAD8 || e.getKeyCode() == KeyEvent.VK_NUMPAD9 
						|| e.getKeyCode() == KeyEvent.VK_NUMPAD0 || e.getKeyCode() == KeyEvent.VK_PERIOD) {
					if(this.text.length() < 15) {
						addText(e.getKeyChar(), this.selectedText.getVariable1());
						this.selectedText.setVariable1(this.selectedText.getVariable1() + 1);
						this.selectedText.setVariable2(0);
					}
				}
				break;

			default:
				break;
			}
			 if(e.getKeyCode() == KeyEvent.VK_LEFT && this.getGui().getState().getKeyInput().getShift()) {
				 int b = this.selectedText.getVariable2() - 1;
				 if(b < -this.selectedText.getVariable1()) {
					 b = -this.selectedText.getVariable1();
				 }
				 if(b > this.text.length()-this.selectedText.getVariable1()) {
					 b = this.text.length()-this.selectedText.getVariable1();
				 }
				 this.selectedText.setVariable2(b);
			 }
			 
			 if(e.getKeyCode() == KeyEvent.VK_RIGHT && this.getGui().getState().getKeyInput().getShift()) {
				 int b = this.selectedText.getVariable2() + 1;
				 if(b < -this.selectedText.getVariable1()) {
					 b = -this.selectedText.getVariable1();
				 }
				 if(b > this.text.length()-this.selectedText.getVariable1()) {
					 b = this.text.length()-this.selectedText.getVariable1();
				 }
				 this.selectedText.setVariable2(b);
			 }
			 
			 if(e.getKeyCode() == KeyEvent.VK_LEFT && !this.getGui().getState().getKeyInput().getShift()) {
				 int a = this.selectedText.getVariable1() - 1;
				 if(a < 0) {
					 a = 0;
				 }
				 if(a > this.text.length() + 1) {
					 a = this.text.length() + 1;
				 }
				 this.selectedText.setVariable1(a);
				 this.selectedText.setVariable2(0);
			 }
			 
			 if(e.getKeyCode() == KeyEvent.VK_RIGHT && !this.getGui().getState().getKeyInput().getShift()) {
				 int a = this.selectedText.getVariable1() + 1;
				 if(a < 0) {
					 a = 0;
				 }
				 if(a > this.text.length() + 1) {
					 a = this.text.length() + 1;
				 }
				 this.selectedText.setVariable1(a);
				 this.selectedText.setVariable2(0);
			 }
			 
			 if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				 if(this.text.length() > 0) {
					 if(this.selectedText.getVariable2() == 0) {
						 String start = this.text.substring(0, this.selectedText.getVariable1() - 1);
						 String end = "";
						 if(this.selectedText.getVariable1() != this.text.length()) {
							 end = this.text.substring(this.selectedText.getVariable1(), this.text.length());
						 }
						 this.text = start + end;
						 this.selectedText.setVariable1(this.selectedText.getVariable1()-1);
					 } else {
						 String start = "";//this.text.substring(this.selectedText.getVariable1(), this.selectedText.getVariable1() - 1)
						 String end = "";//this.text.substring(this.selectedText.getVariable1(), this.text.length())
						 //3 en 2
						 if(this.selectedText.getVariable2() < 0) {
							 start = this.text.substring(0, this.selectedText.getVariable1() + this.selectedText.getVariable2());
							 end = this.text.substring(this.selectedText.getVariable1(), this.text.length());
							 this.selectedText.setVariables(this.selectedText.getVariable1() + this.selectedText.getVariable2(), 0);
						 } else {
							 start = this.text.substring(0, this.selectedText.getVariable1());
							 end = this.text.substring(this.selectedText.getVariable1() + this.selectedText.getVariable2(), this.text.length());
							 this.selectedText.setVariable2(0);
						 }
						 this.text = start + end;
					 }
				 }
			 }
		}
	}

	public InputType getType() {
		return type;
	}

	public void setType(InputType type) {
		this.type = type;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public int getBorderOffset() {
		return borderOffset;
	}

	public void setBorderOffset(int borderOffset) {
		this.borderOffset = borderOffset;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void addText(char text, int position) {
		if(position >= this.text.length()) {
			this.text += text;
		} else {
			this.text = this.text.substring(0, position) + text + this.text.substring(position, this.text.length());
		}
	}
	
	public TrippleVariable<Integer, Boolean, Integer> getCursor() {
		return cursor;
	}

	public void setCursor(TrippleVariable<Integer, Boolean, Integer> cursor) {
		this.cursor = cursor;
	}
}
