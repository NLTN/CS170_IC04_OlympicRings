
/*
    Nguyen, Nguyen
    CS A170
    02/09/2018

    IC04_OlympicRings
*/

import java.awt.Point;

public class Trigonometry {
	public static class IntersectionPoints {
		public Point Point1 = new Point(), Point2 = new Point();
	}

	/**
	 * Convert Radian to Degree. Return a {@code double} value.
	 */
	public static double convertRadianToDegree(double angle) {
		return angle * 180 / Math.PI;
	}

	// https://math.stackexchange.com/a/1367732
	/**
	 * Return the {@code IntersectionPoints} at which two circles intersect. This
	 * method is based on the math https://math.stackexchange.com/a/1367732 by
	 * johannesvalks.
	 */
	public static IntersectionPoints getIntersectionPoints(Circle circle1, Circle circle2) {
		// Variables
		IntersectionPoints points = new IntersectionPoints();

		// R = The distance between the centers of two circles
		double R = getDistanceBetweenTheCentersOfTwoCircles(circle1, circle2);

		double x1 = circle1.Center.x;
		double x2 = circle2.Center.x;
		double y1 = circle1.Center.y;
		double y2 = circle2.Center.y;

		// fx
		double fx = (x1 + x2) / 2
				+ ((Math.pow(circle1.Radius, 2) - Math.pow(circle2.Radius, 2)) / (2 * Math.pow(R, 2))) * (x2 - x1);

		// gx
		double gx = 0.5
				* Math.sqrt((2 * (Math.pow(circle1.Radius, 2) + Math.pow(circle2.Radius, 2)) / Math.pow(R, 2))
						- (Math.pow(Math.pow(circle1.Radius, 2) - Math.pow(circle2.Radius, 2), 2)) / Math.pow(R, 4) - 1)
				* (y2 - y1);
		// fy
		double fy = (y1 + y2) / 2
				+ (Math.pow(circle1.Radius, 2) - Math.pow(circle2.Radius, 2)) / (2 * Math.pow(R, 2)) * (y2 - y1);

		// gy
		double gy = 0.5
				* Math.sqrt((2 * (Math.pow(circle1.Radius, 2) + Math.pow(circle2.Radius, 2)) / Math.pow(R, 2))
						- (Math.pow(Math.pow(circle1.Radius, 2) - Math.pow(circle2.Radius, 2), 2)) / Math.pow(R, 4) - 1)
				* (x1 - x2);

		points.Point1.setLocation(fx + gx, fy + gy);
		points.Point2.setLocation(fx - gx, fy - gy);

		// Print to console
		System.out.println("fx=" + fx);
		System.out.println("gx=" + gx);
		System.out.println("fy=" + fy);
		System.out.println("gy=" + gy);
		System.out.println("(x1, y1)=" + points.Point1.getX() + "," + points.Point1.getY());
		System.out.println("(x2, y2)=" + points.Point2.getX() + "," + points.Point2.getY());

		// Return
		return points;
	}

	/**
	 * Return the distance between the centers of two circles as {@code double}
	 * value. This method is based on the math
	 * https://math.stackexchange.com/a/1367732 by johannesvalks.
	 */
	public static double getDistanceBetweenTheCentersOfTwoCircles(Circle circle1, Circle circle2) {
		// R = Square of (x2-x1)^2 + (y2 - y1)^2
		return Math.sqrt(Math.pow((circle2.Center.getX() - circle1.Center.getX()), 2)
				+ Math.pow((circle2.Center.getY() - circle1.Center.getY()), 2));
	}

	/**
	 * Return the distance between the centers of two circles as {@code double}
	 * value. This method is based on the math
	 * https://math.stackexchange.com/a/1367732 by johannesvalks.
	 */
	public static double getDistanceBetweenTheCentersOfTwoCircles(double x1, double x2, double y1, double y2) {
		// R = Square of (x2-x1)^2 + (y2 - y1)^2
		return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}
}