package com.tgt.app;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgt.response.CurrentPrice;
import com.tgt.response.ProductPricing;

/**
 * This controller provides the REST methods
 */
@RestController
//@RequestMapping("/products/{userId}")
public class ProductPricingController  {
	
	// Spring DI inject the service 
    @Autowired
    private ProductService productService;

	@RequestMapping(value= "/products/{userId}",method = RequestMethod.GET)
    public ProductPricing productPricing(@PathVariable(value="userId") String Id) throws Exception {
	System.out.println("ID is "+Id);
	ProductPricing productPricing= productService.searchProductPrice(Id.toString());
    	//{"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}//
    	return productPricing;
    }
	
	//inserting product Price to Mongo DB: Pay Load JSON data will be inserted
	@RequestMapping(value= "/products/{userId}",method = RequestMethod.POST)
	public String productPricing(@PathVariable(value="userId") String Id, @RequestBody Map<String, Object> insertProductPricing ) throws Exception {
		System.out.println("ID is "+Id);
		if( productService.insertNewProduct(insertProductPricing));
		
	    	//{"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}//
	  	  System.out.println("insertProductPricing in controller is "+insertProductPricing);

	    	return "success";
	    }
    
}
