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
		String out = "";
		Matcher mValue;

		/*
		 * Verifica se é uma String
		 */

		mValue = Pattern.compile("\"(.*)\"").matcher(value);
		if (mValue.find()) {
			out = mValue.group(1);
			PrintToken.out(out, newLine);
			return;
		}

		mValue = Pattern.compile("^[$]([a-zA-Z_0-9]\\w*)[\\s]{0,}").matcher(value);
		if (mValue.find()) {
			Variable var = Variables.getInstance().find(mValue.group(1));
			if (var != null) {
				out = var.getValue();
				PrintToken.out(out, newLine);
				return;
			} else {
			
				HakException.VariableNotFound(mValue.group(1));
			}
		}

		System.out.print(out);

	}

	public static void out(String out, boolean newLine) {
		if (newLine) {
			System.out.println(out);
		} else {
			System.out.print(out);
		}
	}

	public static core.Variable parseVariable(String name, String value) {
		core.Variable var = new core.Variable();
		var.setName(name);

		return var;
	}
}
