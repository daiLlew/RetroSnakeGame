package dai.llew.snake.game;

import java.awt.Dimension;

/**
 * Created by daiLlew on 07/02/2016.
 */
public class Constants {

	public static final Dimension WINDOW_DIMENSIONS = new Dimension(1000, 800);

	public static final int BODY_WIDTH = 25;

	public enum Direction {
		NORTH,

		SOUTH,

		EAST,

		WEST;
	}
}