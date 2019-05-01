package com.example.demo.repo;

import com.example.demo.entity.Employee;

import reactor.core.publisher.Mono;

public interface ReactiveRedisRepo  {
	
	Mono<Employee> save(String key ,Employee employee);

    Mono<Employee> findByKey(String key);
	
}
