package dpu.se.code.ships.factory;

import dpu.se.code.Configuration;
import dpu.se.code.ships.PirateShip;
import dpu.se.codeship.movement.DiagonalPatrolMoveStrategy;
import dpu.se.codeship.movement.HorizontalPatrolMoveStrategy;

public class PirateShipFactory extends ShipFactory {
	
	public PirateShipFactory() { }
	
	public PirateShip createPirateShip(String type) throws Exception {
		// Initialize the ship with the factory pattern
		// set the ship strategy within the initialization.
		PirateShip ship = null;
		
		if (type.equals("horizantal_patrol")){
			ship = new PirateShip(Configuration.horizantalShipImage);
			ship.setMovementStrategy(new HorizontalPatrolMoveStrategy());
		} else if (type.equals("diagnal_patrol")) {
			ship = new PirateShip(Configuration.diagnalShipImage);
			ship.setMovementStrategy(new DiagonalPatrolMoveStrategy());
		} else if (type.equals("follow")) {
			// default strategy
			ship = new PirateShip(Configuration.followShipImage);
		}
		
		return ship;
	}
}
