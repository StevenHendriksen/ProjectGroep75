package ss.week4;

import org.junit.Test;
import ss.week4.MergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MergeSortTest {
	@Test
	public void testMergesortEmptyList() {
		List<Integer> sequence = new ArrayList<>(0);
		MergeSort.mergesort(sequence);
		assertEquals(sequence, Collections.emptyList());
	}

	@Test
	public void testMergesortSingleItem() {
		System.out.println("test1");
		List<Integer> sequence = new ArrayList<>(Arrays.asList(1));
		MergeSort.mergesort(sequence);
		assertEquals(sequence, Arrays.asList(1));
	}

	@Test
	public void testMergesortSortedList() {
		System.out.println("test2");
		List<Integer> sequence = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		MergeSort.mergesort(sequence);
		assertEquals(sequence, Arrays.asList(1, 2, 3, 4, 5));

		sequence = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		MergeSort.mergesort(sequence);
		assertEquals(sequence, Arrays.asList(1, 2, 3, 4, 5, 6));
	}

	@Test
	public void testMergesortUnsortedList() {
		System.out.println("test3");
		List<Integer> sequence = new ArrayList<>(Arrays.asList(3, 2, 1, 5, 4));
		System.out.println("test4");
		MergeSort.mergesort(sequence);
		System.out.println("test4");
		System.out.println("sequence" + sequence);
		assertEquals(sequence, Arrays.asList(1, 2, 3, 4, 5));

		sequence = new ArrayList<>(Arrays.asList(3, 2, 1, 6, 5, 4));
		MergeSort.mergesort(sequence);
		assertEquals(sequence, Arrays.asList(1, 2, 3, 4, 5, 6));
	}
}
