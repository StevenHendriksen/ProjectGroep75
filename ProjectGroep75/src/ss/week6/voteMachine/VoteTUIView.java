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
	public VoteTUIView() {
		votemachine = new VoteMachine();
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
		boolean exit = false;
		System.out.println("Choose between: VOTE (party), ADD PARTY (party), VOTES, PARTIES, EXIT, HELP");
		Scanner in = new Scanner(System.in);
		while (exit == false){
			while (in.hasNextLine()){
				String line = in.nextLine();
				Scanner read = new Scanner(line);
				if(read.hasNext()){
					String check = read.next();
					if(check.equals("VOTE")){
						String party = read.next();
					}
					else if(check.equals("ADD")){
						String check2 = read.next();
						if(check.equals("PARTY")){
							String party = read.next();
						}
					}
					else if(check.equals("VOTES")){
						
					}
					else if(check.equals("PARTIES")){
						
					}
					else if(check.equals("EXIT")){
						
					}
					else if(check.equals("HELP")){
						
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
