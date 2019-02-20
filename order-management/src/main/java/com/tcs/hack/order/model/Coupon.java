package com.tcs.hack.order.model;

public class Coupon {

	long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(long discountAmt) {
		this.discountAmt = discountAmt;
	}
	public String getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	long discountAmt;
	String validityDate;
	String termsAndConditions;
	public Coupon() {
		// TODO Auto-generated constructor stub
	}
	public Coupon(int id, long discountAmt, String validityDate, String termsAndConditions) {
		this.id = id;
		this.discountAmt = discountAmt;
		this.validityDate = validityDate;
		this.termsAndConditions = termsAndConditions;
	}
	

}
