package dai.llew.snake.game;

import dai.llew.snake.game.sprite.Snake;
import dai.llew.snake.game.sprite.Sprite;
import dai.llew.snake.ui.GUI;
import dai.llew.snake.ui.view.GameScreen;
import dai.llew.snake.ui.view.GameView;

import java.awt.Point;

import static dai.llew.snake.game.Constants.BODY_WIDTH;
import static dai.llew.snake.game.Constants.Direction;
import static dai.llew.snake.game.Constants.Direction.SOUTH;
import static dai.llew.snake.game.Constants.GameState;
import static dai.llew.snake.game.Constants.GameState.COLLISION_DETECTED;
import static dai.llew.snake.game.Constants.GameState.IN_PLAY;


public class GameManager implements GameHelper {

	private GUI gui;
	private GameView welcomeView;
	private Snake snake;
	private Direction direction;
	private Direction newDirection;
	private GameState gameState = IN_PLAY;

	public static void main(String[] args) {
		new GameManager().play();
	}

	public GameManager() {
		this.welcomeView = new GameScreen(this);
		this.direction = SOUTH;
		this.newDirection = SOUTH;
		this.snake = new Snake(this);
		this.gui = new GUI(welcomeView);
	}

	private Runnable playGame = () -> {
		try {
			while (true) {
				if (gameState.equals(IN_PLAY)) {
					direction = newDirection;
					gui.repaint();
					checkCollisions();
					Thread.sleep(300);
				} else if (gameState.equals(COLLISION_DETECTED)) {
					while (true) {
						gui.repaint();
						Thread.sleep(400);
					}
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.exit(0);
	};

	private void checkCollisions() {
		if (Sprite.checkCollisions()) {
			this.gameState = COLLISION_DETECTED;
		}
	}

	public void play() {
		execute(playGame);
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
		this.newDirection = newDir;
	}

	@Override
	public Point nextHeadPoint(Point head) {
		Point newCoord;
		switch (getDirection()) {
			case NORTH:
				newCoord = new Point(head.x, head.y - BODY_WIDTH);
				break;
			case SOUTH:
				newCoord = new Point(head.x, head.y + BODY_WIDTH);
				break;
			case EAST:
				newCoord = new Point(head.x + BODY_WIDTH, head.y);
				break;
			default:
				newCoord = new Point(head.x - BODY_WIDTH, head.y);
		}
		return newCoord;
	}

	@Override
	public Point nextTailPoint(Point tail) {
		Point newCoord;
		switch (getDirection()) {
			case NORTH:
				newCoord = new Point(tail.x, tail.y + BODY_WIDTH);
				break;
			case SOUTH:
				newCoord = new Point(tail.x, tail.y - BODY_WIDTH);
				break;
			case EAST:
				newCoord = new Point(tail.x - BODY_WIDTH, tail.y);
				break;
			default:
				newCoord = new Point(tail.x + BODY_WIDTH, tail.y);
		}
		return newCoord;
	}

	@Override
	public GameState getGameState() {
		return this.gameState;
	}
}
