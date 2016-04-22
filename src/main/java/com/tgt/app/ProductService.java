package com.tgt.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.api.ProductApiPojo;
import com.tgt.response.CurrentPrice;
import com.tgt.response.ProductPricing;

import java.util.HashMap;

//import hello.Application;

import java.util.List;
import java.util.Map;

/**
 * This is a  service class 
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public List getProducts() {
        return repository.findAll();
    }

    
	public ProductPricing searchProductPrice(String productId) {

		//call the Target API to get Product Name
				String productName = targetApiCall(productId);
				log.info("productName from API call is "+productName);
				
		System.out.println("productId in SearchProductPrice "+productId);

		//clean the mongo repository
		//repository.deleteAll();
		// Code for Saving New data to the repository
		/*repository.save(new Product("15117729", 12.67, "USD"));
		repository.save(new Product("13860428", 13.49, "USD"));
		repository.save(new Product("16483589", 45.99, "USD"));
		repository.save(new Product("16696652", 24.56, "USD"));
		repository.save(new Product("16752456", 67.45, "USD"));
		repository.save(new Product("15643793", 10.99, "USD"));*/
        
		/*//fetch all Products
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Product product : repository.findAll()) {
			System.out.println(product);
		}
		System.out.println(); */
		
		//intializing ProductPricing response JSON
		CurrentPrice currentPrice = new CurrentPrice("", 0);
		ProductPricing 	productPricing = new ProductPricing(productId,"",currentPrice );

		System.out.println("Customer found with findByProductId(productId):");
		System.out.println("--------------------------------");
		for (Product product : repository.findByProductId(productId))
		{
			System.out.println("ProductPrice With productId is"+product.getCurrentPrice());
			System.out.println("ProductCurrency With productId is"+product.getCurrencyCode());

			//form the response JSON
			 currentPrice = new CurrentPrice(product.getCurrencyCode(), product.getCurrentPrice());
	    	 productPricing = new ProductPricing(productId,productName,currentPrice );
	    	 System.out.println("productPricing is "+productPricing);
		}
		
		
		return productPricing ;
       
// Delete the below code once the code is stable
		/*
		//Fetch based on Product Price
		System.out.println("Customers found with findByCurrentPrice(45.79):");
		System.out.println("--------------------------------");
		for (Product product : repository.findByCurrentPrice(45.79)) {
			System.out.println(product);
		}
        //Fetch Based on Currency Code
		System.out.println("Customers found with findByCurrencyCode('USD'):");
		System.out.println("--------------------------------");
		for (Product product : repository.findByCurrencyCode("USD")) {
			System.out.println(product);
		}*/
	}
	
	public String targetApiCall(String productId)
	{
		
		 RestTemplate restTemplate = new RestTemplate();
		 ProductApiPojo productApiPojo = restTemplate.getForObject("https://api.target.com/products/v3/"+productId+"?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz", ProductApiPojo.class);
	     String productName = "" ;
   
	        /*String productApiResponseJSON = restTemplate.getForObject("https://api.target.com/products/v3/"+productId+"?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz", String.class);
	        String productName = "" ;
	        try {
	    	
	        JSONObject productJSON = new JSONObject(productApiResponseJSON);	
	    	JSONObject productcomponsiteJson = new JSONObject (productJSON.get("product_composite_response").toString());
	    	JSONArray itemsArray = (JSONArray) productcomponsiteJson.get("items");
	    	JSONObject description = (JSONObject) itemsArray.get(0);
	    	productName = (String) description.get("general_description");
	    	log.info("productcomponsiteJson items Array is "+description.get("general_description"));
	    	
	        } 
	        catch (JSONException e) {
	    	e.printStackTrace();
	        }  */
	        System.out.println("productApiPojo is "+productApiPojo.getProductCompositeResponse().getItems().get(0).getGeneralDescription());
	        productName = productApiPojo.getProductCompositeResponse().getItems().get(0).getGeneralDescription();
		return productName;
		
	}
	
	public boolean insertNewProduct(Map<String, Object> insertProductPricingMap)
	{
		try{
			System.out.println("Product Pricing Map to be inserted is"+insertProductPricingMap);
			
			
			Map<String, String> insertProductPricingStringMap = (Map) insertProductPricingMap;
			System.out.println("insertProductPricingStringMap is "+insertProductPricingStringMap);
			 /*ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			 ProductPricing productPricing = mapper.convertValue(insertProductPricingMap, ProductPricing.class);
			System.out.println("productPricing in inserNewProduct Id is "+productPricing.getId());*/
			
			//Test data Insert
			//repository.save(new Product("16483599", 95.99, "USD"));
			for (Product product : repository.findAll()) {
				System.out.println(product);
			}
			System.out.println();
			
			//repository.save(new Product(insertProductPricing.getId(), insertProductPricing.getCurrentPrice().getValue(), insertProductPricing.getCurrentPrice().getCurrencyCode()));
			return true;

		   }
      catch(Exception error)
		{
	      System.out.println("Exception"+error);  
    	  return false;
		}
		
	}
	
	
	
    
}
