package com.hackerrank.sample.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Coupon;
import com.hackerrank.sample.service.CouponService;
@RestController
public class CouponController {

	@Autowired
	private CouponService cpnService;

	@RequestMapping(value = "/coupons", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createCoupon(@RequestBody @Valid Coupon cpn) throws Exception {

		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(cpnService.createCoupon(cpn), HttpStatus.CREATED);
		} catch (BadResourceRequestException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;

	}
	
	@RequestMapping(value = "/coupons", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllCoupons() {
		cpnService.deleteAllCoupons();   
	}
	
	@RequestMapping(value = "/coupons", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getCouponById(@PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(cpnService.getCouponByAmt(id), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}
	
	@RequestMapping(value = "/coupons", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAllCoupons() {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(cpnService.getAllCustomers(), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}
	
}
