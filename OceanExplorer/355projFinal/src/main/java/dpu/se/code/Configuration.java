package dpu.se.code;

public class Configuration {

	private Configuration() { }
	
	private static OceanMap oceanMap; 
	
	public static OceanMap getOceanMap() {
		if (oceanMap == null) {
			oceanMap = new OceanMap();
		}
		return oceanMap;
	}
	
	// dimensions of the oceanMap/grid
	public static int dimensions = 15; //10
	public static int islandCount = 10;
	
	// dimensions of the pane
	public static int paneWidth = 750; //500
	public static int paneHeight = 750; //500
	
	// pane scaling
	public static int scale = 50; 
	
	//islands
	public static String islandImage = "external_files/island.jpg";
	
	// CC ship and treasure creation
	public static String ccShipImage = "external_files/ship.png";
	public static String ccTurboChargedShipImage = "external_files/turbochargedship.png";
	public static String ccJetSkiShip = "external_files/jetski.png";
	
	public static String tresureImage = "external_files/treasure.png"; 
	
	// free range ships, that follow CC ship
	public static int numberOfFollowingPirateShips = 2;
	public static String followShipImage = "external_files/pirateship.png";
	
	// diagonal moving ships that only move left/right
	public static int numberOfDiagnalPirateShips = 1;
	public static String diagnalShipImage = "external_files/patrolship.png";
	
	// horizontal moving ships, that only move up/down
	public static int numberOfHorizantalPirateShips = 1;
	public static String horizantalShipImage = "external_files/patrolship.png";
	
	// moat dimensions, the area in which the sea monsters swim
	public static int numberOfMoats = 3;
	public static int moatLength = 5;
	
	// number of sea monsters to create
	public static int numberOfSeaMonsters = 3;
	public static String seaMonsterImage = "external_files/monster.png";
	
	// title of the game
	public static String windowTitle = "Ocean Explorer Game";
	
	
}
