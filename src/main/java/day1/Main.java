package day1;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import util.ReaderLocal;

public class Main {
	static List<String> states = Arrays.asList("N", "E", "S", "V");
	static CircularLinkedList<String> dir = new CircularLinkedList<>(states, 0);
	static List<Line2D> lines = new ArrayList<>();
	static Point2D auxPoint = new Point(0, 0);

	public static void main(final String[] args) {
		final String pathString = ReaderLocal.readFile("input12.txt");
		final String[] path = pathString.split(",");
		int x = 0;
		int y = 0;

		for (final String step : path) {
			final String turn = step.trim().substring(0, 1);
			final String aux = step.trim().substring(1);
			final int stepsNr = Integer.parseInt(aux);
			final String direction = turn.equals("R") ? dir.nextState() : dir.previousState();

			switch (direction) {
				case "N":
					y += stepsNr;
					break;
				case "E":
					x += stepsNr;
					break;
				case "S":
					y -= stepsNr;
					break;
				case "V":
					x -= stepsNr;
					break;
			}

			createLine(new Point2D.Float(x, y));
			printIfIntersection();
		}

		printSum(x, y);

	}

	public static void printIfIntersection() {
		for (int i = 0; i < lines.size(); i++) {

			for (int j = 0; j < lines.size(); j++) {
				final Line2D line1 = lines.get(i);

				if ((j > i + 1 || j < i - 1) && line1.intersectsLine(lines.get(j))) {
					final Line2D line2 = lines.get(j);
					final double x = Objects.equals(line1.getX1(), line1.getX2()) ? line1.getX1() : line2.getX1();
					final double y = Objects.equals(line1.getY1(), line1.getY2()) ? line1.getY1() : line2.getY1();
					System.out.println("short " + (int) (Math.abs(x) + Math.abs(y)));
				}
			}
		}

	}

	public static void createLine(final Point2D point) {
		lines.add(new Line2D.Float(auxPoint, point));
		auxPoint = point;
	}

	public static void printSum(final int x, final int y) {
		System.out.println(Math.abs(x) + Math.abs(y));
	}

}
