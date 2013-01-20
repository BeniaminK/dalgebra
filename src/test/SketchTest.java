package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.ola.logicgate.inputmanager.IInputManager;
import pl.ola.logicgate.inputmanager.InputManager;
import pl.ola.logicgate.parts.EState;
import pl.ola.logicgate.parts.Gate;
import pl.ola.logicgate.parts.Sketch;

@RunWith(JUnit4.class)
public class SketchTest {

	@Test
	@Category(pl.ola.logicgate.parts.Sketch.class)
	public void testSketch1() throws IOException {
		String filepath = "res/sketch1.txt";
		File file = new File(filepath);
		IInputManager im = new InputManager();

		Sketch sketch = im.getSketch(file);
		System.out.println(sketch.toString());

		for (int x1 = 0; x1 < 2; x1++) {
			Boolean xb1 = x1 == 0 ? Boolean.FALSE : Boolean.TRUE;
			for (int x2 = 0; x2 < 2; x2++) {
				Boolean xb2 = x2 == 0 ? Boolean.FALSE : Boolean.TRUE;
				for (int x3 = 0; x3 < 2; x3++) {
					Boolean xb3 = x3 == 0 ? Boolean.FALSE : Boolean.TRUE;

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
						System.out.println(entry.getKey().getShortName() + " "
								+ entry.getValue().toString());
					}

					// TESTING
					int bin_input[] = { x1, x2, x3 };
					EState output[] = new EState[3];
					for (Map.Entry<Gate, EState> entry : throughStates
							.entrySet()) {
						output[entry.getKey().getNumber() - 1] = entry
								.getValue();
					}
					EState outputREAL[] = new EState[3];

					if (Arrays.equals(bin_input, new int[] { 0, 0, 0 })) {
						outputREAL[0] = EState.fromInteger(1);
						outputREAL[1] = EState.fromInteger(0);
						outputREAL[2] = EState.fromInteger(0);
					} else if (Arrays.equals(bin_input, new int[] { 0, 0, 1 })) {
						outputREAL[0] = EState.fromInteger(1);
						outputREAL[1] = EState.fromInteger(0);
						outputREAL[2] = EState.fromInteger(1);
					} else if (Arrays.equals(bin_input, new int[] { 0, 1, 0 })) {
						outputREAL[0] = EState.fromInteger(0);
						outputREAL[1] = EState.fromInteger(0);
						outputREAL[2] = EState.fromInteger(0);
					} else if (Arrays.equals(bin_input, new int[] { 0, 1, 1 })) {
						outputREAL[0] = EState.fromInteger(0);
						outputREAL[1] = EState.fromInteger(0);
						outputREAL[2] = EState.fromInteger(1);
					} else if (Arrays.equals(bin_input, new int[] { 1, 0, 0 })) {
						outputREAL[0] = EState.fromInteger(1);
						outputREAL[1] = EState.fromInteger(1);
						outputREAL[2] = EState.fromInteger(1);
					} else if (Arrays.equals(bin_input, new int[] { 1, 0, 1 })) {
						outputREAL[0] = EState.fromInteger(1);
						outputREAL[1] = EState.fromInteger(1);
						outputREAL[2] = EState.fromInteger(1);
					} else if (Arrays.equals(bin_input, new int[] { 1, 1, 0 })) {
						outputREAL[0] = EState.fromInteger(0);
						outputREAL[1] = EState.fromInteger(0);
						outputREAL[2] = EState.fromInteger(0);
					} else if (Arrays.equals(bin_input, new int[] { 1, 1, 1 })) {
						outputREAL[0] = EState.fromInteger(0);
						outputREAL[1] = EState.fromInteger(0);
						outputREAL[2] = EState.fromInteger(1);
					}
					Assert.assertArrayEquals(null, output, outputREAL);
				}
			}
		}

	}
}
