package SwingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import Roads.Cell;
import Roads.Grass;
import Roads.Road;

public class LevelLoader {
	
    private static final char CHAR_GRASS            = '*';
    private static final char CHAR_RHIGHT            = 'r';
    private static final char CHAR_DOWN           = 'd';
    private static final char CHAR_LEFT          = 'l';
    private static final char CHAR_UP               = 'u';
   

    /** contains the initial board state of all the levels  */
    private Vector<Cell[][]> _levels;
    
    public LevelLoader() {
    	_levels = new Vector<>();
	}

    /**
     * Loads all the levels to the internal levels buffer
     * @param levelsFile the name of the file contains the levels
     * @return true if success
     * @throws IOException if there is any error with the file
     */
    public boolean load() throws IOException {
    	_levels.clear();

    	BufferedReader br = new BufferedReader(new FileReader("levels.txt"));
    	String line = null;
    	Cell level[][] = null;
    	int w=0;
    	int h=0;
    	int row = 0;
    	while ((line = br.readLine()) != null) {

    		// end of level
    		if (line.trim().isEmpty()) {
    			if (null != level){
    				_levels.add(level);
    				level = null;
    			}
    			continue;
    		}
    		
    		// board size
    		if (line.trim().startsWith("w")){
    			w = Integer.valueOf(line.trim().substring(1));
    			continue;
    		}    		
    		if (line.trim().startsWith("h")){
    			h = Integer.valueOf(line.trim().substring(1));
    			continue;
    		}

    		// comment
    		if (line.startsWith(";")) {
    			continue;
    		}

    		// start of level definition
    		if (null == level && h>0 && w>0){
    			level = new Cell[w][h];
    			row = 0;
    		}

    		// regular board line
    		for (int col=0 ; col< line.length(); col++){
    			Cell cell = parseCell(col, row, line.charAt(col));
    			if (null != cell){
    				level[col][row] = cell;
    			} else {
    				br.close();
    				return false;
    			}
    		}
    		row++;
    	}
		if (null != level){
			
			_levels.add(level);
			level = null;
		}
    	br.close();
    	return true;
    }
    
    /**
     * @return the number of levels available
     */
    public int getLevelsCount() {
		return _levels.size();
	}
    
    /**
     * @param index - the level number
     * @return the initial state of level number {@code index}
     *  
     * TODO - is recommended to create a deep copy of the array.
     */
    public Cell[][] get(int index) {
    	Cell[][] output = new Cell[32][32];
    	Cell[][] source = _levels.get(index);
    	
    	for (int i = 0; i < source.length; i++) {
    		for (int j = 0; j < source.length; j++) {
    			Cell cell = source[i][j];
    			if(source[i][j] instanceof Road)
    				output[i][j] = new Road(cell.getX(),cell.getY(),cell.getToX(),cell.getToY());
    			else
    				output[i][j] = new Grass(cell.getX(),cell.getY(),cell.getToX(),cell.getToY());
			}
		}
    	return output;
    }

    /**
     * create {@code Cell} instance from {@code char} representation
     * 
     * @return the {@code Cell} object 
     */
    private Cell parseCell(int col, int row, char cell) {
		switch (cell) {

		case CHAR_GRASS:
			return new Grass(col, row,0,0);
		case CHAR_RHIGHT:
			return new Road(col, row, 1,0);
		case CHAR_LEFT:
			return new Road(col, row, -1,0);
		case CHAR_DOWN:
			return new Road(col, row, 0,1);
		case CHAR_UP:
			return new Road(col, row,0,-1);
		default:
			return null;
		}
	}
    
}
