package pl.ola.logicgate.parts;

public class Input implements LogicElement {

	/**
	 * Input number.
	 */
	private int mNumber;
	/**
	 * State of an input (H/L etc.)
	 */
	private EState mTempState = null;

	public Input(int number) {
		this.mNumber = number;
	}

	@Override
	public String getShortName() {
		return "IN" + Integer.toString(mNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Input) {
			if (((Input) obj).getShortName().equals(this.getShortName()))
				return true;
			else
				return false;
		} else
			return super.equals(obj);
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
