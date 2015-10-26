package ua.goit7.vovat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LonelyNumber {

	public static void main(String[] args) {

		ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 2, 3, 4, 4, 5, 6));

		HashMap<Integer, Integer> numbers = new HashMap<Integer, Integer>();

		for (int i = 0; i < array.size(); i++) {
			Integer tempChar = array.get(i);

			if (!numbers.containsKey(tempChar)) {
				numbers.put(tempChar, 1);
			} else {
				numbers.put(tempChar, numbers.get(tempChar) + 1);
			}
		}

		for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {
			if (entry.getValue() == 3) {
				System.out.println("Number = " + entry.getKey());
				break;
			}
		}
	}
}
