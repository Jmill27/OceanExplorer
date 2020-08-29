package dpu.se.code.ships.factory;

import dpu.se.code.ships.PirateShip;

public abstract class ShipFactory {
	// abstract ship constructor
	abstract PirateShip createPirateShip(String type) throws Exception;
		
}
