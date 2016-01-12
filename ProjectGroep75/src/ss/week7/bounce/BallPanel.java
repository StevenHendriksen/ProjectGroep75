package ss.week7.bounce;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.swing.JFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * BallPanel a special JPanel for drawing balls on. Used with TimedBouncer.
 * 
 * @version 2005.02.22
 */
public class BallPanel extends JPanel implements ActionListener {
	private Map<Ball, JFrame> balls; // @invariant balls != null
	private javax.swing.Timer timer;
	private ActionListener actionlistener;

	public BallPanel() {
		balls = new java.util.HashMap<Ball, JFrame>();
		timer = new javax.swing.Timer(1, this);
		timer.start();
	}

	/**
	 * Implements the method from the interface ActionListener Move and repaint
	 * the balls
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		moveBalls();
		repaint();
	}

	public void animate() {
		try {
			while (true) {
				Thread.sleep(5);
				moveBalls();
				repaint();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add a new ball to the ball list and start the timer if not yet running.
	 */
	public synchronized void addNewBall(JFrame f) {
		if(balls.size() < 2000){
		balls.put(new Ball(this), f);
		}
	}

	/**
	 * Move all balls BEWARE: collision effects are not respecting Snellius'
	 * law.
	 */
	public synchronized void moveBalls() {
		for (Ball b : balls.keySet()) {
			b.move();
		}

		// collision detection
		List<Ball> ix2 = new ArrayList<Ball>(balls.keySet());
		ListIterator<Ball> ix = ix2.listIterator();
		while (ix.hasNext()) {
			Ball b = ix.next();
			ListIterator<Ball> jx = ix2.listIterator(ix.nextIndex());
			while (jx.hasNext()) {
				Ball other = jx.next();
				b.collide(other,balls.get(b));
			}
		}
	}

	/**
	 * Overrides paintComponent in JPanel. Is called if repaint is called.
	 * Paints all elements of balls.
	 */
	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Ball b : balls.keySet()) {
			b.draw(g);
		}
	}

	public class AnimateThread extends Thread {
		private BallPanel ball;

		public AnimateThread(BallPanel ball) {
			this.ball = ball;
		}

		public void run() {
			ball.animate();
		}
	}
}
