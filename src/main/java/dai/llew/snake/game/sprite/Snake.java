package dai.llew.snake.game.sprite;

import dai.llew.snake.game.GameHelper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.LinkedList;

import static dai.llew.snake.game.Constants.BOARD_DIMENSIONS;
import static dai.llew.snake.game.Constants.BODY_DIMENSIONS;
import static dai.llew.snake.game.Constants.HEAD_COLOR;
import static dai.llew.snake.game.Constants.WINDOW_DIMENSIONS;

public class Snake extends Sprite {

	//private Rectangle boarder = new Rectangle(new Point(25, 10), BOARD_DIMENSIONS);
	private Rectangle boarder = new Rectangle(0, 0, 600, 575);
	private Rectangle head;
	private LinkedList<Rectangle> body;

	public Snake(GameHelper gameHelper) {
		super(gameHelper);
		this.body = new LinkedList<>();
		this.head = new Rectangle(new Point(BOARD_DIMENSIONS.width/2, BOARD_DIMENSIONS.height/2), BODY_DIMENSIONS);
		for (int i=0; i < 12; i++) {
			addBody();
		}
	}

	@Override
	public boolean isCollide() {
		return isBoarderCollision() || isBodyCollide();
	}

	private boolean isBodyCollide() {
		Rectangle next = new Rectangle(gameHelper.nextHeadPoint(head.getLocation()), BODY_DIMENSIONS);
		return body.stream().filter(body -> body.contains(next)).findFirst().isPresent();
	}

	private boolean isBoarderCollision() {
		Rectangle next = new Rectangle(gameHelper.nextHeadPoint(head.getLocation()), BODY_DIMENSIONS);
		return !boarder.contains(next);
	}

	@Override
	public void draw(Graphics2D g) {
		g.draw(boarder);
		drawSnake(g, head, HEAD_COLOR);
		body.forEach(rectangle -> drawSnake(g, rectangle, Color.GREEN));
	}

	private void drawSnake(Graphics2D g, Rectangle rect, Color c) {
		g.setPaint(c);
		g.fill(rect);
		g.setPaint(Color.gray);
		g.draw(rect);
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
}
