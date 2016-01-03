package ss.week6.voteMachine;

import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Observable;

public class VoteTUIView implements Observer {
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
	
	public void update(Observable obs, Object obj){
		String result;
		
		if(obj == "party"){
			result = "A party is added";
		}
		else {
			result = "A vote is added";
		}
		
		System.out.println(result);
	}
} 
