package Roads;


import java.util.LinkedList;

import Enemies.Enemy;
import SwingFiles.Photos;
import Towers.Tower;
import Towers.TowerKicker;

public class Road extends Cell {
		
	private LinkedList<Enemy> _enemysList;
	
	public Road (int x,int y,int toX,int toY){
		super(x,y,toX,toY);
		this._enemysList = new LinkedList<>();
			setCellImage(Photos._regularImage);
	}
	
	//function to attack the enemies on this cell
	public boolean actTower(Cell[][]matrix,Tower tower)
	{
		if(tower instanceof TowerKicker){
		for (Enemy enemy : getEnemysList()) {
			enemy.actTower(tower);
		}
		}
		else			//instance of regular tower
		{
			getEnemysList().get(0).actTower(tower);
		}
		return true;
	}
	
	//se the attacked image of the enemy that impacted
	public void setImage(String imageKind){
		
		if (imageKind.equals("arrowAttack"))
			setCellImage(Photos._arrowAttack);
		
		else
			if (imageKind.equals("dinoAttack"))
			setCellImage(Photos._dinoAttack);
		
		else
			if (imageKind.equals("gokuAttack"))
			setCellImage(Photos._gokuAttack);
		
		else
			if (imageKind.equals("lavaAttack"))
				setCellImage(Photos._lavaAttack);
		
		else
			if (imageKind.equals("magicAttack"))
				setCellImage(Photos._magicAttack);
		else
			if (imageKind.equals("poisonAttack")){
			setCellImage(Photos._poisonAttack);
			}
		else
			if (imageKind.equals("samAttack"))
				setCellImage(Photos._samAttack);
		else
			setCellImage(Photos._regularImage);
	}
	
	//return the deault image of the cell
	public void setDefaultImage(){
		if(this instanceof LastRoad)
			setCellImage(Photos._lastRoad);
		else if(this instanceof FirstRoad)
			setCellImage(Photos._firstRoad);
		else
			setCellImage(Photos._regularImage);
	}
	public LinkedList<Enemy> getEnemysList() {
		return _enemysList;
	}

}