package ss.week6.voteMachine;

import java.util.HashMap;
import java.util.Map;

public class VoteList {
	private Map<String, Integer> allVotes = new HashMap<String, Integer>();
	
	public void addVote(String vote){
		allVotes.put(vote, allVotes.size() + 1);
	}
	
	public Map<String, Integer> getVotes(){
		return allVotes;
	}
}
