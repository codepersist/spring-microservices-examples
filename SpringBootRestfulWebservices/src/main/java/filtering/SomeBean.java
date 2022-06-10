package filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Implementation to show static filtering in Restful service*/
//@JsonIgnoreProperties(value= {"value1"})   // Use in case of static filtering
@JsonFilter("SomeBeanFilter")                // Use in case of dynamic filtering
public class SomeBean {

	private String value1;
	private String value2;
	
	//Value3 will not be sent in JSON response
	@JsonIgnore
	private String value3;

	public SomeBean(String field1, String field2, String field3) {
		super();
		this.value1=field1;
		this.value2=field2;
		this.value3=field3;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
}
