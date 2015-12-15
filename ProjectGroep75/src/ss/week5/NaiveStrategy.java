package ss.week5;

import java.util.ArrayList;
import java.util.List;

public class NaiveStrategy implements Strategy {
	public String getName() {
		return "Naive";
	}

	public int determineMove(Board b, Mark m) {
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < Board.DIM * Board.DIM; i++) {
			if (b.isEmptyField(i)) {
				res.add(i);
			}
		}

		int index = (int) (Math.random() * res.size());

		return res.get(index);
	}
}
