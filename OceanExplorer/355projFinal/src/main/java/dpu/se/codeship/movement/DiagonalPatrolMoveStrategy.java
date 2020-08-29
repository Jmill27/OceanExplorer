package dpu.se.codeship.movement;

import java.awt.Point;

import dpu.se.code.interfaces.IMovementStrategy;
import dpu.se.code.ships.PirateShip;

public class DiagonalPatrolMoveStrategy implements IMovementStrategy<PirateShip> {
	/*
	 * Diagonal Movement strategy for the patrol pirate ships
	 */
	
	public void move(PirateShip ship, Point p) {
		double currX = ship.location.getX();
		double currY = ship.location.getY();
		
		double shipX = p.getX();
		double shipY = p.getY();
		
		try {
			// ship is east, south of pirate ship
			if ( currX < shipX && currY < shipY) {
				ship.moveSouth();
			}
			// ship is west, north of pirate ship
			if ( currX > shipX && currY > shipY) {
				ship.moveNorth();
			}
			// ship is west, south of pirate ship
			if ( currX > shipX && currY < shipY) {
					ship.moveSouth();
			}
			// ship is east, north of pirate ship
			if ( currX < shipX && currY > shipY) {
				ship.moveNorth();
			}
			// ship is east and on same south/north of pirate ship
			if ( currX < shipX && currY == shipY) {
				ship.moveNorth();
			}
			// ship is west and on same south/north of pirate ship
			if ( currX > shipX && currY == shipY) {
				ship.moveSouth();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println(p.getX() + " / " + p.getY());
	}
}
