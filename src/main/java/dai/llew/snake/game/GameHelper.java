package dai.llew.snake.game;

import dai.llew.snake.game.sprite.Snake;

import static dai.llew.snake.game.Constants.Direction;

/**
 * Created by daiLlew on 07/02/2016.
 */
public interface GameHelper {

	Snake snake();

	Direction getDirection();

	void updateDirection(Direction newDir);
}
