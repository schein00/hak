package token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {

	public Matcher comment(String line)
	{
		String regex = "^(\\/\\/)";
		return Pattern.compile(regex).matcher(line);
	}
	
	public Matcher variable(String line)
	{
		String regex = "^[$]([a-zA-Z_0-9]\\w*)[\\s]{0,}[=](.*)[;]$";
		return Pattern.compile(regex).matcher(line);
	}
	
	public Matcher print(String line)
	{
		String regex = "^print(ln)?[\\s]{0,}(.*)[;]$";
		return Pattern.compile(regex).matcher(line);
	}
}
