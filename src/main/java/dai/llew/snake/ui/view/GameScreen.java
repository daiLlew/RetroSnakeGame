package dai.llew.snake.ui.view;

import dai.llew.snake.game.GameHelper;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Optional;

import static dai.llew.snake.game.Constants.Direction;
import static dai.llew.snake.game.Constants.Direction.EAST;
import static dai.llew.snake.game.Constants.Direction.NORTH;
import static dai.llew.snake.game.Constants.Direction.SOUTH;
import static dai.llew.snake.game.Constants.Direction.WEST;

/**
 * Created by daiLlew on 07/02/2016.
 */
public class GameScreen extends GameView {

	public GameScreen(GameHelper gameHelper) {
		super(gameHelper);
	}

	@Override
	public void updateDisplay(Graphics2D g) {
		gameHelper.snake().draw(g);
	}

	@Override
	public void handleKeyPressed(KeyEvent e) {
		Optional<Direction> newDir = Optional.empty();

		switch (e.getKeyCode()) {
			case (37):
				newDir = Optional.of(WEST);
				break;
			case (38):
				newDir = Optional.of(NORTH);
				break;
			case (39):
				newDir = Optional.of(EAST);
				break;
			case (40):
				newDir = Optional.of(SOUTH);
				break;
		}

		if (newDir.isPresent()) {
			gameHelper.updateDirection(newDir.get());
		}
	}
}
