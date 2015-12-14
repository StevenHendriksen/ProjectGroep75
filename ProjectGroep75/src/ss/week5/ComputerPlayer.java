package ss.week5;

public class ComputerPlayer extends Player{
	Mark mark;
	Strategy strategy;
	String name;
	
	public ComputerPlayer(Mark mark, Strategy strategy){
		super(strategy.getName(), mark);
		this.strategy = strategy;
		this.mark = mark;
		name = strategy + "-" + mark;
	}
	
	public ComputerPlayer(Mark mark){
		super("Naive", mark);
		strategy = new NaiveStrategy();
		this.mark = mark;
		name = strategy.getName() + "-" + mark;
	}
	
	public int determineMove(Board board){
		return strategy.determineMove(board, mark);
	}
	
	public Strategy getStrategy(){
		return strategy;
	}
	
	public void setStrategy (Strategy strategy){
		this.strategy = strategy;
	}
}