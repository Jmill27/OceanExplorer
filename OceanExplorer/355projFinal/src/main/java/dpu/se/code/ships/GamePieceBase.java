package dpu.se.code.ships;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import dpu.se.code.Configuration;
import dpu.se.code.interfaces.Drawable;
import javafx.scene.image.ImageView;


public class GamePieceBase extends Observable implements Drawable {
	
	public Point location;
	protected ImageView imageView;
	
	private String shipImage;

	public ArrayList<Point> _boundary = null; 
	
	public GamePieceBase(String image) throws Exception {
		/*
		 * Create a movement controller for the ship
		 */
		shipImage = image;
		location = Configuration.getOceanMap().placeShip();
	}
	
	public GamePieceBase(String image, ArrayList<Point> boundary) throws Exception {
		/*
		 * Create a movement controller for the ship
		 */
		shipImage = image;
		Point startingPoint = boundary.get(0);
		location = Configuration.getOceanMap().placeShip(startingPoint.x, startingPoint.y);
		_boundary = boundary;
	}
	
	public String getImage() {
		return shipImage;
	}
	
	public GamePieceBase setImageView(ImageView view) {
		/*
		 * Set the Image View
		 */
		imageView = view;
		return this;
	}
	
	public void placeShipOnImageView() throws Exception {
		/*
		 * place the ship on the image view 
		 */
		updateGUILocation();
	}

	private void moveObject(int x, int y) throws Exception {
		/*
		 * Performs the move object routine, updates the map
		 * and sets the new location on the image view
		 */
		
		boolean canMove = true;
		if (null != _boundary) {
			// if the the class has set the boundary
			// it is bounded to only move within a 
			// point range, check to determine that
			// requested movement is within the range
			for (Point p : _boundary) {
				if (x == p.getX() && y == p.getY()) {
					canMove = true;
					break;
				}
				canMove = false;
			}
		}
		
		if (canMove) {
			// perform the move
			Configuration.getOceanMap().getMap()[x][y] = true;
			Configuration.getOceanMap().getMap()[location.x][location.y] = false;
			location.setLocation(x, y);
			
			updateGUILocation();
			
			setChanged();
			notifyObservers(location);
		}
	}
	
	private void updateGUILocation() throws Exception {
		/*
		 * update the location on the imageview 
		 */
		if (null != imageView) {
			imageView.setX(location.x * Configuration.scale);
			imageView.setY(location.y * Configuration.scale);
		} else {
			String message = "imageView needs to be set with [public ShipBase setImageView(ImageView view)].";
			throw new Exception(message);
		}
	}
	
	protected Boolean rangeIsValid(int coord) {
		/*
		 * Determines if the proposed coordinate is within the range
		 * Since we are only moving on the y axis or the x axis at a time
		 * we don't need booth coordinates within this calculation 
		 */
		return (coord > -1 && coord < Configuration.dimensions);
	}
	
	protected Boolean availableOnMap(int x, int y) {
		/*
		 * Determines if the space is available on the map
		 */
		return !Configuration.getOceanMap().getMap()[x][y];
	}
	
	public void moveEast() throws Exception {
		/*
		 * Move the object east 
		 */
		int x = location.x+1;
		int y = location.y;

		if (rangeIsValid(x) && 
				availableOnMap(x,y)) {
			moveObject(x, y);
		}
	}

	public void moveWest() throws Exception {
		/*
		 * Move the object west
		 */
		int x = location.x-1;
		int y = location.y;

		if (rangeIsValid(x) && 
				availableOnMap(x,y)) {
			moveObject(x, y);
		}		
		
	}

	public void moveNorth() throws Exception {
		/*
		 * Move the object north
		 */
		int x = location.x;
		int y = location.y-1;
		
		if (rangeIsValid(y) && 
				availableOnMap(x,y)) {
			moveObject(x, y);
		}
	}

	public void moveSouth() throws Exception {
		/*
		 * Move the object South
		 */
		int x = location.x;
		int y = location.y+1;
		
		if (rangeIsValid(y) && 
				availableOnMap(x,y)) {
			moveObject(x, y);
		}
	}

}
