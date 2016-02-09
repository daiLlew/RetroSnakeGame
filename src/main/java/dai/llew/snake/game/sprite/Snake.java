package dai.llew.snake.game.sprite;

import dai.llew.snake.game.GameHelper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import static dai.llew.snake.game.Constants.BOARD_DIMENSIONS;
import static dai.llew.snake.game.Constants.BODY_DIMENSIONS;

public class Snake extends Sprite {

	private Rectangle boarder = new Rectangle(new Point(25, 25), BOARD_DIMENSIONS);
	private Rectangle head;
	private LinkedList<Rectangle> body;

	public Snake(GameHelper gameHelper) {
		super(gameHelper);
		this.body = new LinkedList<>();
		this.head = new Rectangle(new Point(100, 100), BODY_DIMENSIONS);
		addBody();
		addBody();
	}

	@Override
	public boolean isCollide() {
		return isBoarderCollison();
	}

	private boolean isBoarderCollison() {
		return !boarder.contains(head);
	}

	@Override
	public void draw(Graphics2D g) {
		drawSnake(g, head, Color.GREEN);
		body.forEach(rectangle -> drawSnake(g, rectangle, Color.GREEN));
	}

	private void drawSnake(Graphics2D g, Rectangle rect, Color c) {
		g.setPaint(c);
		g.fill(rect);
		g.setPaint(Color.GREEN);
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
