package com.bitcoinRestApi.restApi.service;
//import org.springframework.boot.RestC

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcoinRestApi.restApi.model.Bitcoin;
import com.bitcoinRestApi.restApi.model.BitcoinRepository;
import com.bitcoinRestApi.restApi.model.MovingAverage;
import com.bitcoinRestApi.restApi.model.Price;
import com.bitcoinRestApi.restApi.model.TradeDecision;
import com.bitcoinRestApi.restApi.util.BitcoinRestApiUtil;

@Service
public class BitcoinService {
	
	@Autowired
	private BitcoinRepository bitcoinRepository;
	
	BitcoinRestApiUtil util= new BitcoinRestApiUtil();
	
	public void saveBitcoins(Bitcoin bitcoin ) {
		bitcoinRepository.save(bitcoin); 
	}
	
	public List<Price> getBitcoins(String period ) {
		
		String timePerid = period.trim().toLowerCase();
		System.out.println("** "+timePerid);
		
		List <String> supportedPeriods = new ArrayList<>();
		
//		supportedPeriods.add("day");
		supportedPeriods.add("week");
		supportedPeriods.add("month");
		supportedPeriods.add("year");
				
		if(supportedPeriods.contains(timePerid)) {
			return FilterList(bitcoinRepository.findAll(),timePerid);
		}else {
			
		}
		
		return null;
	}
	
	
	public List<Price> FilterList( List<Bitcoin> list,String filter){
		
		Bitcoin bitCoin = list.get(0);
		
		
		
		if(filter.equals("week")) {
			Calendar firstDatlastWeek = util.getLastWeekFirstDay();

			Calendar lastDatlastWeek = util.getLastWeekLastDay();
			
			Stream<Price> str_Price = Arrays.asList(bitCoin.getPrices()).stream().filter(t -> t.getTime().after(firstDatlastWeek.getTime()) &&  t.getTime().before(lastDatlastWeek.getTime()));
			
//			System.out.println(str_Price.collect(Collectors.toList()));
			
			return str_Price.collect(Collectors.toList());
//			Calendar c = Calendar.getInstance();
//			Date dateSpecified = c.getTime();
//			if (dateSpecified.before(today)) {}
			
//			for (Bitcoin bitcoin : list) {
//				Price[] pArray = bitcoin.getPrices();
//				for (Price priceObj : pArray) {
//					priceObj.getTime()()
//				}
			}else if(filter.equals("month")) {
				Calendar firstDatlastWeek = util.getLastMonthFirstDay();

				Calendar lastDatlastWeek = util.getLastMonthLastDay();
				
				Stream<Price> str_Price = Arrays.asList(bitCoin.getPrices()).stream().filter(t -> t.getTime().after(firstDatlastWeek.getTime()) &&  t.getTime().before(lastDatlastWeek.getTime()));

				return str_Price.collect(Collectors.toList());
			}else if(filter.equals("year")) {
		
				int lastYear = util.getLastYear();
				
				Stream<Price> str_Price = Arrays.asList(bitCoin.getPrices()).stream().filter(t -> util.getYear(t.getTime()) == lastYear );

				return str_Price.collect(Collectors.toList());
			} 
		
	return null;		
	}

	public List<MovingAverage> getMABitCoin(String startDate, String endDate,int rollingDays) {
		List<Bitcoin> list = bitcoinRepository.findAll();
		
		Bitcoin bitCoin = list.get(0);
		
		Date startdateLocal = util.getDate(startDate);
		System.out.println("startdateLocal :"+startdateLocal.getTime());
		Date enddateLocal = util.getDate(endDate);
		System.out.println("enddateLocal :"+enddateLocal);
//		SimpleDateFormat sDF = new SimpleDateFormat("YYYY-MM-dd");
		Stream<Price> str_Price = null;
//		str_Price = Arrays.asList(bitCoin.getPrices()).stream().filter(t -> sDF.parse(t.getTime().toString()).after(startdateLocal) &&  sDF.parse(t.getTime().toString()).before(enddateLocal));
		str_Price = Arrays.asList(bitCoin.getPrices()).stream().filter(t -> t.getTime().after(startdateLocal) &&  t.getTime().before(enddateLocal));
		List<Price> lst_Price = str_Price.collect(Collectors.toList());
		
		System.out.println("the filtered list :"+lst_Price);
		
		List<MovingAverage> lst_mvgAvg = new ArrayList<MovingAverage>();
		
		float sum = 0;
		
		for(Price price : lst_Price) {
			System.out.println("the price is  :"+price.getPrice());
			MovingAverage mvAvg = new MovingAverage();
			mvAvg.setDate(price.getTime());
			mvAvg.setPrice(price.getPrice());
			sum +=price.getPrice();
			mvAvg.setMvgAvg(sum/rollingDays);
			lst_mvgAvg.add(mvAvg);
		}
		
		return lst_mvgAvg;		
		
	}

	public  List<Price> getBitcoinsfordate(String date) {
		System.out.println("the date is  :"+date);
		List<Bitcoin> list = bitcoinRepository.findAll();
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate = util.getOnlyDate(date);
		System.out.println("the inpuedate is  :"+inputDate);
		System.out.println("the inpuedate is  :"+inputDate.getTime());
		Bitcoin bitCoin = list.get(0);
		Stream<Price> str_Price = Arrays.asList(bitCoin.getPrices()).stream().filter(t -> util.getOnlyDate(sDF.format(t.getTime()))
				.equals(inputDate));
		
		return str_Price.collect(Collectors.toList());
		
	}

	public TradeDecision getTradeDecision(int period) {
		Date lastDate = util.getCurrentDay();
		Date firstDate = util.getlastNthDay(period);
		List<Bitcoin> list = bitcoinRepository.findAll();
		Bitcoin bitCoin = list.get(0);
		Stream<Price> str_Price = Arrays.asList(bitCoin.getPrices()).stream().filter(t -> t.getTime().after(firstDate) &&  t.getTime().before(lastDate));
		List<Price> str_Fprice = str_Price.collect(Collectors.toList());
		ArrayList<Float> priceList = new ArrayList<Float>();
		float highvalue = 0.0F;
		float minvalue = 0.0F;
		for(Price price : str_Fprice) {
			priceList.add(price.getPrice());
			highvalue = Collections.max(priceList);
			 minvalue = Collections.min(priceList);
			
			
		}
		float percentageChange = ((highvalue-minvalue)/minvalue)*100;
		System.out.println(priceList);
		System.out.println("percentageChange "+percentageChange);
		String resp = percentageChange> 0 ? "BUY" :(percentageChange< 0 ? "SELL" :"HOLD" );  
		
		return new TradeDecision(resp);
	}

}
