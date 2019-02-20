package com.hackerrank.sample.service;

import java.util.List;

import com.hackerrank.sample.model.Coupon;

public interface CouponService {
	
	Coupon createCoupon(Coupon coupon);
	void deleteAllCoupons();
	Coupon getCouponByAmt(long id);
	List<Coupon> getAllCustomers();

}
