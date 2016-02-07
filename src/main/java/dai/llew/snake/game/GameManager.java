package dai.llew.snake.game;

import dai.llew.snake.game.sprite.Snake;
import dai.llew.snake.game.sprite.Sprite;
import dai.llew.snake.ui.GUI;
import dai.llew.snake.ui.view.GameScreen;
import dai.llew.snake.ui.view.GameView;

import java.util.function.Consumer;

import static dai.llew.snake.game.Constants.Direction;
import static dai.llew.snake.game.Constants.Direction.SOUTH;

/**
 * Created by daiLlew on 07/02/2016.
 */
public class GameManager implements GameHelper {

	private GUI gui;
	private GameView welcomeView;
	private Sprite snake;
	private Direction direction;

	private Consumer<Sprite> animateSnake = sprite -> {
		new Thread(() -> {
			try {
				while (true) {
					snake.move();
					gui.repaint();
					Thread.sleep(250);
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}).start();
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
		animateSnake.accept(snake);
	}

	private void execute(Runnable job) {
		new Thread(job).start();
	}

	@Override
	public Sprite snake() {
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
