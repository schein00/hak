package token;

import java.util.regex.Matcher;

import variable.VariableTypes;
import core.Variable;

public class MathToken {

	public static Variable getVariableResult(String a, String op, String b) {
		
		Variable var = new Variable();

		Variable auxA = VariableToken.parseVariable("auxA", a);
		Variable auxB = VariableToken.parseVariable("auxB", b);
		
		if (auxA != null && auxB != null) {
			
			String type = VariableTypes.INT;
			
			if (auxA.getType() == VariableTypes.DOUBLE || auxB.getType() == VariableTypes.DOUBLE) {
				type = VariableTypes.DOUBLE;
			}

			switch (op) {
				case "+":
					if(type == VariableTypes.DOUBLE) {
						var.setValue(String.valueOf(auxA.getDoubleValue() + auxB.getDoubleValue()));
					} else {
						var.setValue(String.valueOf(auxA.getIntValue() + auxB.getIntValue()));
					}
					
					var.setType(type);
					break;
				case "-":
					if(type == VariableTypes.DOUBLE) {
						var.setValue(String.valueOf(auxA.getDoubleValue() - auxB.getDoubleValue()));
					} else {
						var.setValue(String.valueOf(auxA.getIntValue() - auxB.getIntValue()));
					}
					
					var.setType(type);
					break;
				case "*":
					if(type == VariableTypes.DOUBLE) {
						var.setValue(String.valueOf(auxA.getDoubleValue() * auxB.getDoubleValue()));
					} else {
						var.setValue(String.valueOf(auxA.getIntValue() * auxB.getIntValue()));
					}
					
					var.setType(type);
					break;
				case "/":
					if(type == VariableTypes.DOUBLE) {
						var.setValue(String.valueOf(auxA.getDoubleValue() / auxB.getDoubleValue()));
					} else {
						var.setValue(String.valueOf(auxA.getIntValue() / auxB.getIntValue()));
					}
					
					var.setType(type);
					break;
				case "%":
					if(type == VariableTypes.DOUBLE) {
						var.setValue(String.valueOf(auxA.getDoubleValue() % auxB.getDoubleValue()));
					} else {
						var.setValue(String.valueOf(auxA.getIntValue() % auxB.getIntValue()));
					}
					
					var.setType(type);
					break;
				case "<":
					var.setValue((auxA.getDoubleValue() < auxB.getDoubleValue()) ? "true" : "false");
					break;
				case ">":
					var.setValue((auxA.getDoubleValue() > auxB.getDoubleValue()) ? "true" : "false");
					break;
				case "<=":
					var.setValue((auxA.getDoubleValue() <= auxB.getDoubleValue()) ? "true" : "false");
					break;
				case ">=":
					var.setValue((auxA.getDoubleValue() >= auxB.getDoubleValue()) ? "true" : "false");
					break;
				case "!-":
					var.setValue((auxA.getDoubleValue() != auxB.getDoubleValue()) ? "true" : "false");
					break;
			}

			var.setType(type);
			return var;
			
		} else {
			return null;
		}
	}
}
