package Towers;
import java.awt.Image;

import Interfaces.Visited;
import Interfaces.Visitor;
import Roads.Cell;
import SwingFiles.Photos;

public class SamTower extends TowerKicker implements Visited {

	public SamTower(int x,int y,int toX,int toY) {
		super(1,x, y, toX, toY, 2,2);
		setCellImage(Photos._samPhoto);
		}

	public void accept(Visitor thing) {
		thing.visit(this);
		
	}
	//call super method to attack if its the time to attack(if 1 sec passed)
	public boolean act(Cell[][] matrix){
		if(getTicksToAct() == 0){
			setTicksToAct(1);
			super.act(matrix);
		}
		else{
			setTicksToAct(getTicksToAct()-1);
		}
		return true;
	}
	
}