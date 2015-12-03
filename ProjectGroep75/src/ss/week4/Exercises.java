package ss.week4;

/**
 * Exercises;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public class Exercises {
	// ------------------ Commands ------------------------
	/**
	 * Checks how many numbers of the array are negative;
	 * returns int based on the number of numbers which are negative;
	 */
    public static int countNegativeNumbers(int[] arr) {
        int result2 = 0;
    	int i = 0;
    	for(i = 0; i < arr.length; i++){
        	if(arr[i] < 0){
        		result2 = result2 + 1;
        	}
        }
    	return result2;
    }

    /**
	 * Reverses an array;
	 * returns the array reversed;
	 */
    public static int[] reverseArray(int[] ints) {
    	for(int i = 0; i < (ints.length/2); i++){
    		int replace = ints[i];
    	    ints[i] = ints[ints.length - i - 1];
    	    ints[ints.length - i - 1] = replace;
    	}
    	
    	return ints;
    }

    class SimpleList {
        public class Element {}

        public class Node {
            public Node next;
            public Element element;
        }

        private Node first;

        private Node find(Element element) {
            Node p = first;
            if (p == null) {
                return null;
            }
            while (p.next != null && !p.next.element.equals(element)) {
                p = p.next;
            }
            if (p.next == null) {
                return null;
            } else {
                return p;
            }
        }

        public void remove(Element element) {
            Node p = find(element);
            if (p != null) {
                p.next = p.next.next;
            }
        }
    }
}
