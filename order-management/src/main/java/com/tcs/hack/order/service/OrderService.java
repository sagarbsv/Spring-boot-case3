package com.tcs.hack.order.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tcs.hack.order.exceptions.BadResourceRequestException;
import com.tcs.hack.order.exceptions.NoSuchResourceFoundException;
import com.tcs.hack.order.model.Coupon;
import com.tcs.hack.order.model.Order;
import com.tcs.hack.order.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepo;
	 @Autowired
	 private DiscoveryClient  eurekaClient;
	 
	 public void deleteAllOrders() {
		 orderRepo.deleteAllInBatch();
	    }

	    
	    public void deleteOrderById(Long id) {
	                 Order existingOrder = orderRepo.findOne(id);
	                 if (existingOrder == null) {
	             throw new NoSuchResourceFoundException("Order with the id not exists.");
	         }
	                 orderRepo.delete(id);
	    }
  
	    
	    public Order createOrder(Order Order)  {
	        Order existingOrder = orderRepo.findOne(Order.getOrderId());

	        if (existingOrder != null) {
	            throw new BadResourceRequestException("Order with same id exists.");
	        }

	        Order savedOrder= orderRepo.save(Order);
	        return savedOrder;
	    }
	    
	    @HystrixCommand(fallbackMethod = "failed")
	    public Coupon createCoupon(){
	    	Coupon coupon=new Coupon();
	    	coupon.setDiscountAmt(300);
	    	coupon.setTermsAndConditions("valid till 2 days");
	        List<ServiceInstance> instanceInfo = eurekaClient.getInstances("coupon-service");
	        String serviceBaseUrl = instanceInfo.get(0).getUri().toString();
	        System.out.println("URL---"+serviceBaseUrl);
	        Coupon result = new RestTemplate().postForObject(serviceBaseUrl + "coupons",coupon,Coupon.class);
	        System.out.println("Object is "+result.getDiscountAmt());
	        return result;
	    }

	    public Coupon failed(){
	    	Coupon cpndto=new Coupon();
	        return cpndto;
	    }

	    public boolean checkCustomerAdd(long id){
	    	 List<ServiceInstance> instanceInfo= eurekaClient.getInstances("zuul-proxy");
	        String serviceBaseUrl = instanceInfo.get(0).getUri().toString();
	        System.out.println("URL---"+serviceBaseUrl);
	        Object result = new RestTemplate().getForObject(serviceBaseUrl + "producer/customers/"+id,Object.class);
	        return true;
	    }

	    
	    public Order updateOrder(Order Order,Long id)  {
	        Order existingOrder = orderRepo.findOne(id);
	     
	        if (existingOrder == null) {
	            throw new NoSuchResourceFoundException("Order with the id not exists.");
	        }
	       
	        Order savedOrder= orderRepo.save(Order);
	        return savedOrder;
	    }
	    
	    
	    public Order getOrderById(Long id) { 
	        Order Order = orderRepo.findOne(id);

	        if (Order == null) {
	            throw new NoSuchResourceFoundException("No Order with given id found.");
	        }

	        return Order;
	    }

	    
	    public List<Order> getAllOrders() {
	                List<Order>  OrderList =orderRepo.findAll();
	                 if (OrderList.isEmpty()) {
	             throw new NoSuchResourceFoundException("No Order with given id found.");
	         }
	                 
	                 return OrderList;

	    }
}
