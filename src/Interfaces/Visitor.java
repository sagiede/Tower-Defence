package Interfaces;

import Towers.ArrowTower;
import Towers.DinoTower;
import Towers.GokuTower;
import Towers.LavaTower;
import Towers.MagicTower;
import Towers.PoisonTower;
import Towers.SamTower;

public interface Visitor {
	
	// the visit info for each tower
	public void visit (ArrowTower arrowTower);
	public void visit (GokuTower gokuTower);
	public void visit (LavaTower lavaTower);
	public void visit (MagicTower magicTower);
	public void visit (PoisonTower poisonTower);
	public void visit (SamTower samTower);
	public void visit (DinoTower dinoTower);

}
