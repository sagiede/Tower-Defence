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

public class Knight  extends Enemy implements Visitor{
	
		private boolean _countTick;
		
		public Knight (Cell myCell,Cell nextCell){
			super(3,myCell,nextCell);
			this._countTick = false;
			setImage(Photos._knight1);
		}
		
		public void actTower(Tower tower){
			tower.accept(this);
		}
		// act function
		public boolean act(Cell[][] matrix){
			if (checkIfDied()){
				killEnemy(matrix);
				GameTimer._enemyDied++;
				return false;
			}
			if(getPoisen()){
				if(getCounterStopPoison() ==0)
					setPoisen(false);
				else
					setCounterStopPoison(getCounterStopPoison() - 1);
			}
			
			if(super.getIsSlowly()){
				if(_countTick){
					_countTick = false;
					if(getTickForMove() == 0){
						if(getMyCell() instanceof LastRoad){
							super.runAway();
							return false;
						}
						moveEnemy(matrix);
						changePhoto();
						setTickForMove(3);
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
					setSlowly(false);
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
						setTickForMove(3);
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
			else setNextCell( matrix[getMyCell().getX()+getMyCell().getToX()][getMyCell().getY()+getMyCell().getToY()]);
		}
		private void killEnemy(Cell[][] matrix){
			((Road)getMyCell()).getEnemysList().remove(this);
		}
		private boolean checkIfDied(){
			return getLife() <=0;
		}
		
		public void visit(ArrowTower thing) {
			((Road)getMyCell()).setImage("arrowAttack");
		}
		
		public void visit(GokuTower thing) {
			((Road)getMyCell()).setImage("gokuAttack");
			if(!getPoisen())
				setLife(getLife()-thing.getH()*7);
			else
				setLife(getLife()-thing.getH()*7*2);
		}
	
		public void visit(LavaTower thing) {
			((Road)getMyCell()).setImage("lavaAttack");
			if(getPoisen())
				setLife(getLife()-20);
			else
				setLife(getLife()-10);
		}
		
		public void visit(MagicTower thing) {
			((Road)getMyCell()).setImage("magicAttack");
			if(getPoisen())
				setLife(getLife()-50);
			else
				setLife(getLife()-25);
		}

		public void visit(PoisonTower thing) {
			((Road)getMyCell()).setImage("poisonAttack");
			setPoisen(true);
			this.setCounterStopPoison(20);
		}

		public void visit(SamTower thing) {
			((Road)getMyCell()).setImage("samAttack");
			this.setSlowly(true);
			this.setCounterStopSlow(24);
		}
		public void visit(DinoTower dinoTower) {
			((Road)getMyCell()).setImage("dinoAttack");
			if(getPoisen())
				setLife(getLife()-16);
			else
				setLife(getLife()-8);
			
		}
		//change between 2 photo
		private void changePhoto(){
			if (getImage() == Photos._knight1)
				setImage(Photos._knight2);
			else
				setImage(Photos._knight1);
		}
	}