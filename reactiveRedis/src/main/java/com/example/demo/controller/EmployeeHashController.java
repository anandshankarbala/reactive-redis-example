package com.example.demo.controller;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import io.netty.util.internal.ThreadLocalRandom;
import lombok.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/redis/hash/employee")
public class EmployeeHashController {

	@Autowired
	private EmployeeService employeeService;
	
/*	@GetMapping("/{key}")
	Mono<ResponseEntity<Employee>> getEmployee(@PathVariable String key){
		/*return employeeService.findByKey(key)
				.map(employee -> ResponseEntity.ok(employee))
								.defaultIfEmpty(ResponseEntity.notFound().build());* /
		System.out.println("EmployeeController @@GetMapping "+key);
		Mono<Employee> monoemployee=employeeService.findByKey(key);
		Employee employee = monoemployee.block();
		System.out.println("success get employee=>"+employee);
		return Mono.just(ResponseEntity.ok(employee));
	}*/
	
	@GetMapping("/{key}")
	Mono<Object> getEmployee(@PathVariable String key){
		/*return employeeService.findByKey(key)
				.map(employee -> ResponseEntity.ok(employee))
								.defaultIfEmpty(ResponseEntity.notFound().build());*/
		System.out.println("EmployeeController @@GetMapping "+key);
		Mono<Object> monoemployee=  employeeService.findByKey(key);
		System.out.println("success get employee=>"+monoemployee);
		return monoemployee;
	}
	
	/*@PostMapping("/{key}")
	Mono<ResponseEntity<Employee>> createEmployee(@PathVariable String key,@RequestBody Employee newEmployee){
		/*return employeeService.save(key,newEmployee)
								.map(employee -> ResponseEntity.ok(employee));* /
		System.out.println("EmployeeController @PostMapping "+key+" employee "+newEmployee);
		Mono<Employee> monoemployee= employeeService.save(key,newEmployee);
		Employee employee = monoemployee.block();
		System.out.println("success post employee=>"+employee);
		return Mono.just(ResponseEntity.ok(employee));
	}*/
	
	@PostMapping("")
	Mono<Boolean> createEmployee(@RequestBody Employee newEmployee){
		/*return employeeService.save(key,newEmployee)
								.map(employee -> ResponseEntity.ok(employee));*/
		System.out.println("EmployeeController @PostMapping employee "+newEmployee);
		return employeeService.save(newEmployee).doOnSuccess(System.out :: println);
	}
	
	@GetMapping(value="/getFluxStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<Employee> getEmployeeStream(){
		System.out.println("getFluxStream console");
		return Flux.<Employee>generate(sink -> 
							sink.next(
									new Employee("1234567",(new Date()).toString(),"random-value1")))
        .delayElements(Duration.ofSeconds(1));
	}
}
