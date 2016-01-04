package ss.week6.voteMachine;

/**
 * The Votemachine.
 *
 * @author Steven Hendriksen and Stan Peters
 * @version 1.0
 */

import java.util.List;
import java.util.Map;

public class VoteMachine{
	// ------------------ Instance variables ----------------
	private static VoteMachine voteMachine;
	private PartyList partylist;
	private VoteList votelist;
	private VoteView voteview;
	
	// ------------------ Constructor ------------------------
	public VoteMachine(){
		partylist = new PartyList();
		votelist = new VoteList();
		voteview = new VoteGUIView(this);
	}
	
	// ------------------ Methods ------------------------
	/**
	 * Starts the votemachine.
	 */
	public static void main(String[] args){
		voteMachine = new VoteMachine();
		voteMachine.start();
	}
	
	/**
	 * Starts the voteview. 
	 * Adds the observer.
	 */
	public void start(){
		voteview.start();
		votelist.addObserver(voteview);
		partylist.addObserver(voteview);
	}
	
	/**
	 * Adds a party to the partylist;
	 */
	public void addParty(String party){
		partylist.addParty(party);
	}
	
	/**
	 * Adds a vote to the votelist;
	 */
	public void addvote(String party){
		votelist.addVote(party);
	}
	
	/**
	 * Returns all parties;
	 */
	public List<String> getParties(){
		return partylist.getParties();
	}
	
	/**
	 * returns all votes;
	 */
	public Map<String, Integer> getVotes(){
		return votelist.getVotes();
	}
}