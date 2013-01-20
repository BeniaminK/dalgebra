package pl.ola.logicgate.parts.impl;

import java.util.ArrayList;

import pl.ola.logicgate.parts.LogicGate;
import pl.ola.logicgate.parts.EState;

public class OR implements LogicGate {

	@Override
	public EState calculate(ArrayList<EState> inputs) {
		if (inputs.size() != this.getInputCount())
			throw new IllegalArgumentException(
					"List size mismatch the argument size of the gate.");
		assert (!inputs.contains(null));

		if (inputs.contains(EState.TRUE))
			return EState.TRUE;
		if (inputs.contains(EState.FALSE)) {
			if (inputs.get(0) == EState.FALSE)
				return inputs.get(1);
			else
				return inputs.get(1);
		}
		if (inputs.get(0) == inputs.get(1))
			return inputs.get(0);
		else
			return EState.TRUE;
	}

	@Override
	public int getInputCount() {
		return 2;
	}
}
