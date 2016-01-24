import java.awt.geom.Rectangle2D;

/**
 * MoveableText.java
 * 
 * An interface that contains methods to be implemented regarding the position,
 * or location, of BannerStrings, and the rectangle that bounds a given
 * BannerString. This interface is to be implemented by BannerStrings so that it
 * is possible to locate and adjust the string in an applet for wrapping and
 * collisions.
 * 
 * @author vsub21
 *
 */
public interface MoveableText
{
	/**
	 * Moves the MoveableText object.
	 */
	public void move();

	/**
	 * Returns the x position of the MoveableText object.
	 * 
	 * @return the x position
	 */
	public int getX();

	/**
	 * Returns the y position of the MoveableText object.
	 * 
	 * @return the y position
	 */
	public int getY();

	/**
	 * Returns the bounding rectangle of the MoveableText object.
	 * 
	 * @return the bounding rectangle
	 */
	public Rectangle2D getBoundingRectangle();

	/**
	 * Adjusts the positioning of the MoveableText object based on information
	 * regarding the height and width of a given applet.
	 * 
	 * @param appletWidth
	 * @param appletHeight
	 */
	public void adjustForBoundaries(int appletWidth, int appletHeight);
}
