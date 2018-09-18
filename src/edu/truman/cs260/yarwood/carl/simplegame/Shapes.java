package edu.truman.cs260.yarwood.carl.simplegame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JComponent;
/**
 * Agregates shapes and handels rules of the game
 * @author cdy8858
 *
 */
public class Shapes extends JComponent {

	private ArrayList<DraggableShape> shapes;
	private Random ran;
	private int goalSize;
	private int goalX;
	private int goalY;
	private int size;
	private int shapesCreated;
	private int points;
	private DraggableShape clickedShape;
	private Point mousepoint;
	/**
	 * initalizes the class and adds mouse listeners
	 */
	public Shapes(){
		size = 500;
		super.setPreferredSize(new Dimension(size,size));
		shapes = new ArrayList<DraggableShape>();
		ran = new Random();
		goalX = 150;
		goalY = 150;
		goalSize = 200;
		shapesCreated = 0;
		points = 0;
		clickedShape = null;
		mousepoint = null;
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				mousepoint = e.getPoint();
				for(int i = 0; i<shapes.size();i++){
					if(shapes.get(i).contains(mousepoint)){
						clickedShape = shapes.get(i);
						shapes.remove(i);
						return;
					}
				}
			}
			public void mouseReleased(MouseEvent e){
				if(clickedShape == null){
					return;
				}
				if(score(clickedShape)){
					clickedShape = null;
					points++;
				}
				else{
					shapes.add(clickedShape);
					clickedShape = null;
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				if(clickedShape == null){
					return;
				}
				Point lastPoint = mousepoint;
				mousepoint = e.getPoint();
				double dx = mousepoint.getX()-lastPoint.getX();
				double dy = mousepoint.getY()-lastPoint.getY();
				clickedShape.move((int)dx,(int)dy);
			}
		});
	}
	/**
	 * adds a new shape to the array list and increments the shpaes created
	 */
	public void addShape(){
		int temp = ran.nextInt(3);
		if(temp == 0){
			shapes.add(new Circle(size-50,size-50));
		}
		else if(temp == 1){
			shapes.add(new Square(size-50,size-50));
		}
		else{
			shapes.add(new Triangle(size-50,size-50));
		}
		shapesCreated++;
	}
	/**
	 * paints all the shapes
	 */
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i<shapes.size(); i++){
			shapes.get(i).draw(g2);
		}
		if(clickedShape != null){
			clickedShape.draw(g2);
		}
		g2.setColor(Color.RED);
		g2.drawRect(goalX, goalY, goalSize, goalSize);
	}
	/**
	 * determins if a shape is unreachable, and if so, removes it
	 */
	public void unreachapble(){
		for(int i=0; i<shapes.size();i++){
			if(shapes.get(i).getShapeCurrentX()>size||shapes.get(i).getShapeCurrentY()>size||shapes.get(i).getShapeCurrentX()+shapes.get(i).getShapeWidth()<0||shapes.get(i).getShapeCurrentY()+shapes.get(i).getShapeHeight()<0){
				shapes.remove(i);
			}
		}
	}
	/**
	 * retruns a boolean as to whether a not a shape has scored
	 * @param shape being checked
	 * @return whether is scored or not
	 */
	private boolean score(DraggableShape shape){
		if(shape.getShapeCurrentX()>=goalX&&shape.getShapeCurrentY()>goalY&&shape.getShapeCurrentX()<goalX+goalSize&&shape.getShapeCurrentY()<goalY+goalSize){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * moves all the shapes in the list
	 */
	public void move(){
		for(int i = 0; i<shapes.size();i++){
			shapes.get(i).move();
		}
	}
	/**
	 * gets points scored
	 * @return points scored
	 */
	public int getPoints(){
		return points;
	}
	/**
	 * gets the number of shapes that has been created
	 * @return number of shapes created
	 */
	public int getShapeCount(){
		return shapesCreated;
	}
	/**
	 * gets the percent of shapes created scored with
	 * @return percent of shapes scored with
	 */
	public int getPercent(){
		if(shapesCreated>0){
			double num = (double) points;
			double dem = (double) shapesCreated;
			double percent = num/dem;
			percent *=100;
			return (int) percent;
		}
		else{
			return 100;
		}
		
	}

	
}
