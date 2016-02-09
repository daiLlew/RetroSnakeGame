package dai.llew.snake.ui;

import dai.llew.snake.ui.view.GameView;

import javax.swing.*;

import static dai.llew.snake.game.Constants.WINDOW_DIMENSIONS;

/**
 * Created by daiLlew on 07/02/2016.
 */
public class GUI {

	private JFrame main;
	private GameView view;

	public GUI(GameView view) {
		this.main = new JFrame("Snake!");
		this.main.setSize(WINDOW_DIMENSIONS);
		this.main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.view = view;
		this.main.add(view);
		this.main.setVisible(true);
		this.view.requestFocus();
		this.main.setResizable(false);
	}

	public void updateView(GameView newView) {
		main.remove(this.view);
		main.add(newView);
		this.view.requestFocus();
		main.repaint();
	}

	public void repaint() {
		this.main.repaint();
	}
}
