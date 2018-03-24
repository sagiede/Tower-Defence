package SwingFiles;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class Game extends JFrame {
	private BoardGui _boardGui;
	private SwingFiles.Menu _menu;
	private JSplitPane _myMainPane;
	public Photos _photos;

	public Game() throws IOException {
		super("Game");
		this._photos = new Photos();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_boardGui = new BoardGui();
		_boardGui.setSize(800, 800);
		_menu = new SwingFiles.Menu(_boardGui);
		_myMainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
		_myMainPane.setDividerLocation(100);
		_myMainPane.setOneTouchExpandable(false);
		_myMainPane.add(_menu);
		_myMainPane.add(_boardGui);
		getContentPane().add(_myMainPane);
		this.setResizable(false);
		setSize(830, 980);
		setVisible(true);
	}

}