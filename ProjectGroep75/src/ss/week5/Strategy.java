package ss.week5;

public interface Strategy {
	/**
	 * Returns the name of the strategy;
	 */
	public String getName();
	
	/**
	 * Returns a next legal move, given the Board b, for the player with Mark m.
	 */
	public int determineMove(Board b, Mark m);
}
