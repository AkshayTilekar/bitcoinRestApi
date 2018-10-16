package com.bitcoinRestApi.restApi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="btcprices")
public class Bitcoin {

	@Id
	private String base;
	private String currency;
	private Price[] prices;
	
	public String getBase() {
		System.out.println("etting Base");
		return base;
	}
	public void setBase(String base) {

		System.out.println("Setting Base");
		this.base = base;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Price[] getPrices() {
		return prices;
	}
	public void setPrices(Price[] prices) {
		this.prices = prices;
	}
	
	
	
}
