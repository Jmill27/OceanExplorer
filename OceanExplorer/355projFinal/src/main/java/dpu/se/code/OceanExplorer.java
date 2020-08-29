package dpu.se.code;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dpu.se.code.interfaces.Drawable;
import dpu.se.code.ships.JetSkiShipDecorator;
import dpu.se.code.ships.PirateShip;
import dpu.se.code.ships.SeaMonster;
import dpu.se.code.ships.Ship;
import dpu.se.code.ships.TurboChargedShipDecorator;
import dpu.se.code.ships.factory.PirateShipFactory;
import dpu.se.codeship.movement.SwimInBoundaryMoveStrategy;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application {

	// public static OceanMap oceanMap = new OceanMap(new Configuration());

	public List<Drawable> drawableShips = new ArrayList<Drawable>();

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage oceanStage) throws Exception {

		// configuration for the game

		// draw the ocean map
		AnchorPane pane = drawGUI();

		// create treasure
		Point treasure = Configuration.getOceanMap().createTreasure();

		// create main ships and the pirate ship

		ButtonType standardShip = new ButtonType("Normal Ship");
		ButtonType turboShip = new ButtonType("Turbo Charged Ship");
		ButtonType jetskiShip = new ButtonType("JetSki Ship");

		// create an alert informing the user to select a ship style
		Alert a = new Alert(AlertType.NONE, "Choose a Ship Sytle", standardShip, turboShip, jetskiShip);
		a.setTitle("Ship Style");
		a.setHeaderText("Ship Selector");
		a.setResizable(true);
		Optional<ButtonType> response = a.showAndWait();

		// use the decorator pattern to enhance the ship
		// image, have the user select using a dialog box
		Ship columbusShip = null;

		if (response.get() == standardShip) {
			columbusShip = new Ship(Configuration.ccShipImage);
		} else if (response.get() == turboShip) {
			columbusShip = new TurboChargedShipDecorator();
		} else if (response.get() == jetskiShip) {
			columbusShip = new JetSkiShipDecorator();
		}

		columbusShip.addTreasure(treasure);
		drawableShips.add(columbusShip);

		// pirate ships & monsters
		createGameElements(columbusShip);

		// draw the scene
		Scene scene = drawShipsOnGUI(oceanStage, pane);

		// start the gui
		oceanStage.show();

		// wait for user events
		listen(columbusShip, scene);
	}

	private AnchorPane drawGUI() throws Exception {
		/*
		 * Draws the ocean map and generates the background
		 */
		AnchorPane pane = new AnchorPane();

		// get the map and draw the grid
		boolean[][] oceanGrid = Configuration.getOceanMap().getMap();

		for (int x = 0; x < Configuration.dimensions; x++) {
			for (int y = 0; y < Configuration.dimensions; y++) {

				// set the colors
				if (!oceanGrid[x][y]) {
					// create the rectangles
					Rectangle rect = new Rectangle(x * Configuration.scale, y * Configuration.scale, Configuration.scale,
							Configuration.scale);
					rect.setStroke(Color.BLACK);
					
					rect.setFill(Color.PALETURQUOISE);
					pane.getChildren().add(rect);
				} else {

					String islandImagePath = new File(Configuration.islandImage).toURI().toString();
					ImageView islandImageView = new ImageView(new Image(islandImagePath, 50, 50, true, true));
					islandImageView.setX(x * Configuration.scale);
					islandImageView.setY(y * Configuration.scale);
					pane.getChildren().add(islandImageView);
				}

			}
		}

		// create moats & monsters
		List<ArrayList<Point>> moats = Configuration.getOceanMap().moats;

		for (ArrayList<Point> coords : moats) {
			for (Point p : coords) {
				int x = p.x;
				int y = p.y;

				Rectangle rect = new Rectangle(x * Configuration.scale, y * Configuration.scale, Configuration.scale,
						Configuration.scale);

				rect.setStroke(Color.BLACK);
				rect.setFill(Color.CADETBLUE);
				pane.getChildren().add(rect);
			}

		}

		return pane;
	}

	private void createGameElements(final Ship columbusShip) throws Exception {
		/*
		 * Creates the pirate ships
		 */

		PirateShipFactory pirateShipFactory = new PirateShipFactory();

		// draw following pirate ships
		int createdPirateShips = 0;
		while (createdPirateShips < Configuration.numberOfFollowingPirateShips) {
			// create the new pirate ship
			PirateShip pirateShip = pirateShipFactory.createPirateShip("follow");
			// watch the columbus ship
			pirateShip.watch(columbusShip);
			// add to be drawn later
			drawableShips.add(pirateShip);
			createdPirateShips++;
		}

		// draw diagnal movement ships
		createdPirateShips = 0;
		while (createdPirateShips < Configuration.numberOfDiagnalPirateShips) {
			// create the new pirate ship
			PirateShip pirateShip = pirateShipFactory.createPirateShip("diagnal_patrol");
			// watch the columbus ship
			pirateShip.watch(columbusShip);
			// add to be drawn later
			drawableShips.add(pirateShip);
			createdPirateShips++;
		}

		// draw horizontal movement ships
		createdPirateShips = 0;
		while (createdPirateShips < Configuration.numberOfHorizantalPirateShips) {
			// create the new pirate ship
			PirateShip pirateShip = pirateShipFactory.createPirateShip("horizantal_patrol");
			// watch the columbus ship
			pirateShip.watch(columbusShip);
			// add to be drawn later
			drawableShips.add(pirateShip);
			createdPirateShips++;
		}

		// create moats & monsters
		for (ArrayList<Point> coords : Configuration.getOceanMap().moats) {
			SeaMonster s = new SeaMonster(Configuration.seaMonsterImage, coords);
			s.setMovementStrategy(new SwimInBoundaryMoveStrategy());
			s.watch(columbusShip);
			drawableShips.add(s);
		}
	}

	private Scene drawShipsOnGUI(Stage oceanStage, AnchorPane pane) throws Exception {
		/*
		 * draw the <drawable> ships on the GUI
		 */

		// create the graphical user interface
		Scene scene = new Scene(pane, Configuration.paneWidth, Configuration.paneHeight);

		oceanStage.setScene(scene);
		oceanStage.setTitle(Configuration.windowTitle);

		// draw the ships on the pane
		for (Drawable ship : drawableShips) {

			if (ship instanceof Ship) {
				// create the treasure
				Point treasure = ((Ship) ship).treasure;
				String treasureImagePath = new File(Configuration.tresureImage).toURI().toString();
				ImageView treasureView = new ImageView(new Image(treasureImagePath, 50, 50, true, true));
				treasureView.setX(treasure.x * Configuration.scale);
				treasureView.setY(treasure.y * Configuration.scale);
				pane.getChildren().add(treasureView);
			}

			// create the image and view
			Image image = new Image(ship.getImage(), 50, 50, true, true);
			ImageView view = new ImageView(image);

			// set the view on the ship
			// to be controlled later
			ship.setImageView(view).placeShipOnImageView();

			// add view to pane
			pane.getChildren().add(view);

		}

		return scene;
	}

	private void listen(final Ship columbusShip, Scene scene) {
		/*
		 * Listen for user key press events, move the columbus ship appropriately
		 */

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				try {
					switch (ke.getCode()) {
					case RIGHT:
						columbusShip.moveEast();
						break;
					case LEFT:
						columbusShip.moveWest();
						break;
					case UP:
						columbusShip.moveNorth();
						break;
					case DOWN:
						columbusShip.moveSouth();
						break;
					default:
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
