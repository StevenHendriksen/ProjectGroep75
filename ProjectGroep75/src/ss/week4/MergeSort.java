package ss.week4;

import java.util.*;

public class MergeSort {

	public static <Elem extends Comparable<Elem>> List<Elem> mergesort(List<Elem> list) {
		List<Elem> res = new ArrayList<Elem>();
		if (list.size() <= 1) {
			return list;
		} else {
			List<Elem> fst = mergesort(list.subList(0, list.size() / 2));
			List<Elem> snd = mergesort(list.subList((list.size() / 2) + 1, list.size()));

			int fi = 0;
			int si = 0;
			while (fi < list.size() && si < list.size()) {
				if (fst.get(fi) < snd.get(si)) {
					res.add(snd.get(fi));
					fi++;
				}
				else{
					res.add(snd.get(si));
					si++;
				}
			}
			if(fi < fst.size()){
				List<Elem> c = list.subList(fi, fst.size());
				res.addAll(c);
			}
			else{
				List<Elem> c = list.subList(si, fst.size());
				res.addAll(c);
			}
		}
		return res;
	}

}
