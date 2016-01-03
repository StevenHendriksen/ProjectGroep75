package ss.week6.voteMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class VoteList extends Observable{
	private Map<String, Integer> allVotes = new HashMap<String, Integer>();
	
	public void addVote(String vote){
		allVotes.put(vote, allVotes.size() + 1);
		setChanged();
		notifyObservers();
		System.out.println("vote");
	}
	
	public Map<String, Integer> getVotes(){
		return allVotes;
	}
}
