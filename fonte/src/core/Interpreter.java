/**
 * Interpretador Hak
 *
 * Responsavel por..
 * 
 * Por Anderson Barberini <anderson.aeb@gmail.com>
 */

package core;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import token.Command;
import token.PrintToken;
import token.ScanToken;
import token.VariableToken;

public class Interpreter {

	private ArrayList<String> lines;
	
	private int indexLine;

	public Interpreter(ArrayList<String> lines) {
		
		this.lines = lines;
	}

	public void analyze() {
		Command command = new Command();
		Matcher matcher;

		for (this.indexLine = 0; this.indexLine < this.lines.size(); this.indexLine++) {

			String line = this.lines.get(this.indexLine);
			
			if(line.trim().length() == 0)
				continue;
			
			/*
			 * Comentario
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
			 * Variavel
			 */

			matcher = command.variable(line);
			if (matcher.find()) {
				VariableToken.apply(matcher);
				continue;
			}
			
			/*
			 * Entrada de dados
			 */
			
			matcher = command.scan(line);
			if(matcher.find()) {
				ScanToken.apply(matcher);
				continue;
			}  

			/*
			 * Condicao
			 */

			matcher = command.condition(line);
			if (matcher.find()) {

				// Avanca para a proxima linha
				this.indexLine++;
				
				Variable var = VariableToken.parseVariable("condition", matcher.group(1).trim());	
				boolean addToBlock = (var.isTrue()) ? true : false;
				ArrayList<String> block = this.getSubBlock(addToBlock);
				
				if (block.size() > 0) {
					Interpreter interpreter = new Interpreter(block);
					interpreter.analyze();
				}
				
				continue;
			}
			
			/*
			 * Laco
			 */
			 
			matcher = command.loop(line);
			if (matcher.find()) {

				// Avanca para a proxima linha
				this.indexLine++;

				Variable var = VariableToken.parseVariable("condition", matcher.group(1).trim());
				boolean addToBlock = (var.isTrue()) ? true : false;
				
				ArrayList<String> block = this.getSubBlock(addToBlock);
				
				if (block.size() > 0) {
					while(var.isTrue()) {
						Interpreter interpreter = new Interpreter(block);
						interpreter.analyze();
						
						var = VariableToken.parseVariable("condition", matcher.group(1).trim());
						addToBlock = (var.isTrue()) ? true : false;
					}
				}
				continue;
			}
		}
	}

	public ArrayList<String> getSubBlock(boolean addToBlock) {
	
		// Controle de paenteses
		Stack<String> p = new Stack<String>();
		p.push("{");

		ArrayList<String> block = new ArrayList<String>();

		while (!p.empty() && this.indexLine < this.lines.size()) {

			/*
			 * Fluxo de Chaves ({,});
			 */
			 
			Matcher matcher = Pattern.compile("^[\\s]{0,}(if|while)(.*)\\{$").matcher(this.lines.get(this.indexLine));
			if (matcher.find()) {
				p.push("{");
			} else if (this.lines.get(this.indexLine).trim().equals("}")) {
				p.pop();
			}

			/*
			 * Busca pelo else
			 */
			 
			matcher = Pattern.compile("^[\\s]{0,}\\}[\\s]{0,}else[\\s]{0,}\\{[\\s]{0,}$").matcher(this.lines.get(this.indexLine));
			if (p.size() == 1 && matcher.find()) {
				if (!addToBlock) {
					addToBlock = true;
					this.indexLine++;
				} else {
					addToBlock = false;
				}
			}

			/*
			 * Controle blocos
			 */

			if (!p.empty() && addToBlock) {
				block.add(this.lines.get(this.indexLine));
			}

			if(!p.empty()) {
				this.indexLine++;
			}
		}
		
		return block;
	}
}
