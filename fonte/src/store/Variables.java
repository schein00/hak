package store;

import java.util.ArrayList;

import core.Variable;

public class Variables {

	private ArrayList<Variable> variables;
	
	public static Variables instance;
	
	public static Variables getInstance()
	{
		if(Variables.instance == null) {
			Variables.instance = new Variables();
		}
	
		return Variables.instance;
	}
	
	public Variables()
	{
		this.variables = new ArrayList<Variable>();
	}
	
	public void add(Variable var)
	{
		Variable test = this.find(var.getName());
		if(test != null) {
			
			// Variável já foi criada, somente altera valor
			test.setValue(var.getValue());
		} else {
			
			// Cria a variável
			this.variables.add(var);
		}
	}
	
	public Variable find(String name)
	{
		for(int i = 0; i < this.variables.size(); i++)
		{
			if(this.variables.get(i).getName().equals(name))
			{
				return this.variables.get(i);
			}
		}
		
		return null;
	}
}