package pl.ola.logicgate.parts;

import java.util.ArrayList;

public class Gate implements LogicElement {

	/**
	 * State of an input (H/L etc.)
	 */
	private EState mTempState = null;
	/**
	 * List of references that input to the current gate with their numbers.
	 */
	private ArrayList<LogicElement> mInputs;

	/**
	 * ID of this gate.
	 */
	private int mNumber;

	/**
	 * {@link EOperations} that is enabled by the current gate.
	 */
	private EOperations mOperation;

	/**
	 * Return type of the Gate.
	 * 
	 * @return Return {@link EOperations} supported by the current Gate.
	 */
	public String getType() {
		return mOperation.toString();
	}

	/**
	 * Returns list of input elements connected with a given gate.
	 * 
	 * @return List of inputs.
	 */
	public final ArrayList<LogicElement> getInputs() {
		return mInputs;
	}

	/**
	 * Public constructor of gate class.
	 * 
	 * @param number
	 *            ID number of the current gate.
	 * @param operation
	 *            Logic Operation supported by the current gate.
	 * @param inputs
	 *            List of input LogicElements.
	 */
	public Gate(int number, EOperations operation,
			ArrayList<LogicElement> inputs) {
		this.mNumber = number;
		this.mOperation = operation;
		this.mInputs = inputs;

	}

	public int getNumber() {
		return mNumber;
	}

	public EState calculate(ArrayList<EState> vStates) {
		return GateFactory.get(mOperation).calculate(vStates);
	}

	@Override
	public String getShortName() {
		return "G" + Integer.toString(mNumber);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (LogicElement logicElement : mInputs) {
			sb.append(logicElement.getShortName());
			sb.append(" ");
		}
		return this.getShortName() + "-" + mOperation.toString() + ":"
				+ sb.toString();
	}

	@Override
	public EState getState() {
		return mTempState;
	}

	@Override
	public void setState(EState state) {
		mTempState = state;
	}

}
