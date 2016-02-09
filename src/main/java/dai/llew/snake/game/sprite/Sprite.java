package dai.llew.snake.game.sprite;

import dai.llew.snake.game.GameHelper;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by daiLlew on 07/02/2016.
 */
public abstract class Sprite {

	protected static List<Sprite> ACTIVE_SPRITES = new ArrayList<>();

	public static void register(Sprite sprite) {
		ACTIVE_SPRITES.add(sprite);
	}

	public static void remove(Sprite sprite) {
		ACTIVE_SPRITES.remove(sprite);
	}

	public static List<Sprite> sprites() {
		return ACTIVE_SPRITES;
	}

	public static void animate(Graphics2D g) {
		sprites().forEach(sprite -> {
			sprite.draw(g);
			sprite.move();
		});
	}

	public static boolean checkCollisions() {
		return sprites()
				.stream()
				.filter(sprite -> sprite.isCollide())
				.findFirst()
				.isPresent();
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
}
