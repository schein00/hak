package token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.HakException;
import core.Variable;
import store.Variables;
import variable.VariableTypes;

public class VariableToken {

	public static void apply(Matcher matcher) {

		String name = matcher.group(1).trim();
		String value = matcher.group(2).trim();

		Variable var = VariableToken.parseVariable(name, value);

		if (var != null) {
			Variables.getInstance().add(var);
		}

	}

	public static Variable parseVariable(String name, String value) {
		Variable var = new Variable();
		var.setName(name);

		Matcher mValue;

		/*
		 * Valor da variavel e outra variavel
		 */
		mValue = Pattern.compile("^[\\s]{0,}[$]([a-zA-Z_0-9]\\w*)[\\s]{0,}$").matcher(value);
		if (mValue.find()) {
			Variable aux = Variables.getInstance().find(mValue.group(1));
			if (aux != null) {
				var.setType(aux.getType());
				var.setValue(aux.getValue());
				return var;
			} else {
				HakException.VariableNotFound(mValue.group(1));
			}
		}

		/*
		 * Variavel tipo string
		 */

		mValue = Pattern.compile("^[\\s]{0,}\"(.*)\"[\\s]{0,}$").matcher(value);
		if (mValue.find()) {
			var.setType(VariableTypes.STRING);
			var.setValue(mValue.group(1));
			return var;
		}

		/*
		 * Variavel tipo int
		 */

		mValue = Pattern.compile("[\\s]{0,}(^(\\+|\\-)?[\\s]{0,}([0-9]*))[\\s]{0,}$").matcher(value);
		if (mValue.find()) {
			var.setType(VariableTypes.INT);
			var.setValue(mValue.group(1));
			return var;
		}

		/*
		 * Variavel tipo double
		 */

		mValue = Pattern.compile("[\\s]{0,}(^(\\+|\\-)?[\\s]{0,}([0-9]*)(\\.[0-9]*))[\\s]{0,}$").matcher(value);
		if (mValue.find()) {
			var.setType(VariableTypes.DOUBLE);
			var.setValue(mValue.group(1));
			return var;
		}
		
		/*
		 * Booleano
		 */
		 
		mValue = Pattern.compile("^[\\s]{0,}(true|false)[\\s]{0,}$").matcher(value);
		if (mValue.find()) {
			var.setType(VariableTypes.STRING);
			var.setValue(mValue.group(1));
			return var;
		}

		/*
		 * Expressao logicas e matematicas (a [ + - * / % < > <= >= != ==] b)
		 */

		mValue = Pattern.compile("(.*)[\\s]{0,}(\\+|\\-|\\*|\\/|%|>=|<=|>|<|!=|==)[\\s]{0,}(.*)").matcher(value);
		if (mValue.find()) {
			String a, op, b;
			a = mValue.group(1);
			op = mValue.group(2);
			b = mValue.group(3);

			Variable res = MathToken.getVariableResult(a, op, b);
			if (res != null) {
				var.setType(res.getType());
				var.setValue(res.getValue());
				return var;
			}
		}

		return null;
	}
}
