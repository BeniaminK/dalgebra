package pl.ola.logicgate.parts;

import java.util.ArrayList;

public interface LogicGate {

	public EState calculate(ArrayList<EState> inputs);

	/**
	 * Return number of inputs of a given GATE.
	 * @return number of inputs.
	 */
	public int getInputCount();
}
