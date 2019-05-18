package com.example.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

import reactor.core.publisher.Mono;

@Repository
public class ReactiveRedisRepoImpl  {
	
	/*
	 * @Autowired private ReactiveRedisOperations<String, Employee> operations;
	 */
	
	@Autowired private ReactiveRedisTemplate<String, Employee> operations;

	public Mono<Boolean> save(Employee employee) {
		System.out.println("ReactiveRedisRepoImpl post save Employee");
		return operations.opsForHash()
					.put(Employee.class.getCanonicalName(), employee.getId(), employee);
	}

	public Mono<Object> findByKey(String EmpId) {
		System.out.println("ReactiveRedisRepoImpl get findByKey Employee");
		return operations.opsForHash().get(Employee.class.getCanonicalName(), EmpId);
//		operations.opsForHash().
	}

}
