package app.converter.model;

public interface ConvertRateDTO {
	Currency getCurFrom();
	
	Currency getCurTo();
	
	double getRate();
}
