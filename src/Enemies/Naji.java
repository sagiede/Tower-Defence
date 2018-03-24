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
public class Naji  extends Enemy implements Visitor{
		
		private boolean _countTick;
		
		
		public Naji (Cell myCell, Cell nextCell){
			super(1,myCell,nextCell);
			this._countTick=false;
			setImage(Photos._naji1);
		}
		public void actTower(Tower tower){
			tower.accept(this);
			
		}
		
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
						else{
							changePhoto();
							moveEnemy(matrix);
							setTickForMove(1);
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
						setTickForMove(1);
					}
				}
				else
					setTickForMove(getTickForMove()-1);
			}
			return true;
		}
		private void moveEnemy(Cell[][] matrix){
			((Road)getNextCell()).getEnemysList().add(this);
			((Road)getMyCell()).getEnemysList().remove(this);
			this.setMyCell(getNextCell());
			if (getNextCell() instanceof LastRoad)
				setNextCell(null);
			else setNextCell(matrix[getMyCell().getX()+getMyCell().getToX()]
					[getMyCell().getY()+getMyCell().getToY()]);
		}
		private void killEnemy(Cell[][] matrix){
			((Road)getMyCell()).getEnemysList().remove(this);
		}
		private boolean checkIfDied(){
			return getLife() <=0;
		}
		
		
		public void visit(ArrowTower thing) {
			((Road)getMyCell()).setImage("arrowAttack");
			if(!getPoisen())
				setLife(getLife()-45);
			else
				setLife(getLife()-30);
		}
		
		public void visit(GokuTower thing) {
			((Road)getMyCell()).setImage("gokuAttack");
			if(!getPoisen())
				setLife(getLife()-thing.getH()*5);
			else
				setLife(getLife()-thing.getH()*5*1.5);
		}
	
		public void visit(LavaTower thing) {
			((Road)getMyCell()).setImage("lavaAttack");
			if(getPoisen())
				setLife(getLife()-22.5);
			else
				setLife(getLife()-15);
		}
		
		public void visit(MagicTower thing) {
			((Road)getMyCell()).setImage("magicAttack");
			if(getPoisen())
				setLife(getLife()-15);
			else
				setLife(getLife()-10);
		}

		public void visit(PoisonTower thing) {
			((Road)getMyCell()).setImage("poisonAttack");
			setPoisen(true);
			this.setCounterStopPoison(20);
		}

		public void visit(SamTower thing) {
			((Road)getMyCell()).setImage("samAttack");
			this.setSlowly(true);
			this.setCounterStopSlow(12);
		}
		
		public void visit(DinoTower dinoTower) {
			((Road)getMyCell()).setImage("dinoAttack");
			if(getPoisen())
				setLife(getLife()-(11*1.5));
			else
				setLife(getLife()-11);
	}
		private void changePhoto(){
			if (getImage() == Photos._naji1)
				setImage(Photos._naji2);
			else
				setImage(Photos._naji1);
		}
}