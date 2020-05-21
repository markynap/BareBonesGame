package main;

import java.awt.Color;
import java.awt.Graphics;
/** Represents a Point in 2D space relative to an origin, if no origin is supplied
 *  it is assumed that the middle of the screen is the origin
 * @author mark
 */
public class Point {
	/** x and y coordinates in 2D space relative to the origin */
	private int x, y;
	/** x position of the origin */
	private int originX = Game.WIDTH/2;
	/** y position of the origin */
	private int originY = Game.HEIGHT/2;
	/** Angle this point is from the origin if 0 is along the x-axis */
	private double theta;
	/** The distance this point is from its origin */
	private double distance;
	/** Where distance, angle, and x,y are located relative to */
	private Point origin;
	/** Creates a new point at position (x,y) relative to origin */
	public Point(int x, int y, Point origin) {
		this.origin = origin;
		this.originX = origin.x;
		this.originY = origin.y;
		this.x = x + originX;
		this.y = y + originY;
		setDistAndTheta();
	}
	/** Creates a new point at position (x,y) relative to the center of the screen */
	public Point(int x, int y) {
		this.originX = Game.WIDTH/2;
		this.originY = Game.HEIGHT/2;
		this.x = x + originX;
		this.y = y + originY;
		setDistAndTheta();
	}
	/** Rotates this point by a given angle about its origin
	 * @param amount - change in angle given in radians
	 */
	public void rotate(double amount) {
		theta += amount;
		x = originX + (int)((distance * Math.cos(theta)) + 0.5);
		y = originY - (int)((distance * Math.sin(theta)) + 0.5);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x-2,y-2, 4, 4);
	}
	/** Set this Point's origin */
	public void setOrigin(Point origin) {
		this.origin = origin;
		this.originY = origin.y;
		this.originX = origin.x;
		setDistAndTheta();
	}
	/** Sets the x and y position for the Origin */
	public void setOriginXY(int originX, int originY) {
		this.originX = originX;
		this.originY = originY;
		if (origin != null) {
			origin.x = originX;
			origin.y = originY;
		}
		setDistAndTheta();
	}
	/** x value of this point relative to the Screen */
	public int getWorldX() {
		return x - originX;
	}
	/** y value of this point relative to the Screen */
	public int getWorldY() {
		return y - originY;
	}
	/** x value of this point relative to its origin */
	public int getBodyX() {
		return x;
	}
	/** y value of this point relative to its origin */
	public int getBodyY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
		setDistAndTheta();
	}
	
	public void setY(int y) {
		this.y = y;
		setDistAndTheta();
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
		setDistAndTheta();
	}
	/** Draws a white line from this point to another point */
	public void drawLine(Point otherPoint, Graphics g) {
		g.setColor(Color.white);
		g.drawLine(x, y, otherPoint.getWorldX(), otherPoint.getWorldY());
	}
	/** The origin this point is fixed about */
	public Point getOrigin() {
		if (origin != null) return origin;
		else return new Point(originX, originY);
	}
	
	private void setDistAndTheta() {
		this.theta = Math.atan((originY - y)/(x - originX));
		this.distance = Math.sqrt((x - originX)*(x - originX) + (y - originY)*(y - originY));
	}
	
}
