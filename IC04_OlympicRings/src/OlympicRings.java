
/*
    Nguyen, Nguyen
    CS A170
    02/09/2018

    IC04_OlympicRings
 */

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;

public class OlympicRings extends JApplet implements ActionListener {
	// Variables
	private static final long serialVersionUID = 6080844738077899107L;
	private final Point startingPosition = new Point(20, 100);
	private int radius = 105, gap = 40, stroke = 10;

	// Initialize
	public void init() {
		// Set the canvas size.
		setSize(800, 500);

		// Initialize buttons
		initControllers();
	}

	public void initControllers() {
		// Variables
		Point startingPosition = new Point(10, 10);
		int buttonWidth = 70, buttonHeight = 20;

		Button btnUp = new Button("Up");
		Button btnDown = new Button("Down");
		Button btnLeft = new Button("Left");
		Button btnRight = new Button("Right");
		Button btnIncreaseRadius = new Button("Radius +");
		Button btnDecreaseRadius = new Button("Radius -");
		Button btnIncreaseGap = new Button("Gap +");
		Button btnDecreaseGap = new Button("Gap -");
		Button btnIncreaseStroke = new Button("Stroke +");
		Button btnDecreaseStroke = new Button("Stroke -");

		// Turn off the layout manager
		setLayout(null);

		// Add button to layout
		this.add(btnUp);
		this.add(btnLeft);
		this.add(btnDown);
		this.add(btnRight);
		this.add(btnDecreaseRadius);
		this.add(btnIncreaseRadius);
		this.add(btnDecreaseGap);
		this.add(btnIncreaseGap);
		this.add(btnDecreaseStroke);
		this.add(btnIncreaseStroke);

		// Button Properties - Sizes & Positions
		btnLeft.setBounds(startingPosition.x, startingPosition.y + buttonHeight, buttonWidth, buttonHeight);
		btnDown.setBounds(btnLeft.getWidth() + btnLeft.getX(), startingPosition.y + buttonHeight, buttonWidth,
				buttonHeight);
		btnRight.setBounds(btnDown.getWidth() + btnDown.getX(), startingPosition.y + buttonHeight, buttonWidth,
				buttonHeight);

		btnUp.setBounds(btnDown.getX(), startingPosition.y, buttonWidth, buttonHeight);

		btnDecreaseRadius.setBounds(btnRight.getWidth() + btnRight.getX() + 20, startingPosition.y + buttonHeight,
				buttonWidth, buttonHeight);
		btnIncreaseRadius.setBounds(btnDecreaseRadius.getWidth() + btnDecreaseRadius.getX(),
				startingPosition.y + buttonHeight, buttonWidth, buttonHeight);

		btnDecreaseGap.setBounds(btnIncreaseRadius.getWidth() + btnIncreaseRadius.getX() + 20,
				startingPosition.y + buttonHeight, buttonWidth, buttonHeight);
		btnIncreaseGap.setBounds(btnDecreaseGap.getWidth() + btnDecreaseGap.getX(), startingPosition.y + buttonHeight,
				buttonWidth, buttonHeight);

		btnDecreaseStroke.setBounds(btnIncreaseGap.getWidth() + btnIncreaseGap.getX() + 20,
				startingPosition.y + buttonHeight, buttonWidth, buttonHeight);
		btnIncreaseStroke.setBounds(btnDecreaseStroke.getWidth() + btnDecreaseStroke.getX(),
				startingPosition.y + buttonHeight, buttonWidth, buttonHeight);

		// Add listeners
		btnLeft.addActionListener(this);
		btnDown.addActionListener(this);
		btnRight.addActionListener(this);
		btnUp.addActionListener(this);
		btnDecreaseRadius.addActionListener(this);
		btnIncreaseRadius.addActionListener(this);
		btnDecreaseGap.addActionListener(this);
		btnIncreaseGap.addActionListener(this);
		btnDecreaseStroke.addActionListener(this);
		btnIncreaseStroke.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Left":
			startingPosition.x -= 10;
			break;
		case "Right":
			startingPosition.x += 10;
			break;
		case "Up":
			startingPosition.y -= 10;
			break;
		case "Down":
			startingPosition.y += 10;
			break;
		case "Radius +":
			radius += 5;
			break;
		case "Radius -":
			if (radius > 5) {
				radius -= 5;
			}
			break;
		case "Gap +":
			gap += 10;
			break;
		case "Gap -":
			if (gap > 10) {
				gap -= 10;
			}
			break;
		case "Stroke +":
			stroke += 1;
			break;
		case "Stroke -":
			if (stroke > 0) {
				stroke -= 1;
			}
			break;
		}

