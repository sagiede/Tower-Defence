package SwingFiles;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class WelcomePage extends JFrame implements ActionListener {

	private JButton _level0;
	private JButton _level1;
	private JButton _level2;
	private JButton _level3;
	private ImageIcon _level0P;
	private ImageIcon _level1P;
	private ImageIcon _level2P;
	private ImageIcon _level3P;

	public WelcomePage() {
		super("Welcome to the game");
		this.setLayout(new FlowLayout());

		Image img = new ImageIcon("level0.jpg").getImage();
		_level0P = new ImageIcon(img.getScaledInstance((int) (img.getWidth(null) / 2),
				(int) (img.getHeight(null) / 2),
				Image.SCALE_DEFAULT));

		img = new ImageIcon("level1.jpg").getImage();
		_level1P = new ImageIcon(img.getScaledInstance((int) (img.getWidth(null) / 2),
				(int) (img.getHeight(null) / 2),
				Image.SCALE_DEFAULT));

		img = new ImageIcon("level2.jpg").getImage();
		_level2P = new ImageIcon(img.getScaledInstance((int) (img.getWidth(null) / 2),
				(int) (img.getHeight(null) / 2),
				Image.SCALE_DEFAULT));

		img = new ImageIcon("level3.jpg").getImage();
		_level3P = new ImageIcon(img.getScaledInstance((int) (img.getWidth(null) / 2),
				(int) (img.getHeight(null) / 2),
				Image.SCALE_DEFAULT));

		this._level0 = new JButton();
		_level0.setSize(_level0P.getIconWidth(), _level0P.getIconWidth());
		this._level1 = new JButton();
		_level1.setSize(_level1P.getIconWidth(), _level1P.getIconWidth());
		this._level2 = new JButton();
		_level2.setSize(_level2P.getIconWidth(), _level2P.getIconWidth());
		this._level3 = new JButton();
		_level3.setSize(_level3P.getIconWidth(), _level3P.getIconWidth());
		_level0.setIcon(_level0P);
		_level1.setIcon(_level1P);
		_level2.setIcon(_level2P);
		_level3.setIcon(_level3P);
		this.add(_level0);
		this.add(_level1);
		this.add(_level2);
		this.add(_level3);

		_level0.addActionListener(this);
		_level1.addActionListener(this);
		_level2.addActionListener(this);
		_level3.addActionListener(this);

		_level0.setFocusable(true);
		_level1.setFocusable(true);
		_level2.setFocusable(true);
		_level3.setFocusable(true);

		_level0.setVisible(true);
		_level1.setVisible(true);
		_level2.setVisible(true);
		_level3.setVisible(true);

		pack();
		this.setFocusable(true);

		this.setVisible(true);
		this.setSize(1000, 1000);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _level0) {
			BoardGui._levelNumber = 0;
		}
		if (e.getSource() == _level1) {
			BoardGui._levelNumber = 1;
		}
		if (e.getSource() == _level2) {
			BoardGui._levelNumber = 2;
		}
		if (e.getSource() == _level3) {
			BoardGui._levelNumber = 3;
		}
		try {
			Game g = new Game();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.dispose();

	}

	public static void main(String[] args) throws IOException {
		WelcomePage w = new WelcomePage();
	}

}
