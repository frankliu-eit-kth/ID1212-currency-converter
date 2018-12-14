package app.converter.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app.converter.model.ConvertRate;
import app.converter.model.ConvertRateRepo;
import app.converter.model.Counter;
import app.converter.model.CounterRepo;
import app.converter.model.Currency;
import app.converter.model.CurrencyRepo;
import app.converter.view.CalculateAmountForm;

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
	  
	public int loopUpCount() {
	try {
		Counter counter=counterRepo.findCounterById(COUNTER_ID);
		return counter.getCount();
	}catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
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
				convertRateRepo.saveAndFlush(cr);
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
