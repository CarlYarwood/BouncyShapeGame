package edu.truman.cs260.yarwood.carl.simplegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Square extends DraggableShape {

	public Square(int max_initial_x_pos, int max_initial_y_pos) {
		super(max_initial_x_pos, max_initial_y_pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		if(p.getX()<=super.getShapeCurrentX()+super.getShapeWidth()&&p.getX()>=super.getShapeCurrentX()&&p.getY()<=super.getShapeCurrentY()+super.getShapeHeight()&&p.getY()>=super.getShapeCurrentY()){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Double(super.getShapeCurrentX(),super.getShapeCurrentY(),super.getShapeWidth(),super.getShapeHeight()));
	}

}
