package com.bitcoinRestApi.restApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bitcoinRestApi.restApi.model.Bitcoin;
import com.bitcoinRestApi.restApi.model.BitcoinRepository;
import com.bitcoinRestApi.restApi.model.MovingAverage;
import com.bitcoinRestApi.restApi.model.Price;
import com.bitcoinRestApi.restApi.model.TradeDecision;
import com.bitcoinRestApi.restApi.service.BitcoinService;

@RestController
public class BitcoinController {
	
	@Autowired
	private BitcoinService bitcoinService;
	
	@RequestMapping(method=RequestMethod.POST,value="btcprices")
	public void addBitCoin(@RequestBody Bitcoin bitcoin) {
		bitcoinService.saveBitcoins(bitcoin);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="btcprices/{period}")
	public List<Bitcoin> getBitCoin(@PathVariable("period") String period ) {
		List it = bitcoinService.getBitcoins(period);  
		return it;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/btcprices")
	public List<Bitcoin> getBitCoinfordate(@RequestParam("date") String date ) {
		List it = bitcoinService.getBitcoinsfordate(date);  
		return it;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="tradedecision/{period}")
	public TradeDecision getDecision(@PathVariable("period") int period ) {
		
		return bitcoinService.getTradeDecision(period);  
	
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getmovingavg")
	public List<MovingAverage> getMABitCoin(@RequestParam("startDate") String startDate , @RequestParam("endDate") String endDate,@RequestParam("rollingDays") int rollingDays ) {
		System.out.println(startDate+" "+endDate+" "+rollingDays);		
		return bitcoinService.getMABitCoin(startDate, endDate, rollingDays);
	}

}
