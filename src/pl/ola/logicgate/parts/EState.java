package pl.ola.logicgate.parts;

public enum EState {
	FALSE, TRUE, D, NOT_D;

	public static EState fromInteger(int x) {
		switch (x) {
		case 0:
			return FALSE;
		case 1:
			return TRUE;
		}
		throw new IllegalArgumentException(
				"Only 0 or 1 available on a casting EState.");
	}
}
