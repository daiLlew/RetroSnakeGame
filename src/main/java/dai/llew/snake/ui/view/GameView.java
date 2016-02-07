package dai.llew.snake.ui.view;

import dai.llew.snake.game.GameHelper;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by daiLlew on 07/02/2016.
 */
public abstract class GameView extends JPanel {

	protected GameHelper gameHelper;

	public GameView(GameHelper gameHelper) {
		super();
		this.gameHelper = gameHelper;

		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				handleKeyPressed(e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateDisplay((Graphics2D)g);
	}

	public abstract void updateDisplay(Graphics2D g);

	public abstract void handleKeyPressed(KeyEvent e);
}
