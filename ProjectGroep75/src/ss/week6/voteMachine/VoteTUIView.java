package ss.week6.voteMachine;

/**
 * VoteTUIView for the Votemachine.
 *
 * @author Steven Hendriksen and Stan Peters
 * @version 1.0
 */

import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Scanner;
import java.util.Observable;

public class VoteTUIView implements Observer, VoteView{
	// ------------------ Instance variables ----------------
	private VoteMachine votemachine;
	
	// ------------------ Constructor ------------------------
	public VoteTUIView(VoteMachine voteMachine) {
		this.votemachine = voteMachine;
	}
	
	// ------------------ Methods ------------------------
	/**
	 * Starts the VoteTUIView;
	 * Asks for input and checks which word is the input;'
	 * 
	 * @param exit
	 * 			Checks for input;
	 * 
	 * @param in & read
	 * 			Scanner;
	 * 
	 * @param line
	 * 			Next line;
	 */
	public void start(){
		Scanner in = new Scanner(System.in);
		while (true){
			System.out.println("Please input a command.");
			if (in.hasNextLine()){
				String line = in.nextLine();
				Scanner read = new Scanner(line);
				if(read.hasNext()){
					String check = read.next();
					if(check.equals("VOTE")){
						String party = read.next();
						votemachine.vote(party);
					}
					else if(check.equals("ADD")){
						String check2 = read.next();
						if(check2.equals("PARTY")){
							String party = read.next();
							votemachine.addParty(party);
						}
					}
					else if(check.equals("VOTES")){
						System.out.println(votemachine.getVotes());
					}
					else if(check.equals("PARTIES")){
						System.out.println(votemachine.getParties());
					}
					else if(check.equals("EXIT")){
						System.exit(0);
					}
					else if(check.equals("HELP")){
						System.out.println("Commands: VOTE (party), ADD PARTY (party), VOTES, PARTIES, EXIT, HELP");
					}
				}
			}
		}
	}
	
	/**
	 * Prints all votes;
	 */
	public void showVotes(Map<String, Integer> votes){
		System.out.println(votemachine.getParties());
	}
	
	/**
	 * Prints all parties;
	 */
	public void showParties(List<String> parties){
		System.out.println(votemachine.getVotes());
	}
	
	/**
	 * Prints errors;
	 */
	public void showError(String message) {
		System.out.println("Oops: " + message);
	}
	
	/**
	 * Shows if a party is updated or a vote;
	 * 
	 * @param result 
	 * 			String, which is printed.
	 */
	public void update(Observable obs, Object obj){
		String result = null;
		
		if(obj.equals("party")){
			result = "A party is added";
		}
		else if(obj.equals("vote")){
			result = "A vote is added";
		}
		
		System.out.println(result);
	}
} 
