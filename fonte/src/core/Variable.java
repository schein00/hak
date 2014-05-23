package core;

public class Variable {

	private String name;
	
	private String type;
	
	private String value;
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	/*
	 * String
	 */
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	/*
	 * Int
	 */
	
	public int getIntValue()
	{
		return Integer.parseInt(this.value);
	}
	
	/*
	 * Double
	 */
	
	public double getDoubleValue()
	{
		return Double.parseDouble(this.value);
	}
}
