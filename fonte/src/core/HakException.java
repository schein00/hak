package core;

import java.io.IOException;

public class HakException {

	public static void VariableNotFound(String variableName) {
		HakException.error("Variable '" + variableName + "' has not been created.");
	}

	public static void error(String out) {
		System.out.println("*** ERROR");
		System.out.println("    " + out);
		System.exit(1);
	}
}
