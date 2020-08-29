package dpu.se.code.ships;

import java.awt.Point;

import dpu.se.code.Configuration;

public class JetSkiShipDecorator extends Ship {
	
	public Point treasure;
	
	public JetSkiShipDecorator() throws Exception {
		super(Configuration.ccJetSkiShip);	
	}	 
	
}
