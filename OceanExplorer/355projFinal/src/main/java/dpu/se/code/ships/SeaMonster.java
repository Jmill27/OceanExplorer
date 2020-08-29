package dpu.se.code.ships;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dpu.se.code.interfaces.IMovementStrategy;

public class SeaMonster extends GamePieceBase implements Observer {
	
	private IMovementStrategy<SeaMonster> _movementStrategy;
	public ArrayList<Point> _boundary;
	
	public SeaMonster(String image, ArrayList<Point> boundary) throws Exception {
		super(new File(image).toURI().toString(), boundary);
		_movementStrategy = null;
		_boundary = boundary;
		
	}
	
	public void watch(Observable observable) {
		/*
		 * Watch/Observe the actions of passed
		 * in observable class
		 */
		observable.addObserver(this);
	}
	
	public void setMovementStrategy(IMovementStrategy<SeaMonster> strategy) {
		_movementStrategy = strategy;
	}

	public void update(Observable o, Object arg) {
		/*
		 * called on observable update, implements
		 * the algorithm to follow the ship
		 */
		Point p = (Point)arg;

		_movementStrategy.move(this, p);
	}

	

}
