package ss.week5;

/**
 * Executable class for the game Tic Tac Toe. The game can be played against the
 * computer. Lab assignment Module 2
 * 
 * @author Theo Ruys
 * @version $Revision: 1.4 $
 */
public class TicTacToe {
	public static void main(String[] args) {
		Game game;
		Strategy smartstrategy = new SmartStrategy();

		if (args[0].equals("-n") && args[1].equals("-n")) {
			game = new Game(new ComputerPlayer(Mark.XX), new ComputerPlayer(Mark.OO));
		} else if (args[0].equals("-n") && args[1].equals("-s")) {
			game = new Game(new ComputerPlayer(Mark.XX), new ComputerPlayer(Mark.OO, smartstrategy));
		} else if (args[0].equals("-n") && !args[1].equals("-n") && !args[1].equals("-s")) {
			game = new Game(new ComputerPlayer(Mark.XX), new HumanPlayer(args[1], Mark.OO));
		} else if (args[0].equals("-s") && args[1].equals("-n")) {
			game = new Game(new ComputerPlayer(Mark.XX, smartstrategy), new ComputerPlayer(Mark.OO));
		} else if (args[0].equals("-s") && args[1].equals("-s")) {
			game = new Game(new ComputerPlayer(Mark.XX, smartstrategy), new ComputerPlayer(Mark.OO, smartstrategy));
		} else if (args[0].equals("-s") && !args[1].equals("-n") && !args[1].equals("-s")) {
			game = new Game(new ComputerPlayer(Mark.XX, smartstrategy), new HumanPlayer(args[1], Mark.OO));
		} else if (!args[0].equals("-n") && !args[0].equals("-s") && args[1].equals("-n")) {
			game = new Game(new HumanPlayer(args[0], Mark.XX), new ComputerPlayer(Mark.OO));
		} else if (!args[0].equals("-n") && !args[0].equals("-s") && args[1].equals("-s")) {
			game = new Game(new HumanPlayer(args[0], Mark.XX), new ComputerPlayer(Mark.OO, smartstrategy));
		} else {
			game = new Game(new HumanPlayer(args[0], Mark.XX), new HumanPlayer(args[1], Mark.OO));
		}

		game.start();
	}
}