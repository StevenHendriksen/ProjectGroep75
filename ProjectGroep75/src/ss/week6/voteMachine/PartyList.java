package ss.week6.voteMachine;

/**
 * Partylist for the Votemachine.
 *
 * @author Steven Hendriksen and Stan Peters
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PartyList extends Observable{
	// ------------------ Instance variables ----------------
	private List<String> allParties = new ArrayList<>();

	// ------------------ Methods ------------------------
	/**
	 * Adds a party to the partylist and prints party;
	 */
	public void addParty(String name){
		allParties.add(name);
		setChanged();
		notifyObservers();
		System.out.println("party");
	}
	
	/**
	 * Shows whether the party is in the partylist or not.
	 * 
	 * @param result
	 * 			Shows whether the party is in the partylist or not.
	 */
	public boolean hasParty(String name){
		boolean result = false;
		
		for(int i = 0; i < allParties.size(); i++){
			if (allParties.get(i) == name){
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * Returns all the parties.
	 */
	public List<String> getParties(){
		return allParties;
	}
}
