package com.example.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

import reactor.core.publisher.Mono;

@Repository
public class ReactiveRedisRepoImpl implements ReactiveRedisRepo {
	
	@Autowired
	private ReactiveRedisOperations<String, Employee> operations;

	@Override
	public Mono<Employee> save(String key,Employee employee) {
		System.out.println("ReactiveRedisRepoImpl post save Employee");
		return operations.opsForList()
					.set(key, employee.getId(), employee)
					.map(__ -> employee);
					
	}

	@Override
	public Mono<Employee> findByKey(String key) {
		System.out.println("ReactiveRedisRepoImpl get findByKey Employee");
		return operations.opsForValue()
				.get(key);
	}

}
