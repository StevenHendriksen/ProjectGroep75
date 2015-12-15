package ss.week5;

public class ComputerPlayer extends Player{
	Strategy strategy;
	
	public ComputerPlayer(Mark mark, Strategy strategy){
		super(strategy.getName() + "-" + mark, mark);
		this.strategy = strategy;
	}
	
	public ComputerPlayer(Mark mark){
		this(mark, new NaiveStrategy());
	}
	
	public int determineMove(Board board){
		return strategy.determineMove(board, getMark());
	}
	
	public Strategy getStrategy(){
		return strategy;
	}
	
	public void setStrategy (Strategy strategy){
		this.strategy = strategy;
	}
}