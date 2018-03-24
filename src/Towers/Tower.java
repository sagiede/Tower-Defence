package Towers;


import Interfaces.Visited;
import Interfaces.Visitor;
import Roads.Cell;

public class Tower extends Cell implements Visited {

	private int threatArea;
	private int ticksToAct;

	
	public Tower(int ticksToAct,int x, int y, int toX, int toY, int speed, int threatArea) {
		super(x, y, toX, toY);
		this.setTicksToAct(ticksToAct);
		this.threatArea = threatArea;
	}
	
	//act of tower - will be override by specifics tower
	public boolean act(Cell[][] matrix){
		return true;
	}

	public int getThreatedArea(){
		return this.threatArea;
	}

	public void accept(Visitor thing){	
	}

	public int getTicksToAct() {
		return ticksToAct;
	}

	public void setTicksToAct(int ticksToAct) {
		this.ticksToAct = ticksToAct;
	}

	
}