package ss.week6.voteMachine;

import java.util.List;
import java.util.Map;

public class VoteTUIView {
	private VoteMachine votemachine;
	
	public VoteTUIView() {
		votemachine = new VoteMachine();
	}
	
	public void start(){
		for(int i = 0; i < 10; i++){
			System.out.println("Please enter your vote");
		}
	}
	
	public  List<String> showParties(){
		return votemachine.getParties();
	}
	
	public Map<String, Integer> showVotes(){
		return votemachine.getVotes();
	}
	
	public String showError(String error){
		return error;
		// Errors laten zien
	}
} 
