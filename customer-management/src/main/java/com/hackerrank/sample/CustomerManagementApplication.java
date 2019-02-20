package com.hackerrank.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
@EnableCircuitBreaker
@EnableHystrixDashboard
@SpringBootApplication
@EnableDiscoveryClient
public class CustomerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagementApplication.class, args);
	}

}

/*
 * @HystrixCommand(fallbackMethod="getDefaultEmp")
 * 
 * @HystrixCommand(fallbackMethod="defaultEmp", commandKey="EmpDetails",
 * groupKey="Emp-Service", commandProperties={
 * 
 * @HystrixProperty (name="circuitBreaker.sleepWindowInMilliseconds",
 * value="60000"),
 * 
 * @HystrixProperty (name="circuitBreaker.errorThresholdPercentage", value="5"),
 * })
 */