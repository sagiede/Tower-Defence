package Roads;
import java.awt.Image;

import Towers.Tower;

public class Cell {

	private int _x,_y;
	private int[] road;
	private Image _image;

	
	public Cell(int x,int y,int toX,int toY) {
		this.road=new int[2];
		_x = x;
		_y = y;
		road[0]=toX;
		road[1]=toY;
		this._image = null;
	}
	public int getX()
	{
		return _x;	
	}
	public int getY()
	{
		return _y;	
	}
	public void setX(int x)
	{
		this._x = x;	
	}
	public void setY(int y)
	{
		this._y = y;	
	}
	public int getToX()
	{
		return road[0];	
	}
	public int getToY()
	{
		return road[1];	
	}
	public boolean act(Cell[][] matrix){
		return true;
	}
	public boolean actTower(Cell[][] matrix,Tower tower){
		return true;
	}
	
	public Image getCellImage()
	{
		return this._image;
	}

	public void setCellImage(Image img)
	{
		 this._image = img;
	}
	
}