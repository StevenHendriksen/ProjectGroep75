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
		Game game;
		
		if(args[0].equals("-n")){
			game = new Game(new HumanPlayer(args[1], Mark.OO), new ComputerPlayer(Mark.XX)); 
		}
		else if(args[1].equals("-n")){
			game = new Game(new HumanPlayer(args[0], Mark.XX), new ComputerPlayer(Mark.OO)); 
		}
		else{
			game = new Game(new HumanPlayer(args[0], Mark.XX), new HumanPlayer(args[1], Mark.OO));
		}
		
		game.start();
	}
}