package core;

public class Triple {

	private String attribute;
	private String operator;
	private String value;
	
	public Triple(String attribute, String operator, String value) {
		super();
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
	}
	
	public Triple() {
	}

	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Triple [attribute=" + attribute + ", operator=" + operator + ", value=" + value + "]";
	}

	public void setAll(String[] tvalue) {
		if(tvalue.length == 3){
			attribute = tvalue[0];
			operator = tvalue[1];
			value = tvalue[2];
		}
	}
	
	
}
