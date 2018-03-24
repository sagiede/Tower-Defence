package Enemies;

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

public class Goli extends Enemy implements Visitor {

	private boolean _countTick;
	
	
	public Goli (Cell myCell,Cell nextCell){
		super(3,myCell,nextCell);
		_countTick = false;
		setImage(Photos._goli1);
	} 
	public void actTower(Tower tower){
		tower.accept(this);
	}
	// act action
	public boolean act(Cell[][] matrix){
		if (checkIfDied()){
			killEnemy(matrix);
			GameTimer._enemyDied++;
			return false;
		}
		if(super.getIsSlowly()){
			if(_countTick){
				_countTick = false;
				if(getTickForMove() == 0){
					if(super.getMyCell() instanceof LastRoad){
						super.runAway();
						return false;
					}
					else{
						moveEnemy(matrix);
						changePhoto();
						setTickForMove(3);
					}
				}
				else
					setTickForMove(getTickForMove()-1);
			}
			else
			{
				_countTick = true;
			}
			setCounterStopSlow(getCounterStopSlow() - 1);
			if(getCounterStopSlow() == 0){
				setSlowly(false);;
			}
		}
		else{
			if(getTickForMove() == 0){
				if(getMyCell() instanceof LastRoad){
					super.runAway();
					return false;
				}
				else{
					changePhoto();
					moveEnemy(matrix);
					setTickForMove(3);;
				}
			}
			else
				setTickForMove(getTickForMove()-1);
		}
		return true;
	}
	// move to next cell
	private void moveEnemy(Cell[][] matrix){
		((Road)getNextCell()).getEnemysList().add(this);
		((Road)getMyCell()).getEnemysList().remove(this);
		this.setMyCell(getNextCell());
		if (getNextCell() instanceof LastRoad)
			setNextCell(null);
		else setNextCell(matrix[getMyCell().getX()+getMyCell().getToX()]
				[getMyCell().getY()+getMyCell().getToY()]);
	}
	//enemy died
	private void killEnemy(Cell[][] matrix){
		((Road)getMyCell()).getEnemysList().remove(this);
	}
	private boolean checkIfDied(){
		return getLife() <=0;
	}

	public void visit(ArrowTower thing) {
		((Road)getMyCell()).setImage("arrowAttack");
		setLife(getLife()-15);	
	}

	public void visit(GokuTower thing) {
		((Road)getMyCell()).setImage("gokuAttack");
		setLife(getLife()-thing.getH()*10);
	}

	public void visit(LavaTower thing) {
		((Road)getMyCell()).setImage("lavaAttack");
		setLife(getLife()-15);	
	}

	
	public void visit(MagicTower thing) {
		((Road)getMyCell()).setImage("magicAttack");
		setLife(getLife()-25);	
	}
	
	public void visit(PoisonTower thing) {
		((Road)getMyCell()).setImage("poisonAttack");
		setLife(getLife()-20);	
	}
	
	public void visit(SamTower thing) {
		((Road)getMyCell()).setImage("samAttack");
		this.setSlowly(true);
		this.setCounterStopSlow(12);
	}
	
	public void visit(DinoTower dinoTower) {
		((Road)super.getMyCell()).setImage("dinoAttack");
	}
	private void changePhoto(){
		if (getImage() == Photos._goli1)
			setImage(Photos._goli2);
		else
			setImage(Photos._goli1);
	}
	
}