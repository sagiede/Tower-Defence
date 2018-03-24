package Enemies;

import java.awt.Image;


import Roads.Cell;
import Roads.Road;
import SwingFiles.GameTimer;
import SwingFiles.Menu;
import Towers.ArrowTower;
import Towers.GokuTower;
import Towers.LavaTower;
import Towers.MagicTower;
import Towers.PoisonTower;
import Towers.SamTower;
import Towers.Tower;

public class Enemy {

	private Image _image;
	private double _life;
	private boolean _isSlowly;
	private int _counterStopSlow;
	private int _tickForMove;
	private int _counterStopPoison;
	private boolean _isPoisen;
	private Cell _nextCell;
	private Cell _myCell;

	public Enemy(int tickForMove, Cell myCell, Cell nextCell) {
		this.setMyCell(myCell);
		this._nextCell = nextCell;
		this._life = 100;
		this._tickForMove = tickForMove;
		this.setSlowly(false);
		this.setCounterStopSlow(0);
		this.setCounterStopPoison(0);
		_isPoisen = false;
		this._image = null;
	}

	public Image getImage() {
		return this._image;
	}

	public void setImage(Image img) {
		this._image = img;
	}

	public double getLife() {
		return this._life;
	}

	public void setLife(double life) {
		this._life = life;
	}

	public boolean getPoisen() {
		return this._isPoisen;
	}

	public void setPoisen(boolean isPoisen) {
		this._isPoisen = isPoisen;
	}

	public int getTickForMove() {
		return this._tickForMove;
	}

	public void setTickForMove(int tickForMove) {
		this._tickForMove = tickForMove;
	}

	public void actTower(Tower tower) {
	}

	public boolean act(Cell[][] matrix) {
		return true;
	}

	// if the enemy died
	public void runAway() {
		((Road) getMyCell()).getEnemysList().remove(this);
		Menu._hp--;
		GameTimer._enemyPass++;
	}

	public void visit(ArrowTower thing) {
	}

	public void visit(GokuTower thing) {
	}

	public void visit(LavaTower thing) {
	}

	public void visit(MagicTower thing) {
	}

	public void visit(PoisonTower thing) {
	}

	public void visit(SamTower thing) {
	}

	public Cell getMyCell() {
		return _myCell;
	}

	public void setMyCell(Cell myCell) {
		this._myCell = myCell;
	}

	protected Cell getNextCell() {
		return this._nextCell;
	}

	public void setNextCell(Cell nextCell) {
		this._nextCell = nextCell;
	}

	public boolean getIsSlowly() {
		return _isSlowly;
	}

	public void setSlowly(boolean isSlowly) {
		this._isSlowly = isSlowly;
	}

	public int getCounterStopSlow() {
		return _counterStopSlow;
	}

	public void setCounterStopSlow(int counterStopSlow) {
		this._counterStopSlow = counterStopSlow;
	}

	public int getCounterStopPoison() {
		return _counterStopPoison;
	}

	public void setCounterStopPoison(int counterStopPoison) {
		this._counterStopPoison = counterStopPoison;
	}

}
