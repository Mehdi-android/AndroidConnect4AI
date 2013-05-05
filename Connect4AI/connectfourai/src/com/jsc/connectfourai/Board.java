package com.jsc.connectfourai;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents the game board and holds all of the counters.
 */
public class Board {
    private Counter[][] counters = new Counter[Config.BOARD_WIDTH][Config.BOARD_HEIGHT];    
    private int[] heights = new int[Config.BOARD_WIDTH];
    private int x, y;
    private Sprite sprite;
    
    /**
     * @param x x-coordinate on the screen
     * @param y y-coordinate on the screen
     */
    public Board(int x, int y) {
        this.x = x;
        this.y = y;    	
        sprite = new Sprite(Config.BOARD_TEXTURE, Config.BOARD_TEXTURE_WIDTH, 
        		Config.BOARD_TEXTURE_HEIGHT);
        sprite.setPosition(x, y);
    }

    /**
     * @param other the other board object to clone
     */
    public Board(Board other) {
        this.counters = other.counters;
        this.x = other.x;
        this.y = other.y;
        this.sprite = new Sprite(other.sprite);
    }
    
    /**
     * Add a counter to the top of a column
     * @param column column to drop the counter into
     * @param p player who is dropping the counter
     * @return true if the counter could be added, false otherwise
     */
    public boolean addCounter(int column, Player p) { 
        if (heights[column] < Config.BOARD_HEIGHT) {
            counters[column][heights[column]] = new Counter(
        		x + Config.BOARD_PADDING + column*Config.COUNTER_RADIUS + column*Config.COUNTER_SPACING, 
        		y + Config.BOARD_PADDING + heights[column]*Config.COUNTER_RADIUS + heights[column]*Config.COUNTER_SPACING, 
                p.getColour()
            );
            heights[column]++;
            return true;
        }
        return false;
    }
    
    /**
     * Remove the top counter from a specific column
     * @param column column to remove the counter from
     * @return true if a counter was removed, false if there was no counter
     */
    public boolean removeCounter(int column) {    
    	if (heights[column] > 0) {
	        counters[column][heights[column]-1] = null;
	        heights[column]--;
	        return true;
    	}
    	return false;
    }

    /**
     * Get the column at the x-coordinate on the screen
     * @param x x-coordinate of the column on the screen
     * @return the index of the column
     */
    public int getColumnAtX(int x) {
        return (x - (Config.BOARD_PADDING + this.x))/(Config.COUNTER_RADIUS+Config.COUNTER_SPACING);
    }

    
    /**
     * Check if a counter at a specific board position is involved in a match
     * @param column column the counter is in
     * @param row row the counter is in
     * @return true if the counter is involved in a match, false otherwise
     */
    public boolean checkMatch(int column, int row) {
    	int horizontal_matches = 0;
    	int vertical_matches = 0;
    	int forward_diagonal_matches = 0;
        int backward_diagonal_matches = 0;
        
        // horizontal matches
        for (int i = 1; i < Config.COUNTERS_IN_MATCH; i++) {
        	if (matchingCounters(column, row, column+i, row)) { 
		    	horizontal_matches++;
		    } else break;
        }
        
        for (int i = 1; i < Config.COUNTERS_IN_MATCH; i++) {
        	if (matchingCounters(column, row, column-i, row)) { 
		    	horizontal_matches++;
		    } else break;
        }
        
        // vertical matches
        for (int i = 1; i < Config.COUNTERS_IN_MATCH; i++) {
        	if (matchingCounters(column, row, column, row+i)) {
        		vertical_matches++;
		    } else break;
        }
        
        for (int i = 1; i < Config.COUNTERS_IN_MATCH; i++) {
        	if (matchingCounters(column, row, column, row-i)) {
        		vertical_matches++;
		    } else break;
        }
        
        // backward diagonal matches ( \ )
        for (int i = 1; i < Config.COUNTERS_IN_MATCH; i++) {
        	if (matchingCounters(column, row, column+i, row-i)) { 
        		backward_diagonal_matches++;
		    } else break;
        }
        
        for (int i = 1; i < Config.COUNTERS_IN_MATCH; i++) {
        	if (matchingCounters(column, row, column-i, row+i)) { 
        		backward_diagonal_matches++;
		    } else break;
        }
        
        // forward diagonal matches ( / )
        for (int i = 1; i < Config.COUNTERS_IN_MATCH; i++) {
        	if (matchingCounters(column, row, column+i, row+i)) { 
        		forward_diagonal_matches++;
		    } else break;
        }
        
        for (int i = 1; i < Config.COUNTERS_IN_MATCH; i++) {
        	if (matchingCounters(column, row, column-i, row-i)) { 
        		forward_diagonal_matches++;
		    } else break;
        }
        
        if (horizontal_matches >= Config.COUNTERS_IN_MATCH-1 
        	|| vertical_matches >= Config.COUNTERS_IN_MATCH-1 
            || forward_diagonal_matches >= Config.COUNTERS_IN_MATCH-1 
            || backward_diagonal_matches >= Config.COUNTERS_IN_MATCH-1) {        	
        	return true;
        }
        return false;
    }
    
    /**
     * Check if a counter at a specific position matches a counter at a
     * different specific position
     * @param columnA column the first counter is in
     * @param rowA row the first counter is in
     * @param columnB column the second counter is in
     * @param rowB row the second counter is in
     * @return
     */
    private boolean matchingCounters(int columnA, int rowA, int columnB, int rowB){
    	// return false if either set of coordinates falls out of bounds
    	if (columnA < 0 || columnA >= Config.BOARD_WIDTH 
    		|| rowA < 0 || rowA >= Config.BOARD_HEIGHT
    		|| columnB < 0 || columnB >= Config.BOARD_WIDTH 
    		|| rowB < 0 || rowB >= Config.BOARD_HEIGHT) {
    		return false;
    	}
        if (counters[columnA][rowA]== null || counters[columnB][rowB] == null) {
            return false;
        } 
        if (!counters[columnA][rowA].colour.equals(counters[columnB][rowB].colour)) {
            return false;
        }    	
        return true;
    }

    /**
     * Render the board and counters to a sprite batch
     * @param batch the sprite batch to draw to
     */
    public void render(SpriteBatch batch) {
    	for (int i = 0; i < Config.BOARD_WIDTH; i++) {
    		for (int j = 0; j < heights[i]; j++) {    			
    			counters[i][j].render(batch);
    		}
    	}
    	sprite.draw(batch);
    }
	
	public String toString() {
		String output = "";
		for(int i = Config.BOARD_HEIGHT-1; i >= 0; i--) {
			for(int j = 0; j < Config.BOARD_WIDTH; j++) {
				if(counters[j][i] != null) {
					output += counters[j][i] + " ";
				} else {
					output += "- ";
				}
			}
			if (i != 0) {
				output += "\n";
			}
		}
		return output;
	}
	
    /**
     * Get the height the counters in a specific column
     * @param index index of the column to check
     * @return the height of the column
     */
    public int columnHeight(int index) {
        return heights[index];
    }    
    
    /**
     * Get the number of counters in the board
     * @return the number of counters
     */
    public int numberOfCounters(){
		int output = 0;
		for (int i : heights) {
			output += i;
		}
		return output;
    }
    
    /**
     * Check if the board has no space left for counters
     * @return true if the board is full, false otherwise
     */
    public boolean isFull() {
    	return numberOfCounters() == Config.BOARD_HEIGHT * Config.BOARD_WIDTH;
    }
}