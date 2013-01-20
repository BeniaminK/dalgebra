package pl.ola.logicgate.inputmanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import pl.ola.logicgate.parts.EOperations;
import pl.ola.logicgate.parts.Gate;
import pl.ola.logicgate.parts.Input;
import pl.ola.logicgate.parts.LogicElement;
import pl.ola.logicgate.parts.Sketch;

public class InputManager implements IInputManager {

	@Override
	public Sketch getSketch(File file) throws IOException {

		ArrayList<Input> inputs = new ArrayList<Input>();

		HashMap<Integer, Gate> gates = new HashMap<Integer, Gate>();

		ArrayList<LogicElement> outputs = new ArrayList<LogicElement>();

		for (String fileLine : FileUtils.readLines(file)) {
			String[] lineArgs = fileLine.split(" ");
			if (lineArgs[0].startsWith("G")) { // gate description
				int gateNumber = Integer.parseInt(lineArgs[0].replace("G", "")
						.replace(":", ""));
				String gateOperation = lineArgs[1];
				ArrayList<LogicElement> gateInput = new ArrayList<LogicElement>();

				for (int i = 2; i < lineArgs.length; ++i) {
					if (lineArgs[i].startsWith("x")) {
						int number = Integer.parseInt(lineArgs[i].replace("x",
								""));
						for (int j = inputs.size(); j <= number - 1; ++j) {
							inputs.add(new Input(j + 1));
						}
						gateInput.add(inputs.get(number - 1));
					} else if (lineArgs[i].startsWith("G")) {
						int number = Integer.parseInt(lineArgs[i].replace("G",
								""));
						gateInput.add(gates.get(number));
					}
				}
				gates.put(gateNumber,
						new Gate(gateNumber,
								EOperations.valueOf(gateOperation), gateInput));
			} else if (lineArgs[0].startsWith("O")) { // output description
				for (int i = 1; i < lineArgs.length; ++i) {
					if (lineArgs[i].startsWith("x")) {
						int number = Integer.parseInt(lineArgs[i].replace("x",
								""));
						outputs.add(inputs.get(number));
					} else if (lineArgs[i].startsWith("G")) {
						int number = Integer.parseInt(lineArgs[i].replace("G",
								""));
						outputs.add(gates.get(number));
					}
				}
				break;
			}
		}
		ArrayList<Gate> gateList = new ArrayList<Gate>();
		for (Map.Entry<Integer, Gate> entry : gates.entrySet()) {
			gateList.add(entry.getValue());
		}
		return new Sketch(inputs, gateList, outputs);
	}
}
