package com.gaspar.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean = new SomeBean("val1", "val2", "val3");
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(getFilters("field1","field3"));
		
		return mapping;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList(){
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value 3"),
							 new SomeBean("value4", "value5", "value6"));
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(getFilters("field1","field2"));
		return mapping;
	}
	 
	private FilterProvider getFilters(String... fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		return filters;
	}
	
}
