/**
 * Interpretador Hak
 *
 * Responsável por..
 * 
 * Por Anderson Barberini <anderson.aeb@gmail.com>
 */

package core;

import java.util.ArrayList;
import java.util.regex.Matcher;

import token.Command;
import token.PrintToken;
import token.VariableToken;

public class Interpreter {

	private ArrayList<String> lines;

	public Interpreter(ArrayList<String> lines) {
		this.lines = lines;
	}

	public void analyze() {
		Command command = new Command();
		Matcher matcher;

		for (int i = 0; i < this.lines.size(); i++) {
			String line = this.lines.get(i);
			/*
			 * Comentário
			 */

			matcher = command.comment(line);
			if (matcher.find()) {

				continue;
			}

			/*
			 * Print
			 */

			matcher = command.print(line);
			if (matcher.find()) {
				PrintToken.apply(matcher);
				continue;
			}

			/*
			 * Variável
			 */

			matcher = command.variable(line);
			if (matcher.find()) {
				VariableToken.apply(matcher);
				continue;
			}

		}
	}
}
