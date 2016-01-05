package ss.week6.voteMachine;

/**
 * Votelist for the Votemachine.
 *
 * @author Steven Hendriksen and Stan Peters
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class VoteList extends Observable{
	// ------------------ Instance variables ----------------
	private Map<String, Integer> allVotes = new HashMap<String, Integer>();

	// ------------------ Methods ------------------------
	/**
	 * Adds a vote to the votelist and prints vote;
	 */
	public void addVote(String vote){
		if(allVotes.get(vote) == null){
			allVotes.put(vote, 1);
		}
		else{
			allVotes.put(vote, allVotes.get(vote) + 1);
		}
		setChanged();
		notifyObservers("vote");
	}
	
	/**
	 * Returns all the parties.
	 */
	public Map<String, Integer> getVotes(){
		return allVotes;
	}
}
