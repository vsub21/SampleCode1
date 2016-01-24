import java.applet.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * BannerApplet.java
 * 
 * * Code originally downloaded from http://www.horstmann.com/oodp2/oodp.zip,
 * modified by vsub21 *
 * 
 * Creates a Java applet containing strings (consisting of one character, either
 * R, P, S, L, or K) that move across the applet in a given direction forever,
 * wrapping around the borders of the applet. These strings are constructed
 * based on information provided by the HTML document, BannerApplet.html. If two
 * of these strings collide with each other, depending on the string itself, one
 * of the strings will be destroyed or they may continue on harmlessly as
 * according to the rules of RPSLK. Essentially, a game of RPSLK is played
 * between the two Strings to determine who the survivor is. If there is a tie
 * between the two Strings, the strings will continue on as though nothing
 * happened. Upon collision, a message is printed indicating the event that took
 * place between the strings; for example the intersection between a Rock and
 * Lizard (R and L) would produce the message "Rock crushes Lizard," while the
 * intersection between Paper and Rock (P and R) would produce the message
 * "Paper covers Rock."
 * 
 * @author Horstmann, vsub21
 *
 */
public class BannerApplet extends Applet
{
	public static final int NUMBER_OF_STRINGS = 7; // number of strings that are
													// to be produced as
													// according to HTML
													// document
	public static final int PAUSE = 3000; // number of milliseconds thread
											// should go to sleep

	// List of constants to be used for drawing the graphics for status messages
	// when collisions occur
	public static final int STATUS_MSG_X = 0;
	public static final int STATUS_MSG_Y = 15;
	public static final String STATUS_MSG_FONTNAME = "Serif";
	public static final int STATUS_MSG_FONTSIZE = 20;

	public boolean doublecollide;

	private ArrayList<BannerString> appletStrings;

	private Timer timer;
	private Font font;
	private Decider decider;
	private Graphics2D g2;

	/**
	 * Runs the following methods first upon initialization of the applet.
	 */
	public void init()
	{
		appletStrings = new ArrayList<BannerString>();
		decider = new Decider();

		String fontname = getParameter("fontname");
		int fontsize = Integer.parseInt(getParameter("fontsize"));
		font = new Font(fontname, Font.PLAIN, fontsize);

		int delay = Integer.parseInt(getParameter("delay"));

		g2 = (Graphics2D) getGraphics();
		FontRenderContext context = g2.getFontRenderContext();
		doublecollide = true;

		// Gets the parameters for the BannerStrings from the HTML file and
		// constructs BannerString objects from the information
		for (int i = 1; i <= NUMBER_OF_STRINGS; i++)
		{
			String message = getParameter("message" + i);
			// System.out.println(message);
			Rectangle2D bounds = font.getStringBounds(message, context);
			int startX = Integer.parseInt(getParameter("startX" + i));
			int startY = Integer.parseInt(getParameter("startY" + i));
			int velocityX = Integer.parseInt(getParameter("velocityX" + i));
			int velocityY = Integer.parseInt(getParameter("velocityY" + i));

			BannerString st = new BannerString(startX, startY, velocityX, velocityY, delay, message, font, bounds);
			appletStrings.add(st);
		}

		// Creates a Rectangle2D representation of the applet to use for
		// checking if a BannerObject is inside of the applet boundaries
		Rectangle2D appletBounds = new Rectangle2D.Double(0, 0, getWidth(), getHeight());

		// Check to make sure initial positions of Strings are within the
		// boundaries of the Applet
		for (BannerString st : appletStrings)
		{
			// intersects checks both if rectangle is inside the area and on the
			// boundary
			if (!(st.getBoundingRectangle().intersects(appletBounds)))
			{
			throw new IllegalArgumentException("Initial position of string with message '" + st.getMessage()
					+ "' not within boundaries of applet; " + "please choose new starting coordinates.");
			}
		}

		timer = new Timer(delay, new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
			for (BannerString st : appletStrings)
			{
				st.move();
				st.adjustForBoundaries(getWidth(), getHeight());
			}
			// Parses through list of BannerStrings to check if any are
			// overlapping (colliding)
			for (int i = 0; i < appletStrings.size(); i++)
				for (int j = i + 1; j < appletStrings.size(); j++)
					// Can use "==" operator since it is checking if it is
					// the same object (same class and hashcode)
					if (appletStrings.get(i).isOverlapping(appletStrings.get(j))
							&& appletStrings.get(i) != appletStrings.get(j))
					{
						System.out.println("About to execute playRPSLK()..."); // debug
						playRPSLK(appletStrings.get(i), appletStrings.get(j));
					}
			repaint();
			}
		});
	}

	/**
	 * Starts the timer upon reopening the window.
	 */
	public void start()
	{
		timer.start();
	}

	/**
	 * Stops the timer upon minimizing.
	 */
	public void stop()
	{
		timer.stop();
	}

	/**
	 * Responsible for painting the graphics of all the components in the
	 * applet.
	 */
	public void paint(Graphics g)
	{
		g.setFont(font);
		// g.drawString(message, startX, (int) -bounds.getY());
		for (BannerString st : appletStrings)
			g.drawString(st.getMessage(), st.getX(), st.getY());
	}

	/**
	 * Uses the Decider, Choice, and Rule classes to play a game of RPSLK
	 * between two BannerString objects.
	 * 
	 * @param st1
	 *           first BannerString object
	 * @param st2
	 *           second BannerString object
	 */
	public void playRPSLK(BannerString st1, BannerString st2)
	{
		Font statusFont = new Font(STATUS_MSG_FONTNAME, Font.BOLD, STATUS_MSG_FONTSIZE);
		g2.setFont(statusFont);

		// Gets the character representations of the BannerStrings to be called
		// by the Decider object and play RPSLK
		char st1Char = st1.getChar();
		System.out.println("st1Char: " + st1Char); // debug
		char st2Char = st2.getChar();
		System.out.println("st2Char: " + st2Char); // debug

		int winner = decider.getWinner(st1Char, st2Char);
		// System.out.println("Decider --- winner is: " +
		// decider.getWinner(st1Char, st2Char)); // debug

		if (winner == 1) // st1 wins, so st2 is destroyed
		{
			appletStrings.remove(st2);
			System.out.println("OBLITERATION! " + decider.getRule(st1Char, st2Char) + "."); // debug
			g2.drawString("OBLITERATION! " + decider.getRule(st1Char, st2Char) + ".", STATUS_MSG_X, STATUS_MSG_Y);
			try
			{
			Thread.sleep(PAUSE);
			} catch (InterruptedException e)
			{
			}
		}

		else if (winner == 2) // st2 wins, so st1 is destroyed
		{
			appletStrings.remove(st1);
			System.out.println("DESTRUCTION! " + decider.getRule(st2Char, st1Char) + "."); // debug
			g2.drawString("DESTRUCTION! " + decider.getRule(st2Char, st1Char) + ".", STATUS_MSG_X, STATUS_MSG_Y);
			try
			{
			Thread.sleep(PAUSE);
			} catch (InterruptedException e)
			{
			}
		}

		else // st1 and st2 are identical in type, destroys both of them
		{
			// appletStrings.remove(st1);
			// appletStrings.remove(st2);
			if (doublecollide == true)
			{
			g2.drawString("The two colliding objects (" + st1Char + " and " + st2Char + ") continue on unharmed...",
					STATUS_MSG_X, STATUS_MSG_Y);
			doublecollide = false;
			try
			{
				Thread.sleep(PAUSE);
			} catch (InterruptedException e)
			{
			}
			}
		}
	}

}
