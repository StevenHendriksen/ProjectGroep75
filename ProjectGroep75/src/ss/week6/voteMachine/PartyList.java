package ss.week6.voteMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PartyList extends Observable{
	private List<String> allParties = new ArrayList<>();
	
	public void addParty(String name){
		allParties.add(name);
		setChanged();
		notifyObservers();
		System.out.println("party");
	}
	
	public boolean hasParty(String name){
		boolean result = false;
		
		for(int i = 0; i < allParties.size(); i++){
			if (allParties.get(i) == name){
				result = true;
			}
		}
		
		return result;
	}
	
	public List<String> getParties(){
		return allParties;
	}
}