		// Refresh/Repaint
		repaint();
	}

	public void paint(Graphics canvas) {
		// Clear the canvas
		canvas.clearRect(0, 0, this.getWidth(), this.getHeight());

		// Draw the rings
		drawOlympicRings(startingPosition, radius, gap, stroke, canvas);
	}

	/**
	 * Return the angle as radian. Based on the math
	 * http://www.mathsisfun.com/polar-cartesian-coordinates.html
	 */
	public double getCutoutPosition(Circle target, Circle source) {
		// Variables
		Trigonometry.IntersectionPoints intersections;
		double angle;

		// Calculate - Yellow Ring cut out angle
		intersections = Trigonometry.getIntersectionPoints(target, source);
		angle = Math.atan2(intersections.Point2.getY() - target.Center.getY(),
				intersections.Point2.getX() - target.Center.getX());

		return -Trigonometry.convertRadianToDegree(angle);
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
		Circle blueRing = new Circle(blueRingCenter, radius, stroke, new Color(24, 134, 192));
		Circle yellowRing = new Circle(yellowRingCenter, radius, stroke, new Color(249, 176, 65));
		Circle blackRing = new Circle(blackRingCenter, radius, stroke, Color.BLACK);
		Circle redRing = new Circle(redRingCenter, radius, stroke, new Color(234, 54, 82));
		Circle greenRing = new Circle(greenRingCenter, radius, stroke, new Color(36, 138, 64));

		// Set Cut-out position
		if (Trigonometry.getDistanceBetweenTheCentersOfTwoCircles(yellowRing, blueRing) <= radius * 2) {
			yellowRing.setCutOutPosition((int) getCutoutPosition(yellowRing, blueRing));
		}
		if (Trigonometry.getDistanceBetweenTheCentersOfTwoCircles(blackRing, yellowRing) <= radius * 2) {
			blackRing.setCutOutPosition((int) getCutoutPosition(blackRing, yellowRing));
		}

		if (Trigonometry.getDistanceBetweenTheCentersOfTwoCircles(greenRing, blackRing) <= radius * 2) {
			greenRing.setCutOutPosition((int) getCutoutPosition(greenRing, blackRing));
		}

		if (Trigonometry.getDistanceBetweenTheCentersOfTwoCircles(redRing, greenRing) <= radius * 2) {
			redRing.setCutOutPosition((int) getCutoutPosition(redRing, greenRing));
		}

		// Draw to canvas
		blueRing.draw(canvas);
		yellowRing.draw(canvas);
		blackRing.draw(canvas);
		greenRing.draw(canvas);
		redRing.draw(canvas);
	}

	public class Circle {
		public Point Center;
		public int Radius;
		public int Stroke;
		public Color BorderColor;
		private int CutOutAngle = -999;

		// Constructor
		public Circle(Point centerPoint, int radius, int stroke, Color color) {
			Center = centerPoint;
			Radius = radius;
			BorderColor = color;
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
			canvas.setColor(BorderColor);

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
							- (Math.pow(Math.pow(circle1.Radius, 2) - Math.pow(circle2.Radius, 2), 2)) / Math.pow(R, 4)
							- 1)
					* (y2 - y1);
			// fy
			double fy = (y1 + y2) / 2
					+ (Math.pow(circle1.Radius, 2) - Math.pow(circle2.Radius, 2)) / (2 * Math.pow(R, 2)) * (y2 - y1);

			// gy
			double gy = 0.5
					* Math.sqrt((2 * (Math.pow(circle1.Radius, 2) + Math.pow(circle2.Radius, 2)) / Math.pow(R, 2))
							- (Math.pow(Math.pow(circle1.Radius, 2) - Math.pow(circle2.Radius, 2), 2)) / Math.pow(R, 4)
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
}