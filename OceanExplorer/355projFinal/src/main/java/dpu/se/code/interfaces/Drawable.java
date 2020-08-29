package dpu.se.code.interfaces;

import dpu.se.code.ships.GamePieceBase;
import javafx.scene.image.ImageView;

public interface Drawable {
	public String getImage();
	public GamePieceBase setImageView(ImageView view);
	public void placeShipOnImageView() throws Exception;
}
