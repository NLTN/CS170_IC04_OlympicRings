
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

public class Circle {
	public Point Center;
	public int Radius;
	public int Stroke;
	private Color BorderColor = null;
	private Color FillColor = null;
	public int CutOutPosition = -999;

	// Constructors
	public Circle(Point centerPoint, int radius, int stroke, Color borderColor, Color fillColor) {
		Center = centerPoint;
		Radius = radius;
		if (borderColor != null) {
			BorderColor = borderColor;
		}

		if (fillColor != null) {
			FillColor = fillColor;
		}
		Stroke = stroke;
	}

	/**
	 * Set the angle at which the circle will be cut out.
	 */
	public void setCutOutPosition(int angle) {
		CutOutPosition = angle;
	}

	/**
	 * Set border color
	 */
	public void setBorderColor(Color color) {
		BorderColor = color;
	}

	/**
	 * Get border color
	 */
	public Color getBorderColor() {
		return BorderColor;
	}

	/**
	 * Set fill color
	 */
	public void setFillColor(Color color) {
		FillColor = color;
	}

	/**
	 * Get fill color
	 */
	public Color getFillColor() {
		return FillColor;
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
		if (CutOutPosition != -999) {
			// Draw Border
			if (BorderColor != null) {
				canvas.drawArc(Center.x - Radius, Center.y - Radius, (Radius * 2), (Radius * 2),
						(CutOutPosition + Stroke), 360 - Stroke * 2);
			}

			// Fill color
			if (FillColor != null) {
				canvas.setColor(FillColor);
				canvas.fillArc(Center.x - Radius, Center.y - Radius, (Radius * 2), (Radius * 2),
						(CutOutPosition + Stroke), 360 - Stroke * 2);
			}

		} else {
			// Draw Border
			if (BorderColor != null) {
				canvas.drawOval(Center.x - Radius, Center.y - Radius, Radius * 2, Radius * 2);
			}

			// Fill color
			if (FillColor != null) {
				canvas.setColor(FillColor);
				canvas.fillArc(Center.x - Radius, Center.y - Radius, Radius * 2, Radius * 2, 0, 360);
			}
		}
	}
}