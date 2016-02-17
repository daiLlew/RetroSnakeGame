package dai.llew.snake.ui.view;

import dai.llew.snake.game.Constants;
import dai.llew.snake.game.GameHelper;
import dai.llew.snake.game.sprite.Snake;
import dai.llew.snake.game.sprite.Sprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Optional;

import static dai.llew.snake.game.Constants.BOARD_DIMENSIONS;
import static dai.llew.snake.game.Constants.Direction;
import static dai.llew.snake.game.Constants.Direction.EAST;
import static dai.llew.snake.game.Constants.Direction.NORTH;
import static dai.llew.snake.game.Constants.Direction.SOUTH;
import static dai.llew.snake.game.Constants.Direction.WEST;
import static dai.llew.snake.game.Constants.FONT;
import static dai.llew.snake.game.Constants.WINDOW_DIMENSIONS;

/**
 * Created by daiLlew on 07/02/2016.
 */
public class GameScreen extends GameView {

	private boolean toggle = true;

	public GameScreen(GameHelper gameHelper) {
		super(gameHelper);
	}

	@Override
	public void updateDisplay(Graphics2D g) {
		if (gameHelper.getGameState().equals(Constants.GameState.COLLISION_DETECTED)) {
			collisionAnimation(g);
		} else {
			Sprite.animate(g);
			displayScore(g);
		}
	}

	private void collisionAnimation(Graphics2D g) {
		if (toggle) {
			g.setPaint(Color.RED);
		} else {
			g.setPaint(Color.WHITE);
		}
		g.fill(new Rectangle(0, 0, 600, 600));
		Sprite.getSnake().draw(g);
		toggle = !toggle;
	}

	@Override
	public void handleKeyPressed(KeyEvent e) {
		Optional<Direction> newDir = Optional.empty();
		Direction current = gameHelper.getDirection();

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

		if (newDir.isPresent() && !current.equals(newDir.get().opposite())) {
			gameHelper.updateDirection(newDir.get());
		}
	}

	private void displayScore(Graphics2D g) {
		FontMetrics fm = g.getFontMetrics();

		g.setPaint(Color.BLACK);
		g.setFont(FONT);
		String msg = String.format("Score: %s", gameHelper.getScore());
		int x = (WINDOW_DIMENSIONS.width / 2) - fm.stringWidth(msg);
		g.drawString(msg, x, 30);
	}
}
