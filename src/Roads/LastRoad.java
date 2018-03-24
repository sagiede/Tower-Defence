package Roads;
import java.util.LinkedList;
import Enemies.Enemy;
import SwingFiles.Photos;

public class LastRoad extends Road {
	
	public LinkedList<Enemy> enemysList;
	
	public LastRoad (int x,int y,int toX,int toY){
		super(x,y,toX,toY);
		enemysList = new LinkedList<>();
		setCellImage(Photos._lastRoad);			//put other image then road
	}

	
}
