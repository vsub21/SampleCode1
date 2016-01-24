import java.awt.Font;
import java.awt.geom.Rectangle2D;

/**
 * BannerString.java
 * 
 * A class responsible for creating objects to be displayed in the applet. Each
 * object consists of a message in the form of a String, and x and y position,
 * an x and y velocity, and font (type, size, etc.) that will be used by a
 * Graphics object to paint the BannerString in the applet. Contains methods
 * that allow the BannerString to wrap around when it reaches the border of the
 * applet and also contains methods that allow it to detect collisions between
 * itself and another BannerString object, useful for implementing the RPSLK
 * game.
 * 
 * @author vsub21
 *
 */
public class BannerString implements MoveableText
{
	private int startX;
	private int startY;
	private int velocityX;
	private int velocityY;

	private int delay;
	private String message;
	private Font font;
	private Rectangle2D bounds;

	/**
	 * Constructs a BannerString object that contains the information to create
	 * the image of a String that can move and collide with other BannerStrings
	 * and wrap around the borders of an applet.
	 * 
	 * @param aStartX the given x position
	 * @param aStartY the given y position
	 * @param aVelocityX the given x velocity
 	 * @param aVelocityY the given y velocity
	 * @param aDelay the given delay
	 * @param aMessage the given message
	 * @param aFont the given font
	 * @param aBounds the given bounds
	 */
	public BannerString(int aStartX, int aStartY, int aVelocityX, int aVelocityY, int aDelay, String aMessage,
			Font aFont, Rectangle2D aBounds)
	{
		startX = aStartX;
		startY = aStartY;
		velocityX = aVelocityX;
		velocityY = aVelocityY;

		delay = aDelay;
		message = aMessage;
		font = aFont;
		bounds = aBounds;
	}

	/**
	 * Changes the x and y positions of the BannerString according to the
	 * BannerString's x and y velocities by adjusting the positions by
	 * (velocity) number of pixels each time it is called.
	 */
	public void move()
	{
		startX += velocityX;
		// System.out.println("Value of startX before if condition: " + startX);
		// // debug
		startY += velocityY;
		// System.out.println("Value of startY before if condition: " + startY);
		// // debug
		// System.out.println("Bounds info: " + bounds); // debug
	}

	/**
	 * Adjusts the x and y position of the BannerString so that the BannerString
	 * appears to wrap around the borders of the applet based on parameters
	 * describing the applet's dimensions. For example, if the BannerString
	 * moves all the way to the right and the leftmost border of the
	 * BannerString passes over the right border, it will appear again on the
	 * left side of the applet starting in a position such that the right border
	 * of the BannerString begins at the x-position of the left border of the
	 * applet. Similarly, adjustments are made to make the BannerString look
	 * like it is wrapping around all four borders, appearing at the opposite
	 * border of where it disappeared.
	 * 
	 * @param appletWidth
	 *           the width of the applet
	 * @param appletHeight
	 *           the height of the applet
	 */
	public void adjustForBoundaries(int appletWidth, int appletHeight)
	{
		if (startX + bounds.getWidth() < 0 && velocityX < 0)
		{
			startX = appletWidth;
			// System.out.println("if Value of startX after getting width from
			// if condition: " + startX); // debug
		} else if (startX > appletWidth && velocityX > 0)
		{
			startX = 0 - (int) bounds.getWidth();
			// System.out.println("else Value of startX after getting width from
			// if condition: " + startX); // debug
		}

		if (startY + bounds.getHeight() < 0 && velocityY < 0)
		{
			startY = appletHeight + (int) bounds.getHeight();
			// System.out.println("if Value of startY after getting width from
			// if condition: " + startY); // debug
		} else if (startY - bounds.getHeight() > appletHeight && velocityY > 0)
		{
			startY = 0;
			// System.out.println("else Value of startY after getting width from
			// if condition: " + startY); // debug
		}
	}

	/**
	 * Returns the message of the BannerString
	 * 
	 * @return message the message of the BannerString
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Returns the current x-position of the BannerString
	 * 
	 * @return startY the current x-position
	 */
	public int getX()
	{
		return startX;
	}

	/**
	 * Returns the current y-position of the BannerString
	 * 
	 * @return startY the current y-position
	 */
	public int getY()
	{
		return startY;
	}

	/**
	 * Returns the bounding rectangle of the original string determined by
	 * methods done to parameters from the HTML file
	 * 
	 * @return bounds the original bounding rectangle
	 */
	public Rectangle2D getBounds()
	{
		return bounds;
	}

	/**
	 * Returns the current bounding rectangle of the BannerString
	 * 
	 * @return br the bounding rectangle
	 */
	public Rectangle2D getBoundingRectangle()
	{
		Rectangle2D br = new Rectangle2D.Double(startX, startY, bounds.getWidth(), bounds.getHeight());
		// System.out.println("Bounding rectangle of '" + message + "' is " +
		// br); //debug
		return br;
	}

	/**
	 * Returns a boolean indicating if this is overlapping other by checking an
	 * intersection between the bounding rectangles of the BannerStrings.
	 * 
	 * @param other
	 *           another BannerString
	 * @return true if there is an intersection of the bounding rectangles,
	 *         false if there is not
	 */
	public boolean isOverlapping(BannerString other)
	{
		if (this.getBoundingRectangle().intersects(other.getBoundingRectangle()))
			return true;
		return false;
	}

	/**
	 * Returns the first character of the BannerString's message.
	 * 
	 * @return first character of message
	 */
	public char getChar()
	{
		return message.charAt(0);
	}
}
