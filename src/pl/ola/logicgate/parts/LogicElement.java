package pl.ola.logicgate.parts;

public interface LogicElement {

	String getShortName();

	/**
	 * Returns current state of a Logic Element.
	 */
	EState getState();

	/**
	 * Sets state of a Logic Element.
	 */
	void setState(EState state);

}
