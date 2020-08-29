package dpu.se.code;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class OceanMap {
	private boolean[][] _gameLayout;
	private Random rand = new Random();
	
	public List<ArrayList<Point>> moats;
	
	
	public OceanMap() {
		createGrid();
		moats = createSeaMonsters();
		createIslands();
		clearSeaMonsterPlaceholders();
	
	}

	public void clearSeaMonsterPlaceholders() {
		// clear sea monster grid
		for (ArrayList<Point> c : moats) {
			for(Point p : c) {
				_gameLayout[p.x][p.y] = false;
			}
		}
	}
	
	// Return generated map
	public boolean[][] getMap(){	
		return _gameLayout; 
	}
	
	private void createGrid() {
		
		_gameLayout = new boolean[Configuration.dimensions][Configuration.dimensions];
		for (int x = 0; x < Configuration.dimensions; x++) {
			for (int y = 0; y < Configuration.dimensions; y++) {
				_gameLayout[x][y] = false;
			}
		}
	}
	
	public Point placeShip() {
		// place the ship at a random coordinate
		boolean searchingForShipPlacement = true;
		Point shipPlacement = null;
		
		while(searchingForShipPlacement) {
			int x = rand.nextInt(Configuration.dimensions);
			int y = rand.nextInt(Configuration.dimensions);
			
			if(_gameLayout[x][y] == false) {
				shipPlacement = new Point(x,y);
				_gameLayout[x][y] = true;
				searchingForShipPlacement = false;
			}
		}
		return shipPlacement;
	}

	public Point placeShip(int x, int y) {
		// place the ship at a specific coordinate
		Point shipPlacement = null;
		if(_gameLayout[x][y] == false) {	
			shipPlacement = new Point(x,y);
			_gameLayout[x][y] = true;
		}
		return shipPlacement;
	}
	
	private void createIslands() {
		// place the islands randomly 
		int remainingIslands = Configuration.islandCount;
		while(remainingIslands > 0) {
			int x = rand.nextInt(Configuration.dimensions);
			int y = rand.nextInt(Configuration.dimensions);
			
			if(_gameLayout[x][y] == false) {
				remainingIslands--;
				_gameLayout[x][y] = true;
			}
		}
	}
	
	private List<ArrayList<Point>> createSeaMonsters() {
		// create the moats and seamonster coordinate spaces
		List<ArrayList<Point>> coords = new ArrayList<ArrayList<Point>>();
		
		int remainingMoats = 3;
		while(remainingMoats > 0) {
			// randomly determine the spaces
			int x = rand.nextInt(Configuration.dimensions);
			int y = rand.nextInt(Configuration.dimensions);
			
			int length = Configuration.moatLength;
			
			ArrayList<Point> moatCoords = new ArrayList<Point>();
			
			while(length > 0) {
				if(_gameLayout[x][y] == false) {
					moatCoords.add(new Point(x,y));
					_gameLayout[x][y] = true;
					length--;
				}
				// find the available spaces for the moats
				if (x+1 < Configuration.dimensions -1
						&& _gameLayout[x+1][y] == false) {
					x = x+1;
				} else if (x-1 > 0 
						&& _gameLayout[x-1][y] == false) {
					x = x-1;
				} else if (y+1 < Configuration.dimensions -1
						&& _gameLayout[x][y+1] == false) {
					y = y+1;
				} else if (y-1 > 0 
						&& _gameLayout[x][y-1] == false) {
					y = y-1;
				}
			}
			
			coords.add(moatCoords);
			remainingMoats--;
		}
			
		return coords;
	}
	
	public Point createTreasure() {
		// create the treasure 
		// at a random location
		Point treasurePlacement = null;
		boolean placedTreasure = false;
		while(!placedTreasure) {
			int x = rand.nextInt(Configuration.dimensions);
			int y = rand.nextInt(Configuration.dimensions);
			
			if(_gameLayout[x][y] == false) {
				treasurePlacement = new Point(x,y);
				placedTreasure = true;
			}
		}
		return treasurePlacement;
	}
	
}
