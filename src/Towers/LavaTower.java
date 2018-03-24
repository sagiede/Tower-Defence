package Towers;


import Interfaces.Visited;
import Interfaces.Visitor;
import Roads.Cell;
import SwingFiles.Photos;

public class LavaTower extends TowerKicker implements Visited {

	
	public LavaTower(int x,int y,int toX,int toY) {
		super(3,x, y, toX, toY, 1,1);
		setCellImage(Photos._lavaPhoto);
	}


	public void accept(Visitor thing) {
		thing.visit(this);
		
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
	
}