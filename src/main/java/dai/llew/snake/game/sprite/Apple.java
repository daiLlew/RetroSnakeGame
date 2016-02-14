package dai.llew.snake.game.sprite;

import dai.llew.snake.game.GameHelper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import static dai.llew.snake.game.Constants.ARK;
import static dai.llew.snake.game.Constants.BODY_DIMENSIONS;
import static dai.llew.snake.game.Constants.BODY_WIDTH;
import static dai.llew.snake.game.Constants.MAX_X_MULTIPLY_RANGE;
import static dai.llew.snake.game.Constants.MAX_Y_MULTIPLY_RANGE;

public class Apple extends Sprite {

	private static final Color APPLE_BOARDER = new Color(153, 0, 0);
	private int[] xPoints = new int[3];
	private int[] yPoints = new int[3];
	private Rectangle area;
	private Random random;

	public Apple(GameHelper gameHelper) {
		super(gameHelper);
		random = new Random();

		int xMultiplier = random.nextInt(MAX_X_MULTIPLY_RANGE);
		int yMultiplier = random.nextInt(MAX_Y_MULTIPLY_RANGE);

		Point point = new Point(BODY_WIDTH * xMultiplier, BODY_WIDTH * yMultiplier);
		this.area = new Rectangle(point, BODY_DIMENSIONS);
	}

	@Override
	public boolean isCollide() {
		return area.equals(Sprite.getSnake().getArea());
	}

	@Override
	public void draw(Graphics2D g) {
		g.setPaint(Color.RED);
		g.fillRoundRect(area.x, area.y, area.width, area.height, ARK, ARK);

		g.setPaint(APPLE_BOARDER);
		g.drawRoundRect(area.x, area.y, area.width, area.height, ARK, ARK);

		g.setPaint(Color.GREEN);
		xPoints = new int[] {area.x + 2, area.x + (BODY_WIDTH / 2), area.x + BODY_WIDTH - 2};
		yPoints = new int[] {area.y, area.y + (BODY_WIDTH / 2), area.y};
		g.fillPolygon(xPoints, yPoints, 3);
	}

	@Override
	public void move() {
		// Apple doesnt move.
	}

	@Override
	public Rectangle getArea() {
		return this.area;
	}

	@Override
	public void handleCollision() {
		Sprite.removeApple(this);
		Sprite.getSnake().addBody();
	}
}
