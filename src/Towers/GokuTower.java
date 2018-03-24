package Towers;


import Interfaces.Visited;
import Interfaces.Visitor;
import Roads.Cell;
import SwingFiles.Photos;

public class GokuTower extends TowerRegular implements Visited {

	private int H=1;
	private int countToUpgrade;

	
	public GokuTower(int x,int y,int toX,int toY) {
		super(3,x, y, toX, toY, 1,2);
		this.countToUpgrade = 10;
		setCellImage(Photos._gokuPhoto);
	}
	

	public int getH()
	{
		return this.H;
		
	}
	//call super method to attack if its the time to attack (if second past) 
	public boolean act(Cell[][] matrix){
		if(getTicksToAct() == 0){
			setTicksToAct(3);
			this.countToUpgrade--;
			super.act(matrix);
		}
		else{
			setTicksToAct(getTicksToAct()-1);
		}
		if(countToUpgrade ==0){		//its time to upgrade!
			countToUpgrade = 10;
			H = 2*H;
		}
			
		return true;
	}
	
	public void accept(Visitor thing) {
		thing.visit(this);
	}
	
}