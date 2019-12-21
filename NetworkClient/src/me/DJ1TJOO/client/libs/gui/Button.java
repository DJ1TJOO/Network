package me.DJ1TJOO.client.libs.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Button extends Element {

	private String text;
	private Font font;
	private int borderOffset;
	private Runnable action;
	
	public Button(int x, int y, int width, int height, Color color, Gui gui, String text, Font font, Runnable action) {
		super(0, x, y, width, height, color, gui);
		this.text = text;
		this.font = font;
		this.borderOffset = 0;
		this.action = action;
	}
	
	public Button(int x, int y, Color color, Gui gui, String text, Font font, int borderOffset, Runnable action) {
		this(x, y, 0, 0, color, gui, text, font, action);
		this.text = text;
		this.font = font;
		this.borderOffset = borderOffset;
	}
	
	public Button(Location horizontal, Location vertical, int width, int height, Color color, Gui gui, String text, Font font, Runnable action) {
		super(0, 0, 0, width, height, color, gui);
		this.text = text;
		this.font = font;
		this.borderOffset = 0;
		this.setVertical(vertical);
		this.setHorizontal(horizontal);
		this.action = action;
	}
	
	public Button(Location horizontal, Location vertical, Color color, Gui gui, String text, Font font, int borderOffset, Runnable action) {
		this(0, 0, 0, 0, color, gui, text, font, action);
		this.text = text;
		this.font = font;
		this.borderOffset = borderOffset;
		this.setVertical(vertical);
		this.setHorizontal(horizontal);
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
		
		if(getWidth() == 0 && getHeight() == 0 && getBorderOffset() != 0) {
			Rectangle2D textBounds = g.getFontMetrics().getStringBounds(getText(), g);
			g.drawRect(x + getGui().getX(), y + getGui().getY(), (int)(textBounds.getWidth() + 2 * borderOffset), (int)(textBounds.getHeight() + 2 * borderOffset));
			setLastWidth((int)(textBounds.getWidth() + 2 * borderOffset));
			setLastHeight((int)(textBounds.getHeight() + 2 * borderOffset));
			g.drawString(getText(), (int) (x + getGui().getX() + borderOffset), (int)(y + getGui().getY() + borderOffset + g.getFontMetrics().getAscent()));
		} else {
			g.drawRect(x + getGui().getX(), y + getGui().getY(), getWidth(), getHeight());
			setLastWidth(getWidth());
			setLastHeight(getHeight());
			Rectangle2D textBounds = g.getFontMetrics().getStringBounds(getText(), g);
			g.drawString(getText(), (int) (x + getGui().getX() + getWidth()/2 - textBounds.getWidth()/2), (int)(y + getGui().getY() + getHeight()/2 - textBounds.getHeight()/2 + g.getFontMetrics().getAscent()));
		}
		
		setLastX(x);
		setLastY(y);
		
		g.setColor(beforeColor);
		g.setFont(beforeFont);
	}

	public void tick() {
		
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Runnable getAction() {
		return action;
	}

	public void setAction(Runnable action) {
		this.action = action;
	}

}
