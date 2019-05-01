package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import lombok.Value;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/redis/employee")
public class EmployeeController {

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
	Mono<Employee> getEmployee(@PathVariable String key){
		/*return employeeService.findByKey(key)
				.map(employee -> ResponseEntity.ok(employee))
								.defaultIfEmpty(ResponseEntity.notFound().build());*/
		System.out.println("EmployeeController @@GetMapping "+key);
		Mono<Employee> monoemployee=  employeeService.findByKey(key);
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
	
	@PostMapping("/{key}")
	Mono<Employee> createEmployee(@PathVariable String key,@RequestBody Employee newEmployee){
		/*return employeeService.save(key,newEmployee)
								.map(employee -> ResponseEntity.ok(employee));*/
		System.out.println("EmployeeController @PostMapping "+key+" employee "+newEmployee);
		Mono<Employee> monoemployee= employeeService.save(key,newEmployee);
		System.out.println("success post employee=>"+monoemployee);
		return monoemployee;
	}
}
