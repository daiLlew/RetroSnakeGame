package dai.llew.snake.game;

import dai.llew.snake.game.sprite.Apple;
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
import static dai.llew.snake.game.Constants.GameState.GAME_OVER;
import static dai.llew.snake.game.Constants.GameState.IN_PLAY;
import static dai.llew.snake.game.Level.ONE;

public class GameManager implements GameHelper {

	private GUI gui;
	private GameView welcomeView;
	private Snake snake;
	private Direction direction;
	private Direction newDirection;
	private GameState gameState = IN_PLAY;
	private int score = 0;
	private Level level = Level.next();
	private int appleCount = 0;

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
		addApple();
		try {
			while (!gameState.equals(GAME_OVER)) {
				if (gameState.equals(IN_PLAY)) {
					inPlay();
				} else if (gameState.equals(COLLISION_DETECTED)) {
					collisionDetected();
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.exit(0);
	};

	private void inPlay() throws InterruptedException {
		direction = newDirection;
		gui.repaint();
		Thread.sleep(this.level.speed());
		addApple();
	}

	private void collisionDetected() throws InterruptedException {
		for (int i = 0; i < 7; i++) {
			gui.repaint();
			Thread.sleep(400);
		}
		updateGameState(GAME_OVER);
	}

	public void play() {
		execute(playGame);
	}

	private void addApple() {
		if (!Sprite.appleInPlay()) {
			new Apple(this);
		}
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

	@Override
	public void updateGameState(GameState state) {
		this.gameState = state;
	}

	@Override
	public void scored() {
		this.appleCount++;
		this.score += 1 * level.scoreMultiplier();
		if ((appleCount % 10) == 0) {
			this.level = Level.next();
			System.out.println("You reached level " + this.level.name());
		}
	}

	@Override
	public int getScore() {
		return this.score;
	}
}
