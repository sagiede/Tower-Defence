package Towers;

import java.awt.Image;

import Interfaces.Visited;
import Interfaces.Visitor;
import Roads.Cell;
import Roads.Grass;
import SwingFiles.Photos;

public class DinoTower extends TowerRegular implements Visited {
	
	private Image _image;
	private int _initialX;				//save initial x,y for radius	
	private int _initialY;

	
	public DinoTower(int x,int y,int toX,int toY) {
		super(3,x, y, toX, toY, 1,2);
		this._initialX = x;
		this._initialY = y;
		this._image = Photos._dino1;
	}

	
	public Image getCellImage()
	{
		return this._image;
	}
	
	public boolean act(Cell[][] matrix){
		if(super.getTicksToAct() == 0){
			setTicksToAct(3);;
			dinoAct(matrix);
		}
		else{
			setTicksToAct(getTicksToAct()-1);
		}
		return true;
	}
	
	//pirvate method to change location and attack cells
	private void dinoAct(Cell[][] matrix){
		
		int x = _initialX;
		int y = _initialY;
		Cell[] nextOptionalLocation = new Cell[9];
		
		int indexCounter =0;
		for(int cellX = x-1 ;indexCounter < 9 && cellX <= x+1 ; cellX++){
			for(int cellY = y-1 ; cellY <= y+1; cellY++){
				if(!(cellX == x & cellY == y))
					nextOptionalLocation[indexCounter] = checkCell(cellX, cellY, matrix);
				else
					nextOptionalLocation[indexCounter] = null;
				indexCounter++;
			}
		}
		changeLocation(nextOptionalLocation, matrix);
		super.act(matrix);
	}
	
	//change location of dino
	private void changeLocation(Cell[] nextOptionalLocation, Cell[][] matrix)
	{
		changePhoto();
		int randomLoc = (int) ((Math.random()*100));
		for (int i = 0;i < nextOptionalLocation.length; i++) {
			if(nextOptionalLocation[(randomLoc + i)%9] != null)	//search if possibl
			{
				Cell nextCell = nextOptionalLocation[(i+randomLoc)%9];
				setX(nextCell.getX());
				setY(nextCell.getY());
				nextCell.setX(getX());
				nextCell.setY(getY());
				matrix[nextCell.getX()][nextCell.getY()] = this;
				matrix[getX()][getY()] = nextCell;
				return;
				}
		}	
	}
	
	//make the optional list of Grasses (return cell that Grass or null otherwise)
	private Cell checkCell(int x,int y,Cell[][] matrix){
				
		if((x < 0 | y < 0 ) || x >=matrix.length | y >=matrix.length){
			return null;
			}
		else{
			if(matrix[x][y] instanceof Grass){
				return matrix[x][y];
			}
			else return null;
		}
	}
	private void changePhoto(){
		if (_image == Photos._dino1)
			_image = Photos._dino2;
		else
			_image = Photos._dino1;
	}



	public void accept(Visitor thing) {
		thing.visit(this);
		
	}

}
