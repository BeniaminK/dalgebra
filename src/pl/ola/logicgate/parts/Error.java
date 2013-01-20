package pl.ola.logicgate.parts;

import java.util.ArrayList;

public class Error extends Gate {

	public Error(int number, EOperations operation,
			ArrayList<LogicElement> inputs) {
		super(number, operation, inputs);
	}

}
