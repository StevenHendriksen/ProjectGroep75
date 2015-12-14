package ss.week5;

/**
 * Executable class for the game Tic Tac Toe. The game can be played against the
 * computer. Lab assignment Module 2
 * 
 * @author Theo Ruys
 * @version $Revision: 1.4 $
 */
public class TicTacToe{
	public static void main(String[] args) {
		Game game = new Game(new HumanPlayer("Johnny", Mark.XX), new HumanPlayer("Anita", Mark.OO));
		game.start();
	}
}
