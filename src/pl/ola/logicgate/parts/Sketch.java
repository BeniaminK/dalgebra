package pl.ola.logicgate.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Logic gate sketch class.
 * 
 * @author Ola
 */
public class Sketch {

	/**
	 * Input list.
	 */
	private final ArrayList<Input> mInputs;

	/**
	 * Collection of gates in a sketch.
	 */
	private final ArrayList<Gate> mGates;

	/**
	 * List of outputs of the Sketch.
	 */
	private final ArrayList<LogicElement> mOutputs;

	/**
	 * Creates new Sketch.
	 * 
	 * @param inputs
	 *            List of inputs (may be not sorted).
	 * @param gates
	 *            List of gates (may be not sorted).
	 * @param outputs
	 *            List of outputs (order doesn't matter).
	 */
	public Sketch(ArrayList<Input> inputs, ArrayList<Gate> gates,
			ArrayList<LogicElement> outputs) {
		this.mInputs = inputs;
		Collections.sort(this.mInputs);
		this.mGates = gates;
		Collections.sort(this.mGates);
		this.mOutputs = outputs;

	}

	/**
	 * Counts number of inputs in Sketch.
	 * 
	 * @return Number of inputs in Sketch.
	 */
	public int getInputCount() {
		return mInputs.size();
	}

	/**
	 * Counts which gates are the last ones (there is no input in other gate
	 * from this gate)
	 * 
	 * @return Gates that are the sketch outputs.
	 */
	public final ArrayList<LogicElement> getLastGates() {
		return mOutputs;
	}

	/**
	 * 
	 */
	public HashMap<Gate, EState> goThrough(ArrayList<Boolean> vStateList) {
		// Assertions
		assert (vStateList.size() == mInputs.size());
		// Set inputs to a given state.
		for (int i = 0; i < vStateList.size(); ++i) {
			Boolean state = vStateList.get(i);
			EState vState;
			if (state == Boolean.TRUE) {
				vState = EState.TRUE;
			} else {
				vState = EState.FALSE;
			}
			mInputs.get(i).setState(vState);
		}
		// Initialisation
		HashMap<Gate, EState> result = new HashMap<Gate, EState>();
		ArrayList<Gate> gatesUnresolved = new ArrayList<Gate>(mGates);

		// do {
		for (Iterator<Gate> vIterator = gatesUnresolved.iterator(); vIterator
				.hasNext();) {
			Gate vGate = (Gate) vIterator.next();

			boolean corruptedGate = false;

			// 1. Get gate inputs
			// 2. Get input values (if they are available)
			// 3. If there are values possesed from previous point,
			// calculate
			// gate

			ArrayList<EState> inputsStates = new ArrayList<EState>();
			for (LogicElement input : vGate.getInputs()) {
				EState state = input.getState();
				if (state == null) {
					corruptedGate = true;
					break;
				}
				inputsStates.add(state);

			}
			if (!corruptedGate) {
				EState gateState = vGate.calculate(inputsStates);
				result.put(vGate, gateState);
				vGate.setState(gateState);
				vIterator.remove();
			}
		}
		// System.out.println(gatesUnresolved.size());
		// } while (gatesUnresolved.size() > 0);
		return result;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("Inputs no.: %s\n", mInputs.size()));
		for (Gate gate : mGates) {
			sb.append(String.format("Gate %d: %s\n", gate.getNumber(),
					gate.toString()));
		}
		sb.append("Outputs: ");
		for (LogicElement le : mOutputs) {
			sb.append(String.format("%s", le.getShortName()));
		}
		return sb.toString();
	}
}
