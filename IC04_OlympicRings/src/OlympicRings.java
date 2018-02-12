
/*
    Nguyen, Nguyen
    CS A170
    02/09/2018

    IC04_OlympicRings
 */

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JApplet;

public class OlympicRings extends JApplet {
	
	private static final long serialVersionUID = 6080844738077899107L;

	public void init() {
		// Set the canvas size.
		setSize(600, 300);
		
//		// Buttons
//		// Construct the button
//	     Button beep = new Button("Beep");
//
//	     beep.setSize(50, 30);
//	     // add the button to the layout
//	     this.add(beep);
	}

	public void paint(Graphics canvas) {
		final Point startingPosition = new Point(20, 20);
		final int radius = 80;
		final int gap = 20;
		final short stroke = 10;
		
		drawOlympicRings(startingPosition, radius, gap, stroke, canvas);
		/*
		 * // Variables int diameter = 120, gap = 20, stroke = 10; Point beginPos = new
		 * Point(0, 0);
		 * 
		 * // Set Stroke Graphics2D canvas2D = (Graphics2D) canvas;
		 * canvas2D.setStroke(new BasicStroke(stroke));
		 * 
		 * canvas.setColor(new Color(40, 121, 240)); canvas.drawOval(beginPos.x,
		 * beginPos.y, diameter, diameter);
		 * 
		 * canvas.setColor(Color.BLACK); canvas.drawOval(beginPos.x + 1 * (diameter +
		 * gap + stroke), beginPos.y, diameter, diameter);
		 * 
		 * canvas.setColor(Color.RED); canvas.drawOval(beginPos.x + 2 * (diameter + gap
		 * + stroke), beginPos.y, diameter, diameter);
		 * 
		 * canvas.setColor(Color.YELLOW); canvas.drawOval(beginPos.x + gap / 2 +
		 * diameter / 2, beginPos.y + diameter / 2, diameter, diameter);
		 * 
		 * canvas.setColor(Color.GREEN); canvas.drawOval(beginPos.x + gap / 2 +
		 * (diameter / 2) + 1 * (diameter + gap + stroke), beginPos.y + diameter / 2,
		 * diameter, diameter);
		 */
	}

	/**
	 * Return the angle.
	 * Based on the math http://www.mathsisfun.com/polar-cartesian-coordinates.html
	 */
	public double getCutoutPosition(Ring target, Ring source) {
		// Variables
		Trigonometry.Circle.IntersectionPoints iPoints;
		double angle;

		// Calculate - Yellow Ring cut out angle
		iPoints = Trigonometry.Circle.getIntersectionPoints(target, source);
		angle = Math.atan2(iPoints.Point2.getY() - target.Center.getY(), iPoints.Point2.getX() - target.Center.getX());

		return -Trigonometry.Circle.convertRadianToDegree(angle);
	}

	/**
	 * Draw the Olympic Rings to the canvas.
	 */
	public void drawOlympicRings(Point startingPosition, int radius, int gap, int stroke, Graphics canvas) {
		// Variables - Positions
		Point blueRingCenter = new Point(startingPosition.x + 0 * (radius * 2 + gap + stroke) + radius,
				startingPosition.y + radius);
		Point blackRingCenter = new Point(startingPosition.x + 1 * (radius * 2 + gap + stroke) + radius,
				startingPosition.y + radius);
		Point redRingCenter = new Point(startingPosition.x + 2 * (radius * 2 + gap + stroke) + radius,
				startingPosition.y + radius);
		Point yellowRingCenter = new Point(startingPosition.x + gap / 2 + radius * 2 + 0 * (radius * 2 + gap + stroke),
				startingPosition.y + radius * 2);
		Point greenRingCenter = new Point(startingPosition.x + gap / 2 + radius * 2 + 1 * (radius * 2 + gap + stroke),
				startingPosition.y + radius * 2);

		// Variables - Olympic rings
		Ring blueRing = new Ring(blueRingCenter, radius, stroke, new Color(24, 134, 192));
		Ring yellowRing = new Ring(yellowRingCenter, radius, stroke, new Color(249, 176, 65));
		Ring blackRing = new Ring(blackRingCenter, radius, stroke, Color.BLACK);
		Ring redRing = new Ring(redRingCenter, radius, stroke, new Color(234, 54, 82));
		Ring greenRing = new Ring(greenRingCenter, radius, stroke, new Color(36, 138, 64));

		// Set Cut-out position
		yellowRing.setCutOutPosition((int) getCutoutPosition(yellowRing, blueRing));
		blackRing.setCutOutPosition((int) getCutoutPosition(blackRing, yellowRing));
		greenRing.setCutOutPosition((int) getCutoutPosition(greenRing, blackRing));
		redRing.setCutOutPosition((int) getCutoutPosition(redRing, greenRing));

		// Draw to canvas
		blueRing.draw(canvas);
		yellowRing.draw(canvas);
		blackRing.draw(canvas);
		greenRing.draw(canvas);
		redRing.draw(canvas);
	}

