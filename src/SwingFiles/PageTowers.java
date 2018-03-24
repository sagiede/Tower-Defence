package SwingFiles;



import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import Towers.ArrowTower;
import Towers.DinoTower;
import Towers.GokuTower;
import Towers.LavaTower;
import Towers.MagicTower;
import Towers.PoisonTower;
import Towers.SamTower;
import Towers.Tower;

public class PageTowers extends JFrame implements ActionListener {

	private Image _image;
	private int _arrowNumber;
	private int _dinoNumner;
	private int _gokuNumber;
	private int _lavaNumber;
	private int _magicNumber;
	private int _poisonNmuber;
	private int _samNumber;
	private JButton _lava;
	private JButton _arrow;
	private JButton _poison;
	private JButton _magic;
	private JButton _sam;
	private JButton _goku;
	private JButton _dino;
	private ImageIcon _iconLava;
	private ImageIcon _iconArrow;
	private ImageIcon _iconPoison;
	private ImageIcon _iconMagic;
	private ImageIcon _iconSam;
	private ImageIcon _iconGoku;
	private ImageIcon _iconDino;
	private BoardGui _board;
	private int _counter;
	private int _tower;
	public static int _xClickLocation;
	public static int _yClickLocation;

	public PageTowers(BoardGui board, int x, int y) throws IOException {
		this.setTitle("Add Tower");
		this.setLayout(new GridLayout(2, 3));
		this._arrowNumber = 0;
		this._dinoNumner = 0;
		this._gokuNumber = 0;
		this._lavaNumber = 0;
		this._magicNumber = 0;
		this._poisonNmuber = 0;
		this._samNumber = 0;
		_tower = -1;
		this._board = board;
		this._xClickLocation = x;
		this._yClickLocation = y;

		this._lava = new JButton(_iconLava);
		_lava.setSize(5, 5);
		this._arrow = new JButton(_iconArrow);
		_arrow.setSize(5, 5);
		this._poison = new JButton(_iconPoison);
		_poison.setSize(5, 5);
		this._magic = new JButton(_iconMagic);
		_magic.setSize(5, 5);
		this._sam = new JButton(_iconSam);
		_sam.setSize(5, 5);
		this._goku = new JButton(_iconGoku);
		_goku.setSize(5, 5);
		this._dino = new JButton(_iconDino);
		_dino.setSize(5, 5);

		_iconLava = new ImageIcon(
				new ImageIcon("lavaPhoto.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		_lava.setIcon(_iconLava);
		_iconArrow = new ImageIcon(
				new ImageIcon("arrowPhoto.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		_arrow.setIcon(_iconArrow);
		_iconPoison = new ImageIcon(
				new ImageIcon("poisonPhoto.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		_poison.setIcon(_iconPoison);
		_iconMagic = new ImageIcon(
				new ImageIcon("magicPhoto.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		_magic.setIcon(_iconMagic);
		_iconSam = new ImageIcon(
				new ImageIcon("samPhoto.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		_sam.setIcon(_iconSam);
		_iconGoku = new ImageIcon(
				new ImageIcon("gokuPhoto.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		_goku.setIcon(_iconGoku);
		_iconDino = new ImageIcon(
				new ImageIcon("dino-1.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		_dino.setIcon(_iconDino);

		_lava.setText("Lava - " + (3 - _lavaNumber));
		_arrow.setText("Arrow - " + (3 - _arrowNumber));
		_poison.setText("Poison - " + (3 - _poisonNmuber));
		_magic.setText("Magic - " + (3 - _magicNumber));
		_sam.setText("Sam - " + (1 - _samNumber));
		_goku.setText("Goku - " + (1 - _gokuNumber));
		_dino.setText("Dino - " + (1 - _dinoNumner));

		setTowersLeftNum();
		this.add(_lava);
		this.add(_arrow);
		this.add(_poison);
		this.add(_magic);
		this.add(_sam);
		this.add(_goku);
		this.add(_dino);

		_lava.addActionListener(this);
		_arrow.addActionListener(this);
		_poison.addActionListener(this);
		_magic.addActionListener(this);
		_sam.addActionListener(this);
		_goku.addActionListener(this);
		_dino.addActionListener(this);

		_lava.setFocusable(true);
		_arrow.setFocusable(true);
		_poison.setFocusable(true);
		_magic.setFocusable(true);
		_sam.setFocusable(true);
		_goku.setFocusable(true);
		_dino.setFocusable(true);

		_lava.setVisible(true);
		_arrow.setVisible(true);
		_poison.setVisible(true);
		_magic.setVisible(true);
		_sam.setVisible(true);
		_goku.setVisible(true);
		_dino.setVisible(true);

		this.setFocusable(true);

		this.setVisible(false);
		this.setSize(30, 30);

		pack();
	}

	public void setTowersLeftNum() {
		_lava.setText("Lava - " + (3 - _lavaNumber));
		_arrow.setText("Arrow - " + (3 - _arrowNumber));
		_poison.setText("Poison - " + (3 - _poisonNmuber));
		_magic.setText("Magic - " + (3 - _magicNumber));
		_sam.setText("Sam - " + (1 - _samNumber));
		_goku.setText("Goku - " + (1 - _gokuNumber));
		_dino.setText("Dino - " + (1 - _dinoNumner));
	}

	public void resetTowersNum() {
		this._arrowNumber = 0;
		this._dinoNumner = 0;
		this._gokuNumber = 0;
		this._lavaNumber = 0;
		this._magicNumber = 0;
		this._poisonNmuber = 0;
		this._samNumber = 0;
	}

	public void setXY(int x, int y) {
		this._xClickLocation = x;
		this._yClickLocation = y;
		this.setLocation(x * 25, y * 25);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == _lava) {
			if (getLavaNum() < 3) {
				Tower t = new LavaTower(_xClickLocation, _yClickLocation, 0, 0);
				_board._gameTimer.addTower(t);
				_board._matrix[_xClickLocation][_yClickLocation] = t;
				addLavaNum();
			}
		} else if (e.getSource() == _arrow) {
			if (getArrowNum() < 3) {
				Tower t = new ArrowTower(_xClickLocation, _yClickLocation, 0, 0);
				_board._gameTimer.addTower(t);
				_board._matrix[_xClickLocation][_yClickLocation] = t;
				addArrowNum();
			}
		} else if (e.getSource() == _poison) {
			if (getPoisonNum() < 3) {
				Tower t = new PoisonTower(_xClickLocation, _yClickLocation, 0, 0);
				_board._gameTimer.addTower(t);
				_board._matrix[_xClickLocation][_yClickLocation] = t;
				addPoisonNum();
			}
		} else if (e.getSource() == _magic) {
			if (getMagicNum() < 3) {
				Tower t = new MagicTower(_xClickLocation, _yClickLocation, 0, 0);
				_board._gameTimer.addTower(t);
				_board._matrix[_xClickLocation][_yClickLocation] = t;
				addMagicNum();
			}
		} else if (e.getSource() == _sam) {
			if (getSamNum() < 1) {
				Tower t = new SamTower(_xClickLocation, _yClickLocation, 0, 0);
				_board._gameTimer.addTower(t);
				_board._matrix[_xClickLocation][_yClickLocation] = t;
				addSamNum();
			}
		} else if (e.getSource() == _goku) {
			if (getGokuNum() < 1) {
				Tower t = new GokuTower(_xClickLocation, _yClickLocation, 0, 0);
				_board._gameTimer.addTower(t);
				_board._matrix[_xClickLocation][_yClickLocation] = t;
				addGokuNum();
			}
		} else if (e.getSource() == _dino) {
			if (getDinoNum() < 1) {
				Tower t = new DinoTower(_xClickLocation, _yClickLocation, 0, 0);
				_board._gameTimer.addTower(t);
				_board._matrix[_xClickLocation][_yClickLocation] = t;
				addDinoNum();
			}
		}
		this.setVisible(false);
		_board.repaint();

	}

	public int getArrowNum() {
		return this._arrowNumber;
	}

	public int getDinoNum() {
		return this._dinoNumner;
	}

	public int getMagicNum() {
		return this._magicNumber;
	}

	public int getSamNum() {
		return this._samNumber;
	}

	public int getGokuNum() {
		return this._gokuNumber;
	}

	public int getPoisonNum() {
		return this._poisonNmuber;
	}

	public int getLavaNum() {
		return this._lavaNumber;
	}

	public void addArrowNum() {
		this._arrowNumber++;
	}

	public void addDinoNum() {
		this._dinoNumner++;
	}

	public void addMagicNum() {
		this._magicNumber++;
	}

	public void addLavaNum() {
		this._lavaNumber++;
	}

	public void addPoisonNum() {
		this._poisonNmuber++;
	}

	public void addSamNum() {
		this._samNumber++;
	}

	public void addGokuNum() {
		this._gokuNumber++;
	}

}