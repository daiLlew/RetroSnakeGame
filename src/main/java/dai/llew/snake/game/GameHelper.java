package dai.llew.snake.game;

import dai.llew.snake.game.sprite.Sprite;
import static dai.llew.snake.game.Constants.Direction;

/**
 * Created by daiLlew on 07/02/2016.
 */
public interface GameHelper {

	Sprite snake();

	Direction getDirection();

	void updateDirection(Direction newDir);
}
