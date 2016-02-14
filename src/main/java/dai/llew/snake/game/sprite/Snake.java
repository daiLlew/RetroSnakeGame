package dai.llew.snake.game.sprite;

import dai.llew.snake.game.Constants;
import dai.llew.snake.game.GameHelper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Optional;

import static dai.llew.snake.game.Constants.BOARD_DIMENSIONS;
import static dai.llew.snake.game.Constants.BODY_DIMENSIONS;
import static dai.llew.snake.game.Constants.BODY_WIDTH;
import static dai.llew.snake.game.Constants.HEAD_COLOR;
import static dai.llew.snake.game.Constants.WINDOW_DIMENSIONS;
import static dai.llew.snake.game.Constants.ARK;

/**
 * Class defines the Snake that the player moves around the board.
 */
public class Snake extends Sprite {

	private Rectangle boarder = new Rectangle(0, 0, 600, 575);
	private Rectangle head;
	private LinkedList<Rectangle> body;

	/**
	 * Construct a new Snake.
	 */
	public Snake(GameHelper gameHelper) {
		super(gameHelper);
		this.body = new LinkedList<>();
		this.head = new Rectangle(new Point(BOARD_DIMENSIONS.width / 2, BOARD_DIMENSIONS.height / 2), BODY_DIMENSIONS);
		addBody();
		addBody();
		addBody();
		addBody();
	}

	@Override
	public boolean isCollide() {
		return isBoarderCollision() || isBodyCollide();
	}

	private boolean isBodyCollide() {
		Rectangle next = new Rectangle(gameHelper.nextHeadPoint(head.getLocation()), BODY_DIMENSIONS);
		return body.stream().filter(body -> body.equals(next)).findFirst().isPresent();
	}

	private boolean isBoarderCollision() {
		switch (gameHelper.getDirection()) {
			case NORTH:
				return head.y <= 0;
			case SOUTH:
				return (head.y + BODY_WIDTH) >= boarder.height;
			case EAST:
				return (head.x + BODY_WIDTH) >= WINDOW_DIMENSIONS.width;
			case WEST:
				return head.x <= 0;
		}
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.draw(boarder);
		drawSnake(g, head, Optional.of(getPoly(head)));
		body.forEach(rectangle -> drawSnake(g, rectangle, Optional.empty()));
	}

	private void drawSnake(Graphics2D g, Rectangle rect, Optional<Polygon> poly) {
		g.setStroke(new BasicStroke(2));
		g.setPaint(Color.GREEN);
		g.fillRoundRect(rect.x, rect.y, BODY_WIDTH, BODY_WIDTH, ARK, ARK);

		if (poly.isPresent()) {
			g.setPaint(HEAD_COLOR);
			g.fill(getPoly(rect));
		}

		g.setPaint(HEAD_COLOR);
		g.drawRoundRect(rect.x, rect.y, BODY_WIDTH, BODY_WIDTH, ARK, ARK);
	}

	@Override
	public void move() {
		Point currentPos = head.getLocation();
		head.setLocation(gameHelper.nextHeadPoint(currentPos));
		Point prePos = currentPos;

		for (Rectangle rect : body) {
			prePos = updateLocation(rect, prePos);
		}
	}

	@Override
	public Rectangle getArea() {
		return this.head;
	}

	@Override
	public void handleCollision() {
		gameHelper.updateGameState(Constants.GameState.COLLISION_DETECTED);
	}

	private Point updateLocation(Rectangle rect, Point newPoint) {
		Point current = rect.getLocation();
		rect.setLocation(newPoint);
		return current;
	}

	public void addBody() {
		Point newTail;
		if (body.isEmpty()) {
			newTail = gameHelper.nextTailPoint(head.getLocation());
		} else {
			newTail = gameHelper.nextTailPoint(body.getLast().getLocation());
		}
		body.add(new Rectangle(newTail, BODY_DIMENSIONS));
	}

	private Polygon getPoly(Rectangle body) {
		int[] x;
		int[] y;
		switch (gameHelper.getDirection()) {
			case SOUTH:
				x = new int[]{body.x, body.x + (BODY_WIDTH / 2), body.x + BODY_WIDTH};
				y = new int[]{body.y, body.y + BODY_WIDTH, body.y};
				break;
			case NORTH:
				x = new int[]{body.x, body.x + (BODY_WIDTH / 2), body.x + BODY_WIDTH};
				y = new int[]{body.y + BODY_WIDTH, body.y, body.y + BODY_WIDTH};
				break;
			case EAST:
				x = new int[]{body.x, body.x + BODY_WIDTH, body.x};
				y = new int[]{body.y, body.y + (BODY_WIDTH / 2), body.y + BODY_WIDTH};
				break;
			default:
				// WEST
				x = new int[]{body.x + BODY_WIDTH, body.x, body.x + BODY_WIDTH};
				y = new int[]{body.y, body.y + (BODY_WIDTH / 2), body.y + BODY_WIDTH};
		}
		return new Polygon(x, y, 3);
	}
}
