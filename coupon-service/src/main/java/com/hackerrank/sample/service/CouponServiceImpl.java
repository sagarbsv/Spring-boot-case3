package com.hackerrank.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.Repository.CouponRepository;
import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Coupon;
@Service
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	CouponRepository cpnRepo;

	@Override
	public Coupon createCoupon(Coupon coupon) {
		Coupon existingcpn = cpnRepo.getOne(coupon.getId());  

        if (existingcpn!= null) {
            throw new BadResourceRequestException("Coupon exists.");
        }

        Coupon savedCoupon= cpnRepo.save(coupon);
        return savedCoupon;
	}

	@Override
	public void deleteAllCoupons() {
		cpnRepo.deleteAll();
		
	}

	@Override
	public Coupon getCouponByAmt(long id) {
		Coupon cpn = cpnRepo.getOne(id);

        if (cpn == null) {
            throw new NoSuchResourceFoundException("No coupon with given amount found.");
        }
        return cpn;
	}

	@Override
	public List<Coupon> getAllCustomers() {
		
		return cpnRepo.findAll();
	}
	

}
