package dpu.se.code.interfaces;

import java.awt.Point;

public interface IMovementStrategy<T> {
	public void move(T gamePiece, Point p);
}
