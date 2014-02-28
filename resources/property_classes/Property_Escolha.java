package property_classes;

public class Property_Escolha implements Property {

	private String name;
	
	int selectedOption;
	
	private String[] options;
	
	public Property_Escolha (String name, String selected, String ... options) {
		
		this.name = name;
		
		this.options = options;
		
		for (int i = 0; i < options.length; i++)
			if (options[i].equals(selected))
				selectedOption = i; 
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getSelected() {
		return options[selectedOption];
	}
	
	public boolean isOption(String s) {
		
		if (options[selectedOption].equals(s))
			return true;
		else
			return false;
		
	}
	
	public String[] getOptions() {
		return options;
	}
	
	/**
	 * Takes a string as parameter
	 */
	public void setValue(Object i) {
		
		for (int x = 0; x < options.length;x++) {
			if ((String)i == options[x])
				selectedOption = x;
		}
		
	}

}
