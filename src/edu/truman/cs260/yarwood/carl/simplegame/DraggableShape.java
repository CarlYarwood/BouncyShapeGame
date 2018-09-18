package edu.truman.cs260.yarwood.carl.simplegame;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

public abstract class DraggableShape {
	private int currentX, currentY, xRandomMovement, yRandomMovement;

	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;

	public static final int MAX_X_VARIATION = 10;
	public static final int MAX_Y_VARIATION = 10;

	public static final Random r = new Random();

	/**
	 * Construct a new DraggableShape, place it in a random location in the
	 * component, and calculate a random velocity for it to move when it is
	 * animated.
	 * 
	 * @param max_initial_x_pos
	 *            the maximum initial x position of the object
	 * @param max_initial_y_pos
	 *            the maximum initial y position of the object
	 */
	public DraggableShape(int max_initial_x_pos, int max_initial_y_pos) {
		// Calculate the initial position as a random coordinate
		// based on the values passed to the constructor.
		currentX = r.nextInt(max_initial_x_pos);
		currentY = r.nextInt(max_initial_y_pos);

		// Calculate the random movement. The variation gives the width of the
		// range.
		xRandomMovement = r.nextInt(MAX_X_VARIATION) - MAX_X_VARIATION / 2;
		yRandomMovement = r.nextInt(MAX_Y_VARIATION) - MAX_Y_VARIATION / 2;
	}

	/**
	 * Does this shape contain p?
	 * 
	 * @param p
	 *            the point to test for containership
	 * @return true of this shape contains p, and false otherwise
	 */
	public abstract boolean contains(Point p);

	/**
	 * Move this object by a given amount. Used only when dragging the shape.
	 * 
	 * @param dx
	 *            the x amount to move the shape
	 * @param dy
	 *            the y amount to move the shape
	 */
	public void move(int dx, int dy) {
		currentX += dx;
		currentY += dy;
	}

	/**
	 * Move this shape by the random amount determined when the shape was
	 * created. Used during animation.
	 * 
	 */
	public void move() {
		currentX += xRandomMovement;
		currentY += yRandomMovement;
	}

	/**
	 * Draw this shape in the graphics context
	 * 
	 * @param g2
	 *            the graphics context
	 */
	public abstract void draw(Graphics2D g2);

	/**
	 * Return the current x coordinate of this shape's bounding box
	 * 
	 * @return the x coordinate
	 */
	public int getShapeCurrentX() {
		return currentX;
	}

	/**
	 * Return the current x coordinate of this shape's bounding box
	 * 
	 * @return the y coordinate
	 */
	public int getShapeCurrentY() {
		return currentY;
	}

	/**
	 * Return the width of this shape.
	 * 
	 * @return the width
	 */
	public int getShapeWidth() {
		return WIDTH;
	}

	/**
	 * Return the height of this shape.
	 * 
	 * @return the height
	 */
	public int getShapeHeight() {
		return HEIGHT;
	}
}
