package dai.llew.snake.game;

import dai.llew.snake.game.sprite.Snake;

import java.awt.Point;

import static dai.llew.snake.game.Constants.Direction;

/**
 * Created by daiLlew on 07/02/2016.
 */
public interface GameHelper {

	Snake snake();

	Direction getDirection();

	void updateDirection(Direction newDir);

	Point nextHeadPoint(Point head);

	Point nextTailPoint(Point tail);

	Constants.GameState getGameState();

	void updateGameState(Constants.GameState state);

	void scored();

	int getScore();
}
