package pl.ola.logicgate.parts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Logic gate sketch class.
 * 
 * @author Ola
 */
public class Sketch {

	/**
	 * Input list.
	 */
	private ArrayList<Input> mInputs;

	/**
	 * Collection of gates in a sketch.
	 */
	private ArrayList<Gate> mGates;

	/**
	 * List of outputs of the Sketch.
	 */
	private ArrayList<LogicElement> mOutputs;

	public Sketch(ArrayList<Input> inputs, ArrayList<Gate> gates,
			ArrayList<LogicElement> outputs) {
		this.mInputs = inputs;
		this.mGates = gates;
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
		if (vStateList.size() != mInputs.size())
			throw new IllegalArgumentException("Not compatible sizes.");
		// Initialisation
		HashMap<Gate, EState> result = new HashMap<Gate, EState>();
		boolean flag_countAgain;
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

		do {
			flag_countAgain = false;
			for (Map.Entry<Integer, Gate> gate : mGates.entrySet()) {

				// 1. Get gate inputs
				// 2. Get input values (if they are available)
				// 3. If there are values possesed from previous point,
				// calculate
				// gate

				Gate vGate = gate.getValue();
				ArrayList<EState> inputsStates = new ArrayList<EState>();
				for (LogicElement input : vGate.getInputs()) {
					EState state = input.getState();
					inputsStates.add(state);
					if (state == null) {
						flag_countAgain = true;
						continue;
					}
				}
				result.put(vGate, vGate.calculate(inputsStates));
			}
		} while (flag_countAgain == true);
		return result;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("Inputs no.: %s\n", mInputs.size()));
		for (Map.Entry<Integer, Gate> gateEntry : mGates.entrySet()) {
			sb.append(String.format("Gate %d: %s\n", gateEntry.getKey(),
					gateEntry.getValue().toString()));
		}
		sb.append("Outputs: ");
		for (LogicElement le : mOutputs) {
			sb.append(String.format("%s", le.getShortName()));
		}
		return sb.toString();
	}
}
