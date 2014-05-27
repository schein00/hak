package token;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.HakException;
import core.Variable;
import store.Variables;
import variable.VariableTypes;

public class ScanToken {

	public static void apply(Matcher matcher) {

		Scanner s = new Scanner(System.in);
		String value = s.nextLine();

		Variable var = new Variable();
		var.setName(matcher.group(1));
		var.setValue(value);
		var.setType(VariableTypes.STRING);
		
		Variables.getInstance().add(var);
	}
}
