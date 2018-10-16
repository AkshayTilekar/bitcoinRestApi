package com.bitcoinRestApi.restApi.model;

import java.util.Date;

public class Price {

	private float price;
	private Date time;
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	} 
}
