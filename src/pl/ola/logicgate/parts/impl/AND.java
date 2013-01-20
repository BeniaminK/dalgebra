package pl.ola.logicgate.parts.impl;

import java.util.ArrayList;

import pl.ola.logicgate.parts.LogicGate;
import pl.ola.logicgate.parts.EState;

public class AND implements LogicGate {

	@Override
	public EState calculate(ArrayList<EState> inputs) {
		if (inputs.size() != this.getInputCount())
			throw new IllegalArgumentException(
					"List size mismatch the argument size of the gate.");
		if (inputs.contains(EState.FALSE))
			return EState.FALSE;
		if (inputs.contains(EState.D))
			return EState.D;
		if (inputs.contains(EState.NOT_D))
			return EState.NOT_D;
		if (inputs.contains(EState.TRUE))
			return EState.TRUE;
		throw new IllegalArgumentException("State other than expected.");
	}

	@Override
	public int getInputCount() {
		return 2;
	}
}
