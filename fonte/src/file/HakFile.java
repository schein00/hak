
/**
 * Interpretador Hak
 *
 * Lê o arquivo a ser interpretado e guarda em um
 * ArrayList.
 * 
 * Por Anderson Barberini <anderson.aeb@gmail.com>
 */

package file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HakFile {

	private ArrayList<String> lines;

	public HakFile(String file) throws IOException {

		File f;
		Scanner s;

		this.lines = new ArrayList<String>();

		try {

			f = new File(file);

			s = new Scanner(f);

			while (s.hasNext()) {
				this.lines.add(s.nextLine());
			}
			
			s.close();

		} catch (Exception e) {
			throw new IOException("File '" + file + "' not found.");
		}
	}

	/**
	 * @return {@link ArrayList} Linhas de código do arquivo
	 */
	public ArrayList<String> getLines() {
		return this.lines;
	}
}
