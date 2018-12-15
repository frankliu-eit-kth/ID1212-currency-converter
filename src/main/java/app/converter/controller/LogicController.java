package app.converter.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app.converter.DAO.ConvertRateRepo;
import app.converter.DAO.CounterRepo;
import app.converter.DAO.CurrencyRepo;
import app.converter.model.ConvertRate;
import app.converter.model.Counter;
import app.converter.model.Currency;
import app.converter.view.CalculateAmountForm;
/**
 * 
 * @author Liming Liu
 * @role  the logic controller of the currency converter
 * @robustness the setUp method only set several entries for experiment, it's not complete yet
 * 				there should be only one entry in counter table, currently this is manually maintained by constantly checking
 * @ExceptionHandling  exceptions are not well-handled yet
 *
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class LogicController {
	@Autowired
	private CounterRepo counterRepo;
	@Autowired
	private CurrencyRepo currencyRepo;
	@Autowired
	private ConvertRateRepo convertRateRepo;
	
	private final long COUNTER_ID=1;
	
	private boolean DB_SETUP_STATE=false;
	
	/*
	 * manually set up the data base
	 */
    public void setUp() {
    	if(DB_SETUP_STATE) {
    		return;
    	}
    	Counter c = new Counter(COUNTER_ID,0);
    	counterRepo.saveAndFlush(c);
    	
    	List<Currency> currencyList=new ArrayList<Currency>();
    	Currency cur1 = new Currency(1,"SEK");
    	Currency cur2 = new Currency(2,"EUR");
    	Currency cur3 = new Currency(3,"USD");
    	Currency cur4 = new Currency(4,"RMB");
    	currencyList.add(cur1);
    	currencyList.add(cur2);
    	currencyList.add(cur3);
    	currencyList.add(cur4);
    	currencyRepo.saveAll(currencyList);
    	
    	List<ConvertRate> rateList=new ArrayList<ConvertRate>();
    	ConvertRate cr1=new ConvertRate(1,cur1,cur2,0.1);
    	ConvertRate cr2=new ConvertRate(2,cur1,cur3,1.0);
    	ConvertRate cr3=new ConvertRate(3,cur1,cur4,1.0);
    	ConvertRate cr4=new ConvertRate(4,cur2,cur1,10.0);
    	rateList.add(cr1);
    	rateList.add(cr2);
    	rateList.add(cr3);
    	rateList.add(cr4);
    	convertRateRepo.saveAll(rateList);
    	DB_SETUP_STATE=true;
    	
    }
	
	public void printAllCounter() {
		List<Counter> counters=counterRepo.findAll();
		for(Counter c:counters){
			System.out.println(c.toString());
		}
	}
	
	public void printAllConvertRate() {
		List<ConvertRate> rates=convertRateRepo.findAll();
		for(ConvertRate cr:rates){
			System.out.println(cr.toString());
		}
	}
	
	public void printAllCurrency() {
		List<Currency>	currencies=currencyRepo.findAll();
		for(Currency c:currencies) {
			System.out.println(c.toString());
		}
	}
	
	
	public Counter getCounter() {
		try {
			
			List<Counter> counters=counterRepo.findAll();
			if(counters.size()==0) {
				Counter counter=new Counter(COUNTER_ID,0);
				counterRepo.save(counter);
				
			}
			if(counters.size()>1) {
				System.out.println("more than 1 counter");
				return null;
			}
			Counter counter=counterRepo.findCounterById(COUNTER_ID);
			return counter;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public void addCounterByOne() {
		
		try {
			Counter counter=counterRepo.findCounterById(COUNTER_ID);
			counter.addCount();
			counterRepo.saveAndFlush(counter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double getConvertRate(String fromCurr, String toCurr) throws Exception{
		List<ConvertRate> convertRates= convertRateRepo.findAll();
		for(ConvertRate cr:convertRates) {
			if(cr.getCurFrom().getName().equals(fromCurr)&&cr.getCurTo().getName().equals(toCurr)) {
				return cr.getRate();
			}
		}
		throw new Exception("wrong input currency name");
	}
	
	public void changeConvertRate(String fromCurr, String toCurr, double newRate) throws Exception{
		List<ConvertRate> convertRates= convertRateRepo.findAll();
		for(ConvertRate cr:convertRates) {
			if(cr.getCurFrom().getName().equals(fromCurr)&&cr.getCurTo().getName().equals(toCurr)) {
				cr.setRate(newRate);
				System.out.println("find the corresponding from->to change rate object");
				convertRateRepo.saveAndFlush(cr);
				System.out.println("changed from->to");
			}
			if(cr.getCurFrom().getName().equals(toCurr)&&cr.getCurTo().getName().equals(fromCurr)) {
				cr.setRate(1.0/newRate);
				System.out.println("find the corresponding to->from change rate object");
				convertRateRepo.saveAndFlush(cr);
				System.out.println("changed to->from");
			}
		}
	}
	
	public CalculateAmountForm calculateAmount(String fromCurr, String toCurr, double amount) throws Exception{
		CalculateAmountForm form=new CalculateAmountForm();
		form.setCurrFrom(fromCurr);
		form.setCurrTo(toCurr);
		form.setAmountFrom(amount);
		double convertRate=getConvertRate(fromCurr, toCurr);
		form.setAmountTo(amount*convertRate);
		return form;
	}
	
	
	
	
}