	public class Ring {
		public Point Center;
		public int Radius;
		public int Stroke;
		public Color RingColor;
		public int CutOutAngle = -999;

		// Constructor
		public Ring(Point centerPoint, int radius, int stroke, Color color) {
			Center = centerPoint;
			Radius = radius;
			RingColor = color;
			Stroke = stroke;
		}

		/**
		 * Set the angle at which the circle will be cut out.
		 */
		public void setCutOutPosition(int angle) {
			CutOutAngle = angle;
		}

		/**
		 * Draw the circle to the canvas.
		 */
		public void draw(Graphics canvas) {
			// Set stroke
			Graphics2D canvas2D = (Graphics2D) canvas;
			canvas2D.setStroke(new BasicStroke(Stroke));

			// Set color
			canvas.setColor(RingColor);

			// Draw
			if (CutOutAngle != -999) {
				canvas.drawArc(Center.x - Radius, Center.y - Radius, (Radius * 2), (Radius * 2), (CutOutAngle + Stroke),
						360 - Stroke * 2);
			} else {
				canvas.drawOval(Center.x - Radius, Center.y - Radius, Radius * 2, Radius * 2);
			}
		}
	}

	public static class Trigonometry {
		public static class Circle {
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
			 * Return the {@code IntersectionPoints} at which two circles intersect. 
			 * This method is based on the math https://math.stackexchange.com/a/1367732 by
			 * johannesvalks.
			 */
			public static IntersectionPoints getIntersectionPoints(Ring ring1, Ring ring2) {
				// Variables
				IntersectionPoints points = new IntersectionPoints();

				// R = The distance between the centers of two circles
				double R = Trigonometry.Circle.getDistanceBetweenTheCentersOfTwoCircles(ring1, ring2);

				double x1 = ring1.Center.x;
				double x2 = ring2.Center.x;
				double y1 = ring1.Center.y;
				double y2 = ring2.Center.y;

				// fx
				double fx = (x1 + x2) / 2
						+ ((Math.pow(ring1.Radius, 2) - Math.pow(ring2.Radius, 2)) / (2 * Math.pow(R, 2))) * (x2 - x1);

				// gx
				double gx = 0.5
						* Math.sqrt((2 * (Math.pow(ring1.Radius, 2) + Math.pow(ring2.Radius, 2)) / Math.pow(R, 2))
								- (Math.pow(Math.pow(ring1.Radius, 2) - Math.pow(ring2.Radius, 2), 2)) / Math.pow(R, 4)
								- 1)
						* (y2 - y1);
				// fy
				double fy = (y1 + y2) / 2
						+ (Math.pow(ring1.Radius, 2) - Math.pow(ring2.Radius, 2)) / (2 * Math.pow(R, 2)) * (y2 - y1);

				// gy
				double gy = 0.5
						* Math.sqrt((2 * (Math.pow(ring1.Radius, 2) + Math.pow(ring2.Radius, 2)) / Math.pow(R, 2))
								- (Math.pow(Math.pow(ring1.Radius, 2) - Math.pow(ring2.Radius, 2), 2)) / Math.pow(R, 4)
								- 1)
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
			 * Return the distance between the centers of two circles as {@code double} value.
			 * This method is based on the math https://math.stackexchange.com/a/1367732 by
			 * johannesvalks.
			 */
			public static double getDistanceBetweenTheCentersOfTwoCircles(Ring ring1, Ring ring2) {
				// R = Square of (x2-x1)^2 + (y2 - y1)^2
				return Math.sqrt(Math.pow((ring2.Center.getX() - ring1.Center.getX()), 2)
						+ Math.pow((ring2.Center.getY() - ring1.Center.getY()), 2));
			}

			public static double getDistanceBetweenTheCentersOfTwoCircles(double x1, double x2, double y1, double y2) {
				// R = Square of (x2-x1)^2 + (y2 - y1)^2
				return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
			}
		}
	}
}