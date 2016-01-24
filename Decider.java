/**
 * 
 * Decider.java 
 * 
 * The Decider class is used to construct and store the rules of the game as an
 * array. The rules for the game are created by the constructor when an instance
 * of Decider object is created. It does not have a mutator method because rules
 * are created when an instance of the object is created and the rules of this
 * game are not negotiable.
 *
 * Rounds are compared to given Rules within a ruleArray of a Decider object to
 * determine who wins, who loses, and how the winner beat the loser in a given
 * round.
 *
 * @author vsub21
 *
 */
public class Decider
{

	private Rule[] ruleArray = new Rule[10];

	/**
	 * Creates a list of rules as defined by the game RPSLS. Used as a way to
	 * compare the throws of a match to determine the outcome of a match.
	 */
	public Decider()
	{
		ruleArray[0] = new Rule(Choice.Scissors, Verb.cuts, Choice.Paper);
		ruleArray[1] = new Rule(Choice.Paper, Verb.covers, Choice.Rock);
		ruleArray[2] = new Rule(Choice.Rock, Verb.crushes, Choice.Lizard);
		ruleArray[3] = new Rule(Choice.Lizard, Verb.poisons, Choice.Spock);
		ruleArray[4] = new Rule(Choice.Spock, Verb.smashes, Choice.Scissors);
		ruleArray[5] = new Rule(Choice.Scissors, Verb.decapitates, Choice.Lizard);
		ruleArray[6] = new Rule(Choice.Lizard, Verb.eats, Choice.Paper);
		ruleArray[7] = new Rule(Choice.Paper, Verb.disproves, Choice.Spock);
		ruleArray[8] = new Rule(Choice.Spock, Verb.vaporizes, Choice.Rock);
		ruleArray[9] = new Rule(Choice.Rock, Verb.crushes, Choice.Scissors);
	}

	/**
	 * Returns the constructed ruleArray when it is needed for comparison.
	 *
	 * @return ruleArray the array containing the rules of how the winner is
	 *         determined
	 */
	public Rule[] getRuleArray()
	{
		return ruleArray;
	}

	/**
	 * Sets the value of rulearray
	 * @param Rule[] an array consisting of rules
	 * @return
	 */

	public void setRuleArray(Rule[] array)
	{
		ruleArray = array;
	}

	/**
	 * Matches the proper rule with the given winner and loser and returns a
	 * String containing a sentence with the proper action taken by the winner
	 * to beat the loser.
	 *
	 * @param cChoice the choice made by the computer
	 * @param uChoice the choice made by the user
	 * @return statement a statement indicating the winner, loser, and the method in which
	 * the winner beat the loser
	 */
	public String getRule(char cChoice, char uChoice)
	{

		for (int i = 0; i < ruleArray.length; i++)
		{
			if (ruleArray[i].getWinner().getChar() == cChoice)
			{
				if (ruleArray[i].getLoser().getChar() == uChoice)
				{
					return (ruleArray[i].getWinner().name() + " " + ruleArray[i].getVerb().name() + " " + ruleArray[i].getLoser()
							.name());
				}

			}
		}
		return ("");
	}

	/**
	 * Determines the winner of a given round based on the computer and user choices by comparing them
	 * to the rules specified in the ruleArray. Returns a 0 if there is a tie, a 1 if computer wins,
	 * and a 2 if the user wins.
	 *
	 * @param cChoice the choice made by the computer
	 * @param uChoice the choice made by the user
	 * @return
	 */
	public int getWinner(char cChoice, char uChoice)
	{
		if (cChoice == uChoice)
		{
			System.out.println("getWinner method: first return 0, cChoice == uChoice"); // debug
			return 0; //0 is a tie
		}

		for (int i = 0; i < ruleArray.length; i++)
		{

			if (ruleArray[i].getWinner().getChar() == cChoice)
			{
				if (ruleArray[i].getLoser().getChar() == uChoice)
				{
					return 1;
				}
			}
			else if (ruleArray[i].getWinner().getChar() == uChoice)
			{
				if (ruleArray[i].getLoser().getChar() == cChoice)
				{
					return 2;
				}
			}
		}
		System.out.println("getWinner method: last return 0"); // debug
		return 0;
	}


	/**
	 * String representation of the Decider object for a given rule.
	 * @param rule the rule containing a winner, loser, and verb
	 * @return a String containing a sentence indicating how the winner beats the loser
	 */
	public String toString(Rule rule)
	{
		return (rule.getWinner().name() + " " + rule.getVerb().name() + " " + rule.getLoser().name());
	}
}
