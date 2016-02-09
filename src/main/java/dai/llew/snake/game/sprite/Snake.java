package dai.llew.snake.game.sprite;

import dai.llew.snake.game.GameHelper;

import java.awt.Point;
import java.util.LinkedList;
import java.util.function.Function;

import static dai.llew.snake.game.Constants.BODY_WIDTH;

public class Snake {

	private LinkedList<SnakeBody> body;
	private GameHelper gameHelper;

	private Function<Point, Point> calculateNewPos = current -> {
		Point newCoord;
		switch (gameHelper.getDirection()) {
			case NORTH:
				newCoord = new Point(current.x, current.y - BODY_WIDTH);
				break;
			case SOUTH:
				newCoord = new Point(current.x, current.y + BODY_WIDTH);
				break;
			case EAST:
				newCoord = new Point(current.x + BODY_WIDTH, current.y);
				break;
			default:
				newCoord = new Point(current.x - BODY_WIDTH, current.y);
		}
		return newCoord;
	};

	public Snake(GameHelper gameHelper) {
		this.gameHelper = gameHelper;
		this.body = new LinkedList<>();
		body.add(new SnakeBody(gameHelper, new Point(100, 100)));
		addBody();
	}

	public void addBody() {
		body.add(new SnakeBody(gameHelper, body.getLast()));
	}
}
