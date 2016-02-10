package dai.llew.snake.game;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Created by daiLlew on 07/02/2016.
 */
public class Constants {

	public static final Color HEAD_COLOR = new Color(0, 139, 0);

	public static final Dimension WINDOW_DIMENSIONS = new Dimension(600, 600);

	public static final Dimension BOARD_DIMENSIONS = new Dimension(550, 550);

	public static final int BODY_WIDTH = 25;

	public static final Dimension BODY_DIMENSIONS = new Dimension(BODY_WIDTH, BODY_WIDTH);

	public enum Direction {
		NORTH,

		SOUTH,

		EAST,

		WEST;

		public Direction opposite() {
			switch (this) {
				case WEST:
					return EAST;
				case EAST:
					return WEST;
				case SOUTH:
					return NORTH;
				default:
					return SOUTH;
			}
		}
	}

	public enum GameState {
		IN_PLAY,

		COLLISION_DETECTED
	}
}

