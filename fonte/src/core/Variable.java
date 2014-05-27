package core;

public class Variable {

	private String name;

	private String type;

	private String value;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	/*
	 * String
	 */

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	/*
	 * Int
	 */

	public int getIntValue() {
		
		if (this.value.equals("true")) {
			return 1;
		} else if (this.value.equals("false")) {
			return 0;
		}
		
		return Integer.parseInt(this.value);
	}

	/*
	 * Double
	 */

	public double getDoubleValue() {
		return Double.parseDouble(this.value);
	}

	/*
	 * Boolean
	 */

	public boolean isTrue() {
		if (this.value.equals("true") || this.getIntValue() != 0) {

			return true;
		}

		return false;
	}
}
