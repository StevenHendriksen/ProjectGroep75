package ss.week6.voteMachine;

import java.util.List;
import java.util.Map;

public class VoteMachine{
	private static VoteMachine voteMachine;
	private PartyList partylist;
	private VoteList votelist;
	private VoteTUIView votetuiview;
	
	public VoteMachine(){
		partylist = new PartyList();
		votelist = new VoteList();
		votetuiview = new VoteTUIView();
	}
	
	public static void main(String[] args){
		voteMachine = new VoteMachine();
		voteMachine.start();
	}
	
	public void start(){
		votetuiview.start();
	}
	
	public void addParty(String party){
		partylist.addParty(party);
	}
	
	public void vote(String party){
		votelist.addVote(party);
	}
	
	public List<String> getParties(){
		return partylist.getParties();
	}
	
	public Map<String, Integer> getVotes(){
		return votelist.getVotes();
	}
}
