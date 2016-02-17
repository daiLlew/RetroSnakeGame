package dai.llew.snake.game;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by daiLlew on 17/02/2016.
 */
public enum Level {
	ONE(300, 1),

	TWO(270, 2),

	THREE(240, 3),

	FOUR(210, 4),

	FIVE(180, 5),

	SIX(150, 6),

	SEVEN(120, 7),

	EIGHT(90, 8),

	NINE(60, 9),

	TEN(30, 10);

	private final int speed;
	private final int multiplier;

	private static final List<Level> scores = Arrays.asList(Level.values());
	private static final Iterator<Level> pointer = scores.iterator();

	public static Level next() {
		if (pointer.hasNext()) {
			return pointer.next();
		} else {
			return TEN;
		}
	}

	Level(int speed, int multiplier) {
		this.speed = speed;
		this.multiplier = multiplier;
	}

	public int speed() {
		return speed;
	}

	public int scoreMultiplier() {
		return multiplier;
	}
}
