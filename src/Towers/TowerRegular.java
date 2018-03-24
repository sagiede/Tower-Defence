package Towers;


import Roads.Cell;
import Roads.Road;

public class TowerRegular extends Tower {

	
	public TowerRegular(int ticksToAct,int x,int y,int toX,int toY, int speed, int threatArea) {
		super(ticksToAct,x, y, toX, toY, speed,threatArea);
	}
	
	//attack first near enemy you detect
	public boolean act(Cell[][] matrix){
		int x = getX();
		int y = getY();
		int indexCounter =0;
		
		Cell[] neighborsList = null;
		
		//make list of optional roads for threated area 1
		if(getThreatedArea() ==1){
			neighborsList = new Cell[9];
			for(int neighborX = x-1 ;indexCounter < 9 && neighborX <= x+1 ; neighborX++){
				for(int neighborY = y-1 ; neighborY <= y+1; neighborY++){
					if(!(neighborX == x & neighborY == y)){
						neighborsList[indexCounter] = checkNeighbor(neighborX, neighborY, matrix);
					}
					else
						neighborsList[indexCounter] = null;
					indexCounter++;
				}
			}
		}
		//make list of optional roads for threated area 2
		if(getThreatedArea() ==2){
			neighborsList = new Cell[25];
			for(int neighborX = x-2 ; neighborX <= x+2; neighborX++){
				for(int neighborY = y-2 ; neighborY <= y+2; neighborY++){
					if(!(neighborX == x & neighborY == y))
						neighborsList[indexCounter] = checkNeighbor(neighborX, neighborY, matrix);
					else
						neighborsList[indexCounter] = null;
					indexCounter++;
				}
			}
		}
		
		attackCells(neighborsList, matrix);
		return true;
	}
	
	//private assistant func to attack first cell
	
	private void attackCells(Cell[] neighborsList, Cell[][] matrix)
	{
		for (int i = 0;i < neighborsList.length; i++) {
			if(!(neighborsList[i] == null)){
				Road road = (Road) neighborsList[i];
				if(!(road.getEnemysList().isEmpty())){
					road.actTower(matrix, this);
					break;
				}
			}
		}
		
	}
	
	//make the optional list for roads(return cekk that a road or null otherwise)
	private Cell checkNeighbor(int x,int y,Cell[][] matrix){
				
		if((x < 0 | y < 0 ) || x >=matrix.length | y >=matrix.length){
			return null;
			}
		else
			if(matrix[x][y] instanceof Road)
				return matrix[x][y];
			else return null;
	}
	
}