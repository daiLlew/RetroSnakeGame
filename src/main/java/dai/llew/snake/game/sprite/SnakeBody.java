package dai.llew.snake.game.sprite;

import dai.llew.snake.game.GameHelper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Optional;
import java.util.function.Function;

import static dai.llew.snake.game.Constants.BODY_WIDTH;

/**
 * Created by daiLlew on 08/02/2016.
 */
public class SnakeBody extends Sprite {

	private Rectangle rect;
	private Optional<SnakeBody> prev;

	private Function<Point, Point> newPosition = point -> {
		Point newCoord;
		switch (gameHelper.getDirection()) {
			case NORTH:
				newCoord = new Point(point.x, point.y - BODY_WIDTH);
				break;
			case SOUTH:
				newCoord = new Point(point.x, point.y + (BODY_WIDTH * 2));
				break;
			case EAST:
				newCoord = new Point(point.x + BODY_WIDTH, point.y);
				break;
			default:
				newCoord = new Point(point.x - BODY_WIDTH, point.y);
		}
		return newCoord;
	};

	public SnakeBody(GameHelper gameHelper, SnakeBody prev) {
		super(gameHelper);
		this.prev = Optional.ofNullable(prev);
		Point newPos = newPosition.apply(prev.coords());
		this.rect = new Rectangle(newPos.x, newPos.y, BODY_WIDTH, BODY_WIDTH);
	}

	public SnakeBody(GameHelper gameHelper, Point newCoords) {
		super(gameHelper);
		this.prev = Optional.empty();
		this.rect = new Rectangle(newCoords.x, newCoords.y, BODY_WIDTH, BODY_WIDTH);
	}

	@Override
	public boolean isCollide() {
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setPaint(Color.GREEN);
		g.fill(getRect());

		g.setPaint(Color.BLACK);
		g.draw(getRect());
	}

	@Override
	public void move() {
		Point newPos;
		if (prev.isPresent()) {
			System.out.println("prev exists at " + prev.get().coords().toString());
			newPos = prev.get().coords();
		} else {
			newPos = newPosition.apply(new Point(rect.x, rect.y));
		}
		System.out.println("new coords are " + newPos.toString());
		this.rect.setLocation(newPos);
	}

	public Point coords() {
		return new Point(rect.x, rect.y);
	}

	public Rectangle getRect() {
		return rect;
	}
}
