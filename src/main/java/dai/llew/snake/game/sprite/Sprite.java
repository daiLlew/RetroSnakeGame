package dai.llew.snake.game.sprite;

import dai.llew.snake.game.GameHelper;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class defines common behaviour for Sprite implementations.
 */
public abstract class Sprite {

	private static Sprite snake = null;
	private static List<Sprite> apples = new ArrayList<>();

	public static void register(Sprite sprite) {
		if (sprite instanceof Snake && snake == null) {
			snake = sprite;
		} else {
			apples.add(sprite);
		}
	}

	public static void removeApple(Sprite sprite) {
		apples.remove(sprite);
	}

	public static void animate(Graphics2D g) {
		apples.stream()
				.filter(a -> a.isCollide())
				.collect(Collectors.toList())
				.forEach(a -> a.handleCollision());

		apples.stream()
				.forEach(apple -> apple.draw(g));

		if (snake.isCollide()) {
			snake.draw(g);
			snake.handleCollision();
		} else {
			snake.move();
			snake.draw(g);
		}
	}

	public static boolean appleInPlay() {
		return apples.stream().filter(sprite -> sprite instanceof Apple).count() == 1;
	}

	public static Snake getSnake() {
		return (Snake) snake;
	}

	protected GameHelper gameHelper;

	public Sprite(GameHelper gameHelper) {
		register(this);
		this.gameHelper = gameHelper;
	}

	protected boolean contains(Rectangle r1, Rectangle r2) {
		return r1.contains(r2);
	}

	public abstract boolean isCollide();

	public abstract void draw(Graphics2D g);

	public abstract void move();

	public abstract Rectangle getArea();

	public abstract void handleCollision();
}
