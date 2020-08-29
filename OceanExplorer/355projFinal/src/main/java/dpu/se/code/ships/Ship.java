package dpu.se.code.ships;

import java.awt.Point;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import dpu.se.code.Notification;
import dpu.se.code.interfaces.Drawable;

public class Ship extends GamePieceBase implements Drawable, Observer {
	
	public Point treasure;
	
	private Notification _notify = new Notification();
	
	public Ship(String ccShipImage) throws Exception {
		super(new File(ccShipImage).toURI().toString());	
	}

	public void addTreasure(Point treasure) {
		// allow the columbus ship to hold the 
		// coordinate for the trasure, this will be used to
		// determine if the user has won 
		
		this.treasure = treasure;
		
		// add self as an observer, to determine if 
		// it has won
		this.addObserver((Observer) this);
	}
	
	public void update(Observable o, Object arg) {
		/*
		 * called on observable update, implements
		 * the algorithm to follow the ship
		 */
		Point p = (Point)arg;

		// on movement check if it has reached
		// the treasure, if so notify the user
		if (p.equals(treasure)) {
			_notify.win();
		}
	}
	
	
	 
	
}
