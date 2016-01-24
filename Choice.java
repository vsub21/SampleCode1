/**
 *
 * Choice.java 
 *
 * This is an enumeration of the text for each throw in the game and the associated allowed user entry. No setter method
 * for enumerated values is implemented as it should not be accessible to user or reset in any way.
 *
 * @author vsub21
 *
 */
public enum Choice
{
	Rock('R'), Paper('P'), Scissors('S'), Lizard('L'), Spock('K');

	private char ch;

	private Choice(char inChoice)
	{
		ch = inChoice;
	}

	/**
	 * Returns the name associated with the given enumerated type.
	 * @param c an enumerated type in Choice
	 * @return the name associated with the enumerated type
	 */

	public String getValue(char c)
	{
		return this.name();
	}

	/**
	 * Returns the enumerated value equivalent for the given throw.
	 * @return ch the enumerated value for the throw
	 */
	public char getChar()
	{
		return ch;
	}

}
