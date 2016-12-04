package day2;

import java.util.ArrayList;
import java.util.List;

import ulit.ReadFile;

public class Main {

	public static void main(final String[] args) {
		final String input = ReadFile.readFromFile("src/main/resources/input2.txt");
		final String[] instructuions = input.split("\n");

		final List<String> code = new ArrayList<>();
		final NumPadComplex numpad = new NumPadComplex(5);

		for(final String instruction : instructuions) {
			for(int i=0; i< instruction.length(); i++) {
				numpad.changeButton(instruction.charAt(i));
			}

			code.add(Integer.toHexString(numpad.getSelection()));
		}

		System.out.println(code);

	}

}
