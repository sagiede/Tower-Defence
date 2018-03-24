package Towers;


import Interfaces.Visited;
import Interfaces.Visitor;
import Roads.Cell;
import SwingFiles.Photos;

public class MagicTower extends TowerRegular implements Visited{

	
	public MagicTower(int x,int y,int toX,int toY) {
		super(3,x, y, toX, toY, 1,1);
		setCellImage(Photos._magicPhoto);	
		}
	
	//call super method to attack if its the time to attack(if 1 sec passed)
	public boolean act(Cell[][] matrix){
		if(getTicksToAct() == 0){
			setTicksToAct(3);
			super.act(matrix);
		}
		else{
			setTicksToAct(getTicksToAct()-1);
		}
		return true;
	}
	
	public void accept(Visitor thing) {
		thing.visit(this);
		
	}
	
}