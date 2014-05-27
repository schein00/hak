package token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {

	public Matcher comment(String line)
	{
		String regex = "^[\\s]{0,}(\\/\\/)[\\s]{0,}";
		return Pattern.compile(regex).matcher(line);
	}
	
	public Matcher variable(String line)
	{
		String regex = "^[\\s]{0,}[$]([a-zA-Z_0-9]\\w*)[\\s]{0,}[=](.*)[;][\\s]{0,}$";
		return Pattern.compile(regex).matcher(line);
	}
	
	public Matcher print(String line)
	{
		String regex = "^[\\s]{0,}print(ln)?[\\s]{0,}(.*)[;][\\s]{0,}$";
		return Pattern.compile(regex).matcher(line);
	}
	
	public Matcher condition(String line)
	{
		String regex = "^[\\s]{0,}if[\\s]{0,}\\((.*)\\)[\\s]{0,}\\{[\\s]{0,}$";
		return Pattern.compile(regex).matcher(line);
	}
	
	public Matcher loop(String line)
	{
		String regex = "^[\\s]{0,}while[\\s]{0,}\\((.*)\\)[\\s]{0,}\\{[\\s]{0,}$";
		return Pattern.compile(regex).matcher(line);
	}
	
	public Matcher scan(String line)
	{
		String regex = "^[\\s]{0,}scan[\\s]{0,}[$]([a-zA-Z_0-9]\\w*)[;][\\s]{0,}$";
		return Pattern.compile(regex).matcher(line);
	}
}
