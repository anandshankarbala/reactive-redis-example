package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.ReactiveRedisRepo;

import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

	@Autowired
	private ReactiveRedisRepo reactiveRedisRepo;
	
	public Mono<Employee> save(String key ,Employee employee){
		System.out.println("EmployeeService post save Employee");
		return reactiveRedisRepo.save(key, employee);
	}
	
	public Mono<Employee> findByKey(String key){
		System.out.println("EmployeeService get findByKey Employee");
		return reactiveRedisRepo.findByKey(key);
	}
}
