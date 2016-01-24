/**
 * 
 * Rule.java 
 * 
 * Contains the sentence structure for a single rule to be printed to the user.
 * The constructor is used to assign the corresponding parameter values
 * to the attributes of the class.
 *
 * @author vsub21
 *
 */
public class Rule
{

	private Choice winner;
	private Choice loser;
	private Verb verb;

	/**
	 * Constructor for setting up the winner, the loser, and the verb (or method
	 * used by winner to beat loser) for a given round.
	 *
	 * @param inWinner
	 * @param inVerb
	 * @param inLoser
	 */
	public Rule(Choice inWinner, Verb inVerb, Choice inLoser)
	{
		winner = inWinner;
		loser = inLoser;
		verb = inVerb;
	}

	/**
	 * Returns the winner for a given round.
	 *
	 * @return winner the winning choice
	 */
	public Choice getWinner()
	{
		return winner;
	}

	/**
	 * Returns the loser for a given rule.
	 *
	 * @return loser the losing choice
	 */
	public Choice getLoser()
	{
		return loser;
	}


	/**
	 * Returns the verb associated with the way the winner beats the loser. Has private accessibility as it cannot be
	 * changed from the outside
	 *
	 * @return verb way in which winner beats loser
	 */
	public Verb getVerb()
	{
		return verb;
	}

}
