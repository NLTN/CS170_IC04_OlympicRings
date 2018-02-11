
/*
    Nguyen, Nguyen
    CS A170
    02/09/2018

    IC04_OlympicRings
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JApplet;

public class OlympicRings extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6080844738077899107L;

	public void init() {
		setSize(500, 300);
	}

	public void paint(Graphics canvas) {
		DrawOlympicRings(canvas);
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

		// Test 1
		// canvas.drawArc(beginPos.x, beginPos.y, diameter, diameter, 0,
		// (360-stroke*2));

		// Test 2
		// canvas.drawLine(60, 60, 60, 60);
		// canvas.drawLine(210, 60, 210, 60);
		// canvas.drawLine(360, 60, 360, 60);

		// System.out.println(beginPos.x + diameter / 2);
		// System.out.println(beginPos.x + 1 * (diameter + gap + stroke) + diameter /
		// 2);
		// System.out.println(beginPos.x + 2 * (diameter + gap + stroke) + diameter /
		// 2);

		
	}

	public void DrawOlympicRings(Graphics canvas) {
		// Variables - General variables
		int radius = 60, gap = 20, stroke = 10;
		Point beginPos = new Point(10, 10);

		// Variables - Positions
		Point blueRingCenter = new Point(beginPos.x + 0 * (radius * 2 + gap + stroke) + radius, beginPos.y + radius);
		Point blackRingCenter = new Point(beginPos.x + 1 * (radius * 2 + gap + stroke) + radius, beginPos.y + radius);
		Point redRingCenter = new Point(beginPos.x + 2 * (radius * 2 + gap + stroke) + radius, beginPos.y + radius);
		Point yellowRingCenter = new Point(beginPos.x + gap / 2 + radius * 2 + 0 * (radius * 2 + gap + stroke),
				beginPos.y + radius * 2);
		Point greenRingCenter = new Point(beginPos.x + gap / 2 + radius * 2 + 1 * (radius * 2 + gap + stroke),
				beginPos.y + radius * 2);

		// Variables - Olympic rings
		Ring blueRing = new Ring(blueRingCenter, radius, stroke, Color.BLUE);
		Ring yellowRing = new Ring(yellowRingCenter, radius, stroke, Color.YELLOW);
		Ring blackRing = new Ring(blackRingCenter, radius, stroke, Color.BLACK);
		Ring redRing = new Ring(redRingCenter, radius, stroke, Color.RED);
		Ring greenRing = new Ring(greenRingCenter, radius, stroke, Color.GREEN);
		
		
		// Variables - Intersection
		Trigonometry.Circle.IntersectionPoints iPoints;
		double angle;
		
		// Calculate - Yellow Ring cut out angle
		iPoints = Trigonometry.Circle.GetIntersectionPoints(yellowRing, blueRing);
		angle = Math.atan2(iPoints.Point2.getY() - yellowRing.Center.getY(), iPoints.Point2.getX() - yellowRing.Center.getX());
		yellowRing.setCutOutPosition(-(int)Trigonometry.Circle.RadianToDegree(angle));
		
		// Calculate - Black Ring cut out angle
		iPoints = Trigonometry.Circle.GetIntersectionPoints(blackRing,yellowRing);
		angle = Math.atan2(iPoints.Point2.getY() - blackRing.Center.getY(), iPoints.Point2.getX() - blackRing.Center.getX());
		blackRing.setCutOutPosition(-(int)Trigonometry.Circle.RadianToDegree(angle));
		System.out.println("angle1=" + (int)Trigonometry.Circle.RadianToDegree(angle));
		
		// Calculate - Green Ring cut out angle
		iPoints = Trigonometry.Circle.GetIntersectionPoints(greenRing,blackRing);
		angle = Math.atan2(iPoints.Point2.getY() - greenRing.Center.getY(), iPoints.Point2.getX() - greenRing.Center.getX());
		greenRing.setCutOutPosition(-(int)Trigonometry.Circle.RadianToDegree(angle));
		
		// Calculate - Red Ring cut out angle
		iPoints = Trigonometry.Circle.GetIntersectionPoints(redRing,greenRing);
		angle = Math.atan2(iPoints.Point2.getY() - redRing.Center.getY(), iPoints.Point2.getX() - redRing.Center.getX());
		redRing.setCutOutPosition(-(int)Trigonometry.Circle.RadianToDegree(angle));
				
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

		public Ring(Point centerPoint, int radius, int stroke, Color color) {
			Center = centerPoint;
			Radius = radius;
			RingColor = color;
			Stroke = stroke;
		}

		public void setCutOutPosition(int angle) {
			CutOutAngle = angle;
		}

		public void draw(Graphics canvas) {
			// Set Stroke
			Graphics2D canvas2D = (Graphics2D) canvas;
			canvas2D.setStroke(new BasicStroke(Stroke));

			canvas.setColor(RingColor);
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

			public static double RadianToDegree(double angle) {
				return angle*180/Math.PI;
			}
			
			public static IntersectionPoints GetIntersectionPoints(Ring ring1, Ring ring2) {
				IntersectionPoints points = new IntersectionPoints();

				// R = The distance between the centers of two circles
				double R = Trigonometry.Circle.DistanceBetweenTheCenterTwoCircles(ring1.Center.x, ring2.Center.x,
						ring1.Center.y, ring2.Center.y);

				double x1 = ring1.Center.x;
				double x2 = ring2.Center.x;
				double y1 = ring1.Center.y;
				double y2 = ring2.Center.y;

				// fx
				double fx = (x1 + x2) / 2
						+ ((Math.pow(ring1.Radius, 2) - Math.pow(ring2.Radius, 2)) / (2 * Math.pow(R, 2))) * (x2 - x1);

				

				// gx
				double gx = 		0.5 * 
								Math.sqrt(
											(2 * (Math.pow(ring1.Radius, 2) + Math.pow(ring2.Radius, 2)) /Math.pow(R, 2)) 
											- (Math.pow(Math.pow(ring1.Radius, 2) - Math.pow(ring2.Radius, 2), 2))/Math.pow(R, 4) - 1 
										) * (y2 - y1);
				// fy
				double fy = (y1+y2)/2 + (Math.pow(ring1.Radius, 2) - Math.pow(ring2.Radius, 2))/(2 * Math.pow(R, 2)) * (y2-y1);

				// gy
				double gy = 0.5 * 
						Math.sqrt(
								(2 * (Math.pow(ring1.Radius, 2) + Math.pow(ring2.Radius, 2)) /Math.pow(R, 2)) 
								- (Math.pow(Math.pow(ring1.Radius, 2) - Math.pow(ring2.Radius, 2), 2))/Math.pow(R, 4) - 1 
							) * (x1 - x2);
				
				System.out.println("fx=" + fx);
				System.out.println("gx=" + gx);
				System.out.println("fy=" + fy);
				System.out.println("gy=" + gy);
				
				points.Point1.setLocation(fx + gx, fy + gy);
				points.Point2.setLocation(fx - gx, fy - gy);
				
				System.out.println("(x1, y1)=" + points.Point1.getX() + "," + points.Point1.getY());
				System.out.println("(x2, y2)=" + points.Point2.getX() + "," + points.Point2.getY());
				return points;
			}

			public static double DistanceBetweenTheCenterTwoCircles(double x1, double x2, double y1, double y2) {
				// R = Square of (x2-x1)^2 + (y2 - y1)^2
				return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
			}
		}

	}
}
