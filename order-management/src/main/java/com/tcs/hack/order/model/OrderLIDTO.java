package com.tcs.hack.order.model;

public class OrderLIDTO {
	private long skuId;
	public OrderLIDTO() {
		
	}
	public OrderLIDTO(long skuId, int itemQty) {
		this.skuId = skuId;
		this.itemQty = itemQty;
	}
	public long getSkuId() {
		return skuId;
	}
	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	private int itemQty;
}
