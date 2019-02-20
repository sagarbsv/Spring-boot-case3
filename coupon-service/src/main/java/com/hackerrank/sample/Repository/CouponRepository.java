package com.hackerrank.sample.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.sample.model.Coupon;
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

}
