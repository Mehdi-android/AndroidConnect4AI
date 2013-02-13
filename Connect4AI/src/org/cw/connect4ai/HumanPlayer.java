package org.cw.connect4ai;

public class HumanPlayer implements Player {
	private int colour;
	
	public HumanPlayer(int colour) {
		this.colour = colour;
	}
	
	public int getColour() {
		return colour;
	}
	
	@Override
	public void update(Board board) {
		//handle input
		if (true) {
			board.addCounter(5, colour);
		}
	}
}
