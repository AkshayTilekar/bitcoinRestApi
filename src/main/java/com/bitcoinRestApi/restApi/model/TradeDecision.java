package com.bitcoinRestApi.restApi.model;

public class TradeDecision {

	private String decision;

	public TradeDecision(String resp) {
		// TODO Auto-generated constructor stub
		this.decision=resp;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}
}
