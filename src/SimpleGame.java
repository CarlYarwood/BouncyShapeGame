import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import edu.truman.cs260.yarwood.carl.simplegame.*;
/**
 * main runner class for game
 * @author cdy8858
 *
 */
public class SimpleGame {

	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args){
		int delay = 42;
		int second = 1000;
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		Shapes shape = new Shapes();
		JTextField percent = new JTextField("   100%");
		JTextField score = new JTextField("    0 points");
		JTextField shapes = new JTextField("   0 Shapes");
		Timer t1 = new Timer(delay, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				shape.move();
				shape.unreachapble();
				shape.repaint();
				score.setText(""+shape.getPoints()+" points.");
				percent.setText(""+shape.getPercent()+"%");
			}
			
		});
		Timer t2 = new Timer(second, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shape.addShape();
				shapes.setText(""+shape.getShapeCount()+" Shapes");
			}
			
		});
		panel.add(shapes);
		panel.add(score);
		panel.add(percent);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		frame.add(panel);
		frame.add(shape);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		t2.start();
		t1.start();
		frame.setVisible(true);
	}
}
