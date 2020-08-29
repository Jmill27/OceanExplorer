package dpu.se.codeship.movement;

import java.awt.Point;
import java.util.Random;

import dpu.se.code.interfaces.IMovementStrategy;
import dpu.se.code.ships.PirateShip;

public class FollowMoveStrategy implements IMovementStrategy<PirateShip> {
	/*
	 * Free/Chase Movement strategy for the pirate ships
	 */
	
	public void move(PirateShip ship, Point p) {
		double currX = ship.location.getX();
		double currY = ship.location.getY();
		
		double shipX = p.getX();
		double shipY = p.getY();
		
		Random rand = new Random();
		try {
			// ship is east, south of pirate ship
			if ( currX < shipX && currY < shipY) {
				int randMove = rand.nextInt(2);
				if (randMove == 1) {
					ship.moveSouth();
				} else {
					ship.moveEast();
				}
			}
			// ship is west, north of pirate ship
			if ( currX > shipX && currY > shipY) {
				int randMove = rand.nextInt(2);
				if (randMove == 1) {
					ship.moveNorth();
				} else {
					ship.moveWest();
				}
			}
			// ship is west, south of pirate ship
			if ( currX > shipX && currY < shipY) {
				int randMove = rand.nextInt(2);
				if (randMove == 1) {
					ship.moveSouth();
				} else {
					ship.moveWest();
				}
			}
			// ship is east, north of pirate ship
			if ( currX < shipX && currY > shipY) {
				int randMove = rand.nextInt(2);
				if (randMove == 1) {
					ship.moveNorth();
				} else {
					ship.moveEast();
				}
			}
			// ship is east and on same south/north of pirate ship
			if ( currX < shipX && currY == shipY) {
				ship.moveEast();
			}
			// ship is west and on same south/north of pirate ship
			if ( currX > shipX && currY == shipY) {
				ship.moveWest();
			}
			// ship is north and on same east/west of pirate ship
			if ( currX == shipX && currY < shipY) {
				ship.moveSouth();
			}
			// ship is north and on same east/west of pirate ship
			if ( currX == shipX && currY > shipY) {
				ship.moveNorth();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
