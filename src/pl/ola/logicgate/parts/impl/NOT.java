package pl.ola.logicgate.parts.impl;

import java.util.ArrayList;

import pl.ola.logicgate.parts.LogicGate;
import pl.ola.logicgate.parts.EState;

public class NOT implements LogicGate {

	@Override
	public EState calculate(ArrayList<EState> inputs) {
		if (inputs.size() != this.getInputCount())
			throw new IllegalArgumentException(
					"List size mismatch the argument size of the gate.");
		assert (!inputs.contains(null));
		
		if (inputs.get(0) == EState.D)
			return EState.NOT_D;
		if (inputs.get(0) == EState.FALSE)
			return EState.TRUE;
		if (inputs.get(0) == EState.TRUE)
			return EState.FALSE;
		if (inputs.get(0) == EState.NOT_D)
			return EState.D;
		throw new IllegalArgumentException("State other than expected.");
	}

	@Override
	public int getInputCount() {
		return 1;
	}
}
