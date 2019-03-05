package com.radhe.reactive;

import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController
{
	@Autowired
	private EmployeeRepository repo;

	@PostMapping("/employee")
	public @ResponseBody Mono<ResponseEntity<Employee>> createEmployee(@RequestBody Employee employee){
		
		return Mono.just(employee).map(emp -> {
			if(StringUtils.isBlank(employee.getName())){
				throw new HttpMessageConversionException("Name not found");
			}
			return emp;
		}).flatMap(emp -> repo.save(emp)).map(emp -> new ResponseEntity<>(emp, HttpStatus.OK));
	}
	
	@GetMapping(value = "employees", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public @ResponseBody Flux<ResponseEntity<Employee>> getEmployees(){
		return repo.findAll().map(emp -> new ResponseEntity<>(emp, HttpStatus.OK));
	}
	
	@Autowired
	private MongoOperations operations;
	@PostConstruct
	public void postConstruct() {
		CollectionOptions options = CollectionOptions.empty().capped().size(5242880).maxDocuments(5000);
		operations.createCollection("employee", options);
	}
}
