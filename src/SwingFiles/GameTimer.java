package SwingFiles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import Enemies.Enemy;
import Enemies.Goli;
import Enemies.Knight;
import Enemies.Mike;
import Enemies.Naji;
import Roads.Cell;
import Roads.FirstRoad;
import Roads.LastRoad;
import Roads.Road;
import Towers.Tower;

public class GameTimer implements ActionListener {

	protected javax.swing.Timer _t;
	private int _delay;
	protected LinkedList<Enemy> _enemies;
	protected LinkedList<Tower> _towers;
	protected LinkedList<Road> _roads;
	public int[] _nextWave = new int[0];
	public int _nextEnemyIndex;
	private BoardGui _board;
	private Cell[][] _matrix;
	protected FirstRoad _firstRoad;
	protected LastRoad _lastroad;
	protected int _counter;

	public static int _numOfEnemy = 0;
	public static int _enemyDied = 0;
	public static int _enemyPass = 0;

	public static int _time;

	public GameTimer(int delay, Cell[][] matrix, BoardGui board, LinkedList<Enemy> enemies, LinkedList<Tower> towers,
			LinkedList<Road> roads) {

		this._time = 0;
		this._roads = roads;
		this._enemies = enemies;
		this._towers = towers;
		this._board = board;
		this._t = new javax.swing.Timer(delay, this);
		this._firstRoad = null;
		this._lastroad = null;
		this._matrix = matrix;
		this._counter = 0;
		findRoad(roads, matrix);
		this._nextEnemyIndex = 0;

		_t.start();
	}
	// find the road on the matrix
	public void findRoad(LinkedList<Road> roads, Cell[][] _matrix) {
		for (int i = 0; i < 32; i++)
			for (int j = 0; j < 32; j++)
				if (_matrix[i][j] instanceof Road) {
					if (i == 0) {

						FirstRoad f = new FirstRoad(i, j, _matrix[i][j].getToX(), _matrix[0][j].getToY());
						this._firstRoad = f;
						_matrix[i][j] = f;
						roads.add(_firstRoad);
					}
					if (i == 31) {

						LastRoad l = new LastRoad(i, j, _matrix[i][j].getToX(), _matrix[0][j].getToY());
						this._lastroad = l;
						_matrix[i][j] = l;
						roads.add(_lastroad);
					} else {
						roads.add((Road) _matrix[i][j]);
					}
				}
	}

	public int getDelay() {
		return _delay;
	}

	public void setDelay(int delay) {
		this._delay = delay;
		_t.setDelay(delay);
	}

	public LinkedList<Tower> getTowers() {
		return _towers;
	}

	public void addTower(Tower tower) {
		_towers.add(tower);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_counter++;
		calTime();
		Menu._hpText.setText("hp: " + Menu._hp);
		Menu._wave.setText("wave: " + _board._wave);
		Menu._level.setText("level: " + _board._levelNumber);
		Menu._time.setText("time: " + _time);
		isFinish();

		if (e.getSource() == _t) {
			if (_nextEnemyIndex < _nextWave.length & _counter % 4 == 0) {
				int enemyKind = _nextWave[_nextEnemyIndex];
				addEnemy(enemyKind);
				_nextEnemyIndex++;
				_numOfEnemy++;
			}
			if (_enemies.size() > 0) {
				for (Road road : _roads) {
					road.setDefaultImage();
				}

				for (int i = 0; i < _enemies.size(); i++) {
					Enemy enemy = _enemies.get(i);
					if (!(enemy.act(_matrix))) {
						_numOfEnemy--;
						_enemies.remove(enemy);
					}

				}
				for (Tower tower : _towers) {
					tower.act(_matrix);
				}
				_board.repaint();
			}
		}
	}
	// Calculate the time for real time
	private void calTime() {
		if (_counter % 4 == 0)
			_time++;

	}
	// add enemy to the road
	private void addEnemy(int enemyKind) {

		Road nextRoad = (Road) _matrix[_firstRoad.getX() + _firstRoad.getToX()][_firstRoad.getY()
				+ _firstRoad.getToY()];
		if (enemyKind == 1) {
			Enemy enemy = new Goli(_firstRoad, nextRoad);
			_firstRoad.enemysList.add(enemy);
			_enemies.add(enemy);
		} else if (enemyKind == 2) {
			Enemy enemy = new Knight(_firstRoad, nextRoad);
			_firstRoad.enemysList.add(enemy);
			_enemies.add(enemy);
		} else if (enemyKind == 3) {
			Enemy enemy = new Mike(_firstRoad, nextRoad);
			_firstRoad.enemysList.add(enemy);
			_enemies.add(enemy);
		} else if (enemyKind == 0) {
			Enemy enemy = new Naji(_firstRoad, nextRoad);
			_firstRoad.enemysList.add(enemy);
			_enemies.add(enemy);
		}
	}
	// add wave
	public void updateNextWaveList(int waveNum) {
		if (waveNum <= 5) {
			_board._wave += 1;
			_nextWave = new int[(int) (4*(Math.pow(2, waveNum-1)))];
			int randomNumForEnemyKind = ((int) Math.random() * 100);
			for (int i = 0; i < 4 * waveNum; i++) {
				_nextWave[i] = randomNumForEnemyKind + i % 4;
				_nextEnemyIndex = 0;
			}
		} else
			Menu._error.setText("no more waves");
	}
	//check if the game done
	public void isFinish() {
		if (Menu._hp <= 0) {
			_t.stop();
			gameOver done = new gameOver(_board, false);
			done.setVisible(true);
		}
		if (_enemies.size() == 0 & BoardGui._wave == 5 & Menu._hp > 0 & _nextWave.length == _nextEnemyIndex) {
			_t.stop();
			gameOver done = new gameOver(_board, true);
			done.setVisible(true);
		}
	}

}
