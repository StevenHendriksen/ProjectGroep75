package ss.week4;

import java.util.*;

public class MergeSort {

	public static <Elem extends Comparable<Elem>> void mergesort(List<Elem> list) {
		int high = list.size();
		int low = 0;
		List<Elem> lijst = new ArrayList<>();
		for (int i = low; i <= list.size(); i++) {
			if (low < high) {
				int middle = low + (high - low) / 2;
				mergesort(list.subList(low, middle));
				mergesort(list.subList(middle + 1, high));
				for (int j = low; j < middle; j++) {
					lijst.add(i, list.get(i));
				}
				if (list.size() > 1) {
					for (int j = low; j < middle; i++) {
						lijst.add(j, list.get(j));
					}
					for (int j = middle + 1; j < high; i++) {
						lijst.add(j, list.get(j));
					}
				}
				list = lijst;
				System.out.println("list " + list);
			}
		}
	}

}
