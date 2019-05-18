package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.ReactiveRedisRepoImpl;

import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

	@Autowired
	private ReactiveRedisRepoImpl reactiveRedisRepo;
	
	public Mono<Boolean> save(Employee employee){
		System.out.println("EmployeeService post save Employee");
		return reactiveRedisRepo.save( employee);
	}
	
	public Mono<Object> findByKey(String objKey){
		System.out.println("EmployeeService get findByKey Employee");
		return reactiveRedisRepo.findByKey(objKey);
	}
}
