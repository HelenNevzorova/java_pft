package ru.stqa.pft.sandbox;

import ru.stqa.pft.sandbox.Point;

public class WorkWithPoint {

	public static void main(String[] args) {
		Point p1 = new Point(2.3, 5.0);
		Point p2 = new Point(1.0, 9.5);
		System.out.println("The distance between two points is: " + Point.distance(p1, p2));
	}

	// initial version of the function in the main class (task 2)
	public static double distance(Point p1, Point p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
	}
}