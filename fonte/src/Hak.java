/**
 * Interpretador Hak
 *
 * Inicia um novo HakFile contendo um arquivo específico
 * para se interpretador pelo programa.
 * 
 * Para executar, rode o seguinte comando no terminal:
 * java Hak ./teste.hak
 * 
 * Por Anderson Barberini <anderson.aeb@gmail.com>
 */

import java.io.IOException;
import file.HakFile;

import core.Interpreter;

public class Hak {

	public static void main(String[] args) throws IOException {

		if(args.length <= 0) {
			throw new IOException("Unspecified file.");
		}
		
		HakFile file = new HakFile(args[0]);
		
		Interpreter interpreter = new Interpreter(file.getLines());
		interpreter.analyze();
	}
}