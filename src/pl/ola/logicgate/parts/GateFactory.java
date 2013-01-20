package pl.ola.logicgate.parts;

import pl.ola.logicgate.parts.impl.AND;
import pl.ola.logicgate.parts.impl.NOT;
import pl.ola.logicgate.parts.impl.OR;

public class GateFactory {

	static public LogicGate get(EOperations vOperations) {
		switch (vOperations) {
		case AND:
			return new AND();
		case NOT:
			return new NOT();
		case OR:
			return new OR();
		case NAND:
		case SA1:
		case SA2:
		default:
			throw new IllegalStateException(
					"Not every operation supported in GateFactory: "
							+ vOperations.toString());
		}
	}
}
