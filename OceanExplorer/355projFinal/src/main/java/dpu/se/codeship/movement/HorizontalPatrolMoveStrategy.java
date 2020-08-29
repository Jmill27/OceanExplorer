package dpu.se.codeship.movement;

import java.awt.Point;

import dpu.se.code.interfaces.IMovementStrategy;
import dpu.se.code.ships.PirateShip;

public class HorizontalPatrolMoveStrategy implements IMovementStrategy<PirateShip> {
	/*
	 * Horizontal Movement strategy for the patrol pirate ships
	 */
	
	public void move(PirateShip ship, Point p) {
		double currX = ship.location.getX();
		double currY = ship.location.getY();
		
		double shipX = p.getX();
		double shipY = p.getY();
		
		try {
			// ship is east, south of pirate ship
			if ( currX < shipX && currY < shipY) {
				ship.moveEast();
			}
			// ship is west, north of pirate ship
			if ( currX > shipX && currY > shipY) {
				ship.moveWest();
			}
			// ship is west, south of pirate ship
			if ( currX > shipX && currY < shipY) {
				ship.moveWest();
			}
			// ship is east, north of pirate ship
			if ( currX < shipX && currY > shipY) {
				ship.moveEast();
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
				ship.moveEast();
			}
			// ship is north and on same east/west of pirate ship
			if ( currX == shipX && currY > shipY) {
				ship.moveWest();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
