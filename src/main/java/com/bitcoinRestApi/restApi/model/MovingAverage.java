package com.bitcoinRestApi.restApi.model;

import java.util.Date;

public class MovingAverage {

	private Date date;
	private float price;
	private float mvgAvg;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getMvgAvg() {
		return mvgAvg;
	}
	public void setMvgAvg(float mvgAvg) {
		this.mvgAvg = mvgAvg;
	}
	
}
