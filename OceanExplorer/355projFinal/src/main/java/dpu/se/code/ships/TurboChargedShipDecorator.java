package dpu.se.code.ships;

import java.awt.Point;

import dpu.se.code.Configuration;

public class TurboChargedShipDecorator extends Ship {
	
	public Point treasure;
	
	public TurboChargedShipDecorator() throws Exception {
		super(Configuration.ccTurboChargedShipImage);	
	}	 
	
}
