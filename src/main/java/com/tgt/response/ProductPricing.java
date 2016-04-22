package com.tgt.response;

public class ProductPricing {
//{"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
    private final String id;
    private final String name;
    private final CurrentPrice currentPrice;
    
    public String getId() {
        return id;
    }
    public String getname() {
		return name;
	}

	public CurrentPrice getCurrentPrice() {
		return currentPrice;
	}

	
    public ProductPricing(String id, String name, CurrentPrice currentPrice) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        
    }

   

   
}