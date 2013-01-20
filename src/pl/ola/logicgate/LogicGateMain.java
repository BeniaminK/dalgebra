package pl.ola.logicgate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.ola.logicgate.inputmanager.IInputManager;
import pl.ola.logicgate.inputmanager.InputManager;
import pl.ola.logicgate.parts.EState;
import pl.ola.logicgate.parts.Gate;
import pl.ola.logicgate.parts.Sketch;

public class LogicGateMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filepath = "res/sketch1.txt";
		File file = new File(filepath);
		IInputManager im = new InputManager();

		Sketch sketch;
		try {
			sketch = im.getSketch(file);
			System.out.println(sketch.toString());

			for (int x1 = 0; x1 < 2; x1++) {
				Boolean xb1 = x1 == 0 ? Boolean.TRUE : Boolean.FALSE;
				for (int x2 = 0; x2 < 2; x2++) {
					Boolean xb2 = x2 == 0 ? Boolean.TRUE : Boolean.FALSE;
					for (int x3 = 0; x3 < 2; x3++) {
						Boolean xb3 = x3 == 0 ? Boolean.TRUE : Boolean.FALSE;

						ArrayList<Boolean> inputs = new ArrayList<Boolean>(3);
						inputs.add(xb1);
						inputs.add(xb2);
						inputs.add(xb3);
						HashMap<Gate, EState> throughStates = sketch
								.goThrough(inputs);
						StringBuffer sb = new StringBuffer();
						for (Boolean element : inputs) {
							int val = element ? 1 : 0;
							sb.append(val);
							sb.append(" ");
						}
						System.out.println(sb.toString());
						for (Map.Entry<Gate, EState> entry : throughStates
								.entrySet()) {
							System.out.println(entry.getKey().getShortName()
									+ " " + entry.getValue().toString());
						}

					}
				}
			}
		} catch (IOException e1) {
			System.err.println("Couldn't parse " + file.getAbsolutePath()
					+ " as a sketch.");
			System.exit(1);
		}
	}
}
