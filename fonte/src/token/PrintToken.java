package token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.HakException;
import core.Variable;
import store.Variables;

public class PrintToken {

	public static void apply(Matcher matcher) {

		boolean newLine = (matcher.group(1) != null) ? true : false;

		String value = matcher.group(2).trim();
		Variable var = VariableToken.parseVariable("print", value);
		PrintToken.out(var.getValue(), newLine);

	}

	public static void out(String out, boolean newLine) {
		if (newLine) {
			System.out.println(out);
		} else {
			System.out.print(out);
		}
	}
}
