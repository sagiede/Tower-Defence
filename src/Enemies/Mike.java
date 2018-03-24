package Enemies;

import java.awt.Image;

import Interfaces.Visitor;
import Roads.Cell;
import Roads.LastRoad;
import Roads.Road;
import SwingFiles.GameTimer;
import SwingFiles.Photos;
import Towers.ArrowTower;
import Towers.DinoTower;
import Towers.GokuTower;
import Towers.LavaTower;
import Towers.MagicTower;
import Towers.PoisonTower;
import Towers.SamTower;
import Towers.Tower;

public class Mike extends Enemy implements Visitor {

	private Image _image;
	private boolean _countTick;

	public Mike(Cell myCell, Cell nextCell) {
		super(3, myCell, nextCell);
		_countTick = false;
		this._image = Photos._mike1;
	}

	public Image getImage() {
		return this._image;
	}

	public void actTower(Tower tower) {
		tower.accept(this);
	}

	// act function
	public boolean act(Cell[][] matrix) {
		if (checkIfDied()) {
			killEnemy(matrix);
			GameTimer._enemyDied++;
			return false;
		}
		if (super.getIsSlowly()) {
			if (_countTick) {
				_countTick = false;
				if (getTickForMove() == 0) {
					if (getMyCell() instanceof LastRoad) {
						super.runAway();
						return false;
					} else {
						changePhoto();
						moveEnemy(matrix);
						setTickForMove(3);
					}
				} else
					setTickForMove(getTickForMove() - 1);
			} else {
				_countTick = true;
			}
			setCounterStopSlow(getCounterStopSlow() - 1);
			if (getCounterStopSlow() == 0) {
				setSlowly(false);
			}
		}

		else { // not slowly
			if (getTickForMove() == 0) {
				if (getMyCell() instanceof LastRoad) {
					super.runAway();
					return false;
				} else {
					changePhoto();
					moveEnemy(matrix);
					setTickForMove(3);
				}
			} else
				setTickForMove(getTickForMove() - 1);
		}
		return true;
	}

	// move to the next cell
	private void moveEnemy(Cell[][] matrix) {
		((Road) getNextCell()).getEnemysList().add(this);
		((Road) getMyCell()).getEnemysList().remove(this);
		setMyCell(getNextCell());
		if (getNextCell() instanceof LastRoad)
			setNextCell(null);
		else
			setNextCell(matrix[getNextCell().getX() + getNextCell().getToX()][getNextCell().getY()
					+ getNextCell().getToY()]);
	}

	private void killEnemy(Cell[][] matrix) {
		((Road) getMyCell()).getEnemysList().remove(this);
	}

	private boolean checkIfDied() {
		return getLife() <= 0;
	}

	public void visit(ArrowTower thing) {
		((Road) getMyCell()).setImage("arrowAttack");
		setLife(getLife() - 30);
	}

	public void visit(GokuTower thing) {
		((Road) getMyCell()).setImage("gokuAttack");
		setLife(getLife() - thing.getH() * 5);
	}

	public void visit(LavaTower thing) {
		((Road) getMyCell()).setImage("lavaAttack");
		setLife(getLife() - 15);
	}

	public void visit(MagicTower thing) {
		((Road) getMyCell()).setImage("magicAttack");
		setLife(getLife() - 10);
	}

	public void visit(PoisonTower thing) {
		((Road) getMyCell()).setImage("poisonAttack");
	}

	public void visit(SamTower thing) {
		((Road) getMyCell()).setImage("samAttack");
		this.setSlowly(true);
		this.setCounterStopSlow(12);
		setLife(getLife() - 10);
	}

	public void visit(DinoTower dinoTower) {
		((Road) getMyCell()).setImage("dinoAttack");
		setLife(getLife() - 10);
	}

	// change photos
	private void changePhoto() {
		if (_image == Photos._mike1)
			_image = Photos._mike2;
		else
			_image = Photos._mike1;
	}
}