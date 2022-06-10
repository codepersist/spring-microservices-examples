package filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/* Implementation to show static filtering in Restful service*/
@RestController
public class FilteringController {

	/*
	 * @GetMapping("/filtering") public SomeBean retrieveSameBean() { return new
	 * SomeBean("value1","value2","value3"); }
	 * 
	 * @GetMapping("/filtering-list") public List<SomeBean> retrieveSameBeanList() {
	 * return Arrays.asList(new SomeBean("value1","value2","value3"), new
	 * SomeBean("value11","value22","value33")); }
	 */
	
	/* Implementing Dynamic Filtering in Restful service */
	//Either run above or below

	//returning field1,field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSameBean() {
		SomeBean somebean = new SomeBean("value1","value2","value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1","value2");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mappingJacksonValue =  new MappingJacksonValue(somebean);
		mappingJacksonValue.setFilters(filterProvider);
		
		return mappingJacksonValue;
	}

	//returning field2,field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveSameBeanList() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2","value3"),
								new SomeBean("value11","value22","value33"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value2", "value3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
		
	}
}
