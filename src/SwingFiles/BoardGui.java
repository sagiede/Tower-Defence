package SwingFiles;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.*;

import Enemies.Enemy;
import Roads.Cell;
import Roads.Road;
import Towers.Tower;

public class BoardGui extends JPanel implements MouseListener {

	protected GameTimer _gameTimer; // timer
	private Image _image; // Background image
	protected Cell[][] _matrix; // the matrix
	private LevelLoader _level; // level loader
	private JFrame _pageT; // page tower

	public static int _levelNumber = 0; // the current level number
	public static int _wave; // the current wave
	public boolean _paintLimit; // paint limit of tower
	private int _x = 0; // x coordinate click
	private int _y = 0;// y coordinate click
	private int _limit = 0; // the limit of the tower
	private Image _redbox; // pic of limit
	

	public BoardGui() throws IOException {
		this.addMouseListener(this);
		this._paintLimit = false;
		this._wave = 0;

		this._level = new LevelLoader();
		_level.load();

		this._matrix = _level.get(_levelNumber);
		
		this._pageT = new PageTowers(this, _x, _y);
		try {
			this._image = ImageIO.read(new File("grass.png"));
			_image = _image.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
			this._redbox = ImageIO.read(new File("redbox.png"));
			_redbox = _redbox.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		LinkedList<Enemy> enemies = new LinkedList<Enemy>();
		LinkedList<Tower> towers = new LinkedList<Tower>();
		LinkedList<Road> roads = new LinkedList<Road>();

		this._gameTimer = new GameTimer(250, _matrix, this, enemies, towers, roads);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		buildWorld(g);
	}

	// the paint func
	public void buildWorld(Graphics g) {

		g.drawImage(_image, 0, 0, null);
		for (Road road : _gameTimer._roads) {
			g.drawImage(road.getCellImage(), (int) Math.floor(road.getX() * 25), (int) Math.floor(road.getY() * 25), this);
		}
		for (Tower tower : _gameTimer._towers) {
			g.drawImage(tower.getCellImage(), (int) Math.floor(tower.getX() * 25), (int) Math.floor(tower.getY() * 25),
					this);
		}
		for (Enemy enemy : _gameTimer._enemies) {
			g.drawImage(enemy.getImage(), (int) Math.floor(enemy.getMyCell().getX() * 25),
					(int) Math.floor(enemy.getMyCell().getY() * 25), this);
			if (enemy.getIsSlowly())
				g.drawImage(Photos._slowMark, (int) Math.floor(enemy.getMyCell().getX() * 25),
						(int) Math.floor((enemy.getMyCell().getY() * 25) + 10), this);
			if (enemy.getPoisen())
				g.drawImage(Photos._poisonMark, (int) Math.floor(enemy.getMyCell().getX() * 25),
						(int) Math.floor((enemy.getMyCell().getY() * 25)), this);
		}
		if (_paintLimit == true) {
			for (int i = _x - _limit; i <= _x + _limit; i++)
				for (int j = _y - _limit; j <= _y + _limit; j++) {
					if (j != _y | i != _x)
						g.drawImage(_redbox, i * 25, j * 25, this);
				}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Menu._error.setText("");
		int xCordinate = (int) Math.floor(e.getX() / 25);
		int yCordinate = (int) Math.floor(e.getY() / 25);
		Tower t = isTower(xCordinate, yCordinate);
		// add enemies
		if (_gameTimer._enemies.size() == 0 && t != null & _paintLimit == false) {

			this._x = xCordinate;
			this._y = yCordinate;
			this._paintLimit = true;
			this._limit = t.getThreatedArea();
			repaint();
		}
		// close enemies
		else if (_gameTimer._enemies.size() == 0 && t != null & _paintLimit == true) {
			_paintLimit = false;
			repaint();
		}
		// show limit
		else if (_gameTimer._enemies.size() > 0 && t != null & _paintLimit == false) {
			this._x = xCordinate;
			this._y = yCordinate;
			this._paintLimit = true;
			this._limit = t.getThreatedArea();
			repaint();
		}
		// close limit
		else if (_gameTimer._enemies.size() > 0 && t != null & _paintLimit == true) {
			_paintLimit = false;
			repaint();
		}
		// add tower
		else if (_gameTimer._enemies.size() == 0 && isRoad(xCordinate, yCordinate)) {

			((PageTowers) this._pageT).setTowersLeftNum();
			this._pageT.setLocation(xCordinate * 25, yCordinate * 25);
			PageTowers._xClickLocation = xCordinate;
			PageTowers._yClickLocation = yCordinate;
			this._pageT.setVisible(true);

		}

	}
	// is the point is tower
	private Tower isTower(int x, int y) {
		for (Tower tower : _gameTimer._towers) {
			if (tower.getX() == x & tower.getY() == y)
				return tower;
		}
		return null;
	}
	// is the point is road
	private boolean isRoad(int x, int y) {
		for (Road road : _gameTimer._roads) {
			if (road.getX() == x & road.getY() == y)
				return false;
		}
		return true;
	}
	// move to next level
	public void getNextLevel() {
		if (_levelNumber + 1 < _level.getLevelsCount()) {
			((PageTowers) this._pageT).resetTowersNum();
			((PageTowers) this._pageT).setTowersLeftNum();
			
			_matrix = _level.get(_levelNumber + 1);
			
			_levelNumber++;
			LinkedList<Enemy> enemies = new LinkedList<Enemy>();
			LinkedList<Tower> towers = new LinkedList<Tower>();
			LinkedList<Road> roads = new LinkedList<Road>();
			Menu._hp = 20;
			this._wave = 0;
			_paintLimit = false;
			this._gameTimer._t.stop();
			GameTimer._numOfEnemy=0;
			GameTimer._enemyDied = 0;
			GameTimer._enemyPass = 0;
		
			this._gameTimer = new GameTimer(250, _matrix, this, enemies, towers, roads);
			repaint();
		} else
			Menu._error.setText("no more levels");

	}
	// go to prev level
	public void getPrevLevel() {
		if (_levelNumber > 0) {
			((PageTowers) this._pageT).resetTowersNum();
			((PageTowers) this._pageT).setTowersLeftNum();
			
			_matrix = _level.get(_levelNumber - 1);
			_levelNumber--;
			LinkedList<Enemy> enemies = new LinkedList<Enemy>();
			LinkedList<Tower> towers = new LinkedList<Tower>();
			LinkedList<Road> roads = new LinkedList<Road>();
			Menu._hp = 20;
			this._wave = 0;
			_paintLimit = false;
			this._gameTimer._t.stop();
			GameTimer._numOfEnemy=0;
			GameTimer._enemyDied = 0;
			GameTimer._enemyPass = 0;
			
			this._gameTimer = new GameTimer(250, _matrix, this, enemies, towers, roads);
			repaint();
		} else
			Menu._error.setText("no more levels");

	}
	// update the level number
	public void UpdateLevel() {
		_matrix = _level.get(_levelNumber);

		LinkedList<Enemy> enemies = new LinkedList<Enemy>();
		LinkedList<Tower> towers = new LinkedList<Tower>();
		LinkedList<Road> roads = new LinkedList<Road>();
		Menu._hp = 20;
		this._wave = 0;
		this._gameTimer._t.stop();
		this._gameTimer = new GameTimer(250, _matrix, this, enemies, towers, roads);
		repaint();

	}

}