
/*
    Nguyen, Nguyen
    CS A170
    02/09/2018

    IC04_OlympicRings
*/

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;

public class OlympicRings extends JApplet implements ActionListener {
	// FIELDS
	private static final long serialVersionUID = 6080844738077899107L;
	private final Point STARTING_POSITION = new Point(20, 100);
	private final int CANVAS_WIDTH = 800, CANVAS_HEIGHT = 500;
	private final int BUTTON_WIDTH = 70, BUTTON_HEIGHT = 20;
	private int radius = 105, gap = 40, stroke = 10;

	// Initialize
	public void init() {
		// Set the canvas size.
		setSize(CANVAS_WIDTH, CANVAS_HEIGHT);

		// Initialize buttons
		initControllers();
	}

	public void initControllers() {
		// Variables
		Point controllerStartingPosition = new Point(10, 10);

		Button btnUp = new Button("Up");
		Button btnCenter = new Button("Center");
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
		this.add(btnCenter);
		this.add(btnDown);
		this.add(btnRight);
		this.add(btnDecreaseRadius);
		this.add(btnIncreaseRadius);
		this.add(btnDecreaseGap);
		this.add(btnIncreaseGap);
		this.add(btnDecreaseStroke);
		this.add(btnIncreaseStroke);

		// Button Properties - Sizes & Positions
		btnLeft.setBounds(controllerStartingPosition.x, controllerStartingPosition.y + BUTTON_HEIGHT, BUTTON_WIDTH,
				BUTTON_HEIGHT);
		btnCenter.setBounds(btnLeft.getWidth() + btnLeft.getX(), controllerStartingPosition.y + BUTTON_HEIGHT,
				BUTTON_WIDTH, BUTTON_HEIGHT);
		btnDown.setBounds(btnCenter.getX(), btnCenter.getY() + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		btnRight.setBounds(btnCenter.getWidth() + btnCenter.getX(), controllerStartingPosition.y + BUTTON_HEIGHT,
				BUTTON_WIDTH, BUTTON_HEIGHT);

		btnUp.setBounds(btnCenter.getX(), controllerStartingPosition.y, BUTTON_WIDTH, BUTTON_HEIGHT);

		btnDecreaseRadius.setBounds(btnRight.getWidth() + btnRight.getX() + 20,
				controllerStartingPosition.y + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		btnIncreaseRadius.setBounds(btnDecreaseRadius.getWidth() + btnDecreaseRadius.getX(),
				controllerStartingPosition.y + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

		btnDecreaseGap.setBounds(btnIncreaseRadius.getWidth() + btnIncreaseRadius.getX() + 20,
				controllerStartingPosition.y + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		btnIncreaseGap.setBounds(btnDecreaseGap.getWidth() + btnDecreaseGap.getX(),
				controllerStartingPosition.y + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

		btnDecreaseStroke.setBounds(btnIncreaseGap.getWidth() + btnIncreaseGap.getX() + 20,
				controllerStartingPosition.y + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		btnIncreaseStroke.setBounds(btnDecreaseStroke.getWidth() + btnDecreaseStroke.getX(),
				controllerStartingPosition.y + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

		// Add listeners
		btnLeft.addActionListener(this);
		btnCenter.addActionListener(this);
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
			STARTING_POSITION.x -= 10;
			break;
		case "Right":
			STARTING_POSITION.x += 10;
			break;
		case "Up":
			STARTING_POSITION.y -= 10;
			break;
		case "Down":
			STARTING_POSITION.y += 10;
			break;
		case "Center":
			STARTING_POSITION.x = (CANVAS_WIDTH - 3 * 2 * radius - 2 * gap - 2 * stroke) / 2;
			STARTING_POSITION.y = (CANVAS_HEIGHT + 3 * BUTTON_HEIGHT - 3 * radius + stroke) / 2;
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
		drawOlympicRings(STARTING_POSITION, radius, gap, stroke, canvas);
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
		Circle blueRing = new Circle(blueRingCenter, radius, stroke, new Color(24, 134, 192), null);
		Circle yellowRing = new Circle(yellowRingCenter, radius, stroke, new Color(249, 176, 65), null);
		Circle blackRing = new Circle(blackRingCenter, radius, stroke, Color.BLACK, null);
		Circle redRing = new Circle(redRingCenter, radius, stroke, new Color(234, 54, 82), null);
		Circle greenRing = new Circle(greenRingCenter, radius, stroke, new Color(36, 138, 64), null);

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
}