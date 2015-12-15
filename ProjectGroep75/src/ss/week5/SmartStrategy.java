package ss.week5;

import java.util.ArrayList;
import java.util.List;

public class SmartStrategy implements Strategy {
	public String getName() {
		return "Smart";
	}
	
	public int determineMove(Board b, Mark m){
		if(b.isEmptyField((b.DIM * b.DIM)/2)){
			return (b.DIM * b.DIM)/2;
		}
		else if(canWin(b, m) != -1){
			return canWin(b, m);
		}
		else if(canWin(b, m.other()) != -1){
			return canWin(b, m.other());
		}
		else{
			return empty(b);
		}
	}
	
	public int canWin(Board b, Mark m){
		Board deepcopy = b.deepCopy();
		for(int i = 0; i < deepcopy.DIM * deepcopy.DIM; i++){
			if(deepcopy.isEmptyField(i)){
				deepcopy.setField(i, m);
				if(deepcopy.isWinner(m)){
					return i;
				}
				deepcopy.setField(i, Mark.EMPTY);
			}
		}
		return -1;
	}
	
	public int empty(Board b){
		List<Integer> res = new ArrayList<Integer>();
		for(int i = 0; i < b.DIM * b.DIM; i++){
			if(b.isEmptyField(i)){
				res.add(i);
			}
		}
		
		int index = (int) (Math.random() * res.size());
		
		return res.get(index);
	}
}