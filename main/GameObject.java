package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/** An object belonging to this Game, can change fields as needed */
public abstract class GameObject {
	
	/** Reference to the game */
	protected Game game;
	/** x and y position on game's coordinate system */
	protected double x, y;
	/** Image representing this object */
	protected Image img;

	public GameObject(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
	}
	
	public GameObject(Game game) {
		this.game = game;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract Rectangle[] getBounds();
	
	public abstract Point getMidPoint();
	
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/** Moves the object a specified amount in x and y directions */
	public void moveObject(double xAmount, double yAmount) {
		x += xAmount;
		y += yAmount;
	}
	
	public String toString() {
		return getClass().getName() + " at pos (" + x + ","  + y + ")";
	}
	
	/** Returns the True distance between the two points if you drew a line between them */
	public double getTrueDist(GameObject other) {
		return Math.sqrt((x - other.x)*(x - other.x) + (y - other.y)*(y - other.y));
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public boolean intersects(GameObject other) {
		for (Rectangle rect : getBounds()) {
			for (Rectangle enRect : other.getBounds()) {
				if (rect.intersects(enRect)) return true;
			}
		}
		return false;
	}
}
