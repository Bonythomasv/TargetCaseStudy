package com.tgt.response;

//"current_price":{"value": 13.49,"currency_code":"USD"}}
public class CurrentPrice {
	
	private final String currencyCode;
    private final double value;

	   public String getCurrencyCode() {
		return currencyCode;
	}
	public double getValue() {
		return value;
	}

	public CurrentPrice(String currencyCode, double value) {
        this.value = value;
        this.currencyCode = currencyCode;
        
    }
}
