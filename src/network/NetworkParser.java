package network;

public abstract class NetworkParser {
	private static final Exception MethodMustBeOverridenException = null;
	
	public void parseInput(String in) throws Exception {
		throw MethodMustBeOverridenException;
	}
}
