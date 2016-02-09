package dai.llew.snake.game;

import dai.llew.snake.game.sprite.Snake;
import dai.llew.snake.game.sprite.Sprite;
import dai.llew.snake.ui.GUI;
import dai.llew.snake.ui.view.GameScreen;
import dai.llew.snake.ui.view.GameView;

import static dai.llew.snake.game.Constants.Direction;
import static dai.llew.snake.game.Constants.Direction.SOUTH;

/**
 * Created by daiLlew on 07/02/2016.
 */
public class GameManager implements GameHelper {

	private GUI gui;
	private GameView welcomeView;
	private Snake snake;
	private Direction direction;

	private Runnable animateSnake = () -> {
		try {
			while (true) {
				gui.repaint();
				Thread.sleep(2000);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	};

	public static void main(String[] args) {
		new GameManager().play();
	}

	public GameManager() {
		this.welcomeView = new GameScreen(this);
		this.direction = SOUTH;
		this.snake = new Snake(this);
		this.gui = new GUI(welcomeView);
	}

	public void play() {
		execute(animateSnake);
	}

	private void execute(Runnable job) {
		new Thread(job).start();
	}

	@Override
	public Snake snake() {
		return this.snake;
	}

	@Override
	public Constants.Direction getDirection() {
		return direction;
	}

	@Override
	public void updateDirection(Direction newDir) {
		this.direction = newDir;
	}
}
