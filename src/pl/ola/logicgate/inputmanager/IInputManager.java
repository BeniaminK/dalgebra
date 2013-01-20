package pl.ola.logicgate.inputmanager;

import java.io.File;
import java.io.IOException;

import pl.ola.logicgate.parts.Sketch;

public interface IInputManager {

	Sketch getSketch(File file) throws IOException;

}
