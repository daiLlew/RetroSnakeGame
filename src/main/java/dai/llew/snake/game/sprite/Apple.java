package dai.llew.snake.game.sprite;

import dai.llew.snake.game.GameHelper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import static dai.llew.snake.game.Constants.BODY_DIMENSIONS;
import static dai.llew.snake.game.Constants.BODY_WIDTH;
import static dai.llew.snake.game.Constants.MAX_X_MULTIPLY_RANGE;
import static dai.llew.snake.game.Constants.MAX_Y_MULTIPLY_RANGE;
import static dai.llew.snake.game.Constants.STROKE;

public class Apple extends Sprite {

	private static final Color APPLE_BOARDER = new Color(150, 0, 0);
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
		g.fillOval(area.x, area.y, area.width, area.height);

		g.setStroke(STROKE);
		g.setPaint(APPLE_BOARDER);
		g.drawOval(area.x, area.y, area.width, area.height);
	}

	@Override
	public void move() {
		// Apple doesn't move.
	}

	@Override
	public Rectangle getArea() {
		return this.area;
	}

	@Override
	public void handleCollision() {
		Sprite.getSnake().addBody();
		Sprite.removeApple(this);
	}
}
