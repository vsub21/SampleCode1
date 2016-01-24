/**
 * Verb.java
 *
 * This is an enumeration of the text for all valid actions available. Note that every action is not applicable to each
 * throw. It is used for building the set of valid rules. No setter methods for enum
 *
 * @author vsub21
 *
 */
public enum Verb
{
   	cuts ("cuts"),
   	covers ("covers"),
   	crushes ("crushes"),
   	decapitates ("decapitates"),
   	disproves("disproves"),
   	eats("eats"),
   	poisons("poisons"),
   	smashes ("smashes"),
   	vaporizes ("vaporizes");
	
	private String action;

	/**
	 * Constructor for a Verb object, stores an action as an instance variable.
	 *
	 * @param inAction a given action
	 */
	private Verb(String inAction)
	{
		this.action = inAction;
	}

	/**
	 * Returns the enumerated value equivalent for the given throw.
	 * @return action the enumerated value for the throw
	 */
	public String getAction()
	{
		return action;
	}

}
