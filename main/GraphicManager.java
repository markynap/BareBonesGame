package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GraphicManager {
	
	public void renderBoxOutline(Graphics g, int x, int y, int width, int height, int thickness, Color lineColor) {
		g.setColor(lineColor);
		for (int i = 0; i < thickness; i++) {
			g.drawRect(x + i, y + i, width, height);
		}
	}
	
	public void renderBoxFilled(Graphics g, int x, int y, int width, int height, int thickness, Color lineColor, Color fillColor) {
		renderBoxOutline(g, x, y, width, height, thickness, lineColor);
		g.setColor(fillColor);
		g.fillRect(x + thickness, y + thickness, width - thickness, height - thickness);
	}
	
	public void renderBoxWithString(Graphics g, int x, int y, int width, int height, int thickness,
			Color lineColor, Color fillColor, String str, Color textColor, Font font, int gravity) {
		renderBoxFilled(g, x, y, width, height, thickness, lineColor, fillColor);
		g.setColor(textColor);
		g.setFont(font);
		g.drawString(str, x + thickness + 2 + (gravity * width/4), y + 2*height/3);
	}
}
