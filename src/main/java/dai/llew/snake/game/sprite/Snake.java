package dai.llew.snake.game.sprite;

import dai.llew.snake.game.GameHelper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static dai.llew.snake.game.Constants.BODY_WIDTH;

public class Snake extends Sprite {

	private List<Rectangle> body;

	private Function<Rectangle, Point> newPosition = rect -> {
		Point newCoord;
		switch (gameHelper.getDirection()) {
			case NORTH:
				newCoord = new Point(rect.x, rect.y - BODY_WIDTH);
				break;
			case SOUTH:
				newCoord = new Point(rect.x, rect.y + BODY_WIDTH);
				break;
			case EAST:
				newCoord = new Point(rect.x + BODY_WIDTH, rect.y);
				break;
			default:
				newCoord = new Point(rect.x - BODY_WIDTH, rect.y);
		}
		return newCoord;
	};

	public Snake(GameHelper gameHelper) {
		super(gameHelper);
		this.body = new ArrayList<>();
		body.add(new Rectangle(100, 100, BODY_WIDTH, BODY_WIDTH));
		increase();
		increase();
	}

	@Override
	public boolean isCollide() {
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		this.body.stream().forEach(rect -> {
			g.setPaint(Color.GREEN);
			g.fill(rect);

			g.setPaint(Color.BLACK);
			g.draw(rect);
		});
	}

	public void increase() {
		Rectangle tail = this.body.get(body.size() - 1);
		Rectangle newTail;

		switch (gameHelper.getDirection()) {
			case NORTH:
				newTail = new Rectangle(tail.x, tail.y - BODY_WIDTH, BODY_WIDTH, BODY_WIDTH);
				break;
			case SOUTH:
				newTail = new Rectangle(tail.x, tail.y + BODY_WIDTH, BODY_WIDTH, BODY_WIDTH);
				break;
			case EAST:
				newTail = new Rectangle(tail.x + BODY_WIDTH, tail.y, BODY_WIDTH, BODY_WIDTH);
				break;
			default:
				newTail = new Rectangle(tail.x - BODY_WIDTH, tail.y, BODY_WIDTH, BODY_WIDTH);
		}
		this.body.add(newTail);
	}

	public void move() {
		Point p = newPosition.apply(body.get(0));
		body.add(0, new Rectangle(p.x, p.y, BODY_WIDTH, BODY_WIDTH));
		body.remove(body.size() - 1);
	}
}
