package dpu.se.code.ships;

import java.awt.Point;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import dpu.se.code.interfaces.IMovementStrategy;
import dpu.se.codeship.movement.FollowMoveStrategy;

public class PirateShip extends GamePieceBase implements Observer {
	
	private IMovementStrategy<PirateShip> _movementStrategy;
	
	public PirateShip(String image) throws Exception {
		super(new File(image).toURI().toString());
		_movementStrategy = new FollowMoveStrategy();
	}
	
	public void watch(Observable observable) {
		/*
		 * Watch/Observe the actions of passed
		 * in observable class
		 */
		observable.addObserver(this);
	}
	
	public void setMovementStrategy(IMovementStrategy<PirateShip> strategy) {
		// decouple the movement strategy from the client
		_movementStrategy = strategy;
	}

	public void update(Observable o, Object arg) {
		/*
		 * called on observable update, implements
		 * the algorithm to follow the ship
		 */
		Point p = (Point)arg;
		// perform the move on observer update
		_movementStrategy.move(this, p);
	}

	

}
