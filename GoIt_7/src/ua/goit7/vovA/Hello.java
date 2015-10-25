package ua.goit7.vovA;

import java.util.Scanner;

public class Hello {

	public static void main(String[] args) {

		System.out.println("What is your name?");
		Scanner s = new Scanner(System.in);
		System.out.println("Hello " + s.next() + "!");
		s.close();
	}

}
