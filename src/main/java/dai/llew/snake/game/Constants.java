package dai.llew.snake.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Stroke;

/**
 * Created by daiLlew on 07/02/2016.
 */
public class Constants {

	/**
	 * The Colour of the snake head.
	 */
	public static final Color HEAD_COLOR = new Color(0, 139, 0);

	/**
	 * The dimensions for the game window.
	 */
	public static final Dimension WINDOW_DIMENSIONS = new Dimension(600, 600);

	/**
	 * The Dimensions of the playing area.
	 */
	public static final Dimension BOARD_DIMENSIONS = new Dimension(550, 550);

	/**
	 * The Width and Height of the Snake body sections.
	 */
	public static final int BODY_WIDTH = 25;

	/**
	 * The Dimensions of the Snake body parts.
	 */
	public static final Dimension BODY_DIMENSIONS = new Dimension(BODY_WIDTH, BODY_WIDTH);

	/**
	 * The max x range used to calculate the apple position.
	 */
	public static final int MAX_X_MULTIPLY_RANGE = BOARD_DIMENSIONS.width / BODY_WIDTH;

	/**
	 * The max y range used to calculate the apple position.
	 */
	public static final int MAX_Y_MULTIPLY_RANGE = BOARD_DIMENSIONS.height / BODY_WIDTH;

	public static final Stroke STROKE = new BasicStroke(2);

	public static final int ARK = 10;

	public static final Font FONT = new Font("Courier New", 1, 35);

	/**
	 * Type to represent the direction of movement.
	 */
	public enum Direction {
		NORTH,

		SOUTH,

		EAST,

		WEST;

		/**
		 * @return the opposite of the current {@link Direction}.
		 */
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

	/**
	 * Type representing the current state of the game.
	 */
	public enum GameState {
		/**
		 * Game is still in play.
		 */
		IN_PLAY,

		/**
		 * Snake collision detected.
		 */
		COLLISION_DETECTED,

		/**
		 * Game is over.
		 */
		GAME_OVER;
	}
}

