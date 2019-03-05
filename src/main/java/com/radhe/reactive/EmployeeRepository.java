package com.radhe.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String>
{
	@Tailable
	Flux<Employee> findPeopleBy();
	@Override
	default Flux<Employee> findAll(){
		return findPeopleBy();
	}
} 
