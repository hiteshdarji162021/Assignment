package com.aspire.testcases;


import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.aspire.webpages.Page_InventoryProduct;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TC02_InventoryProduct extends Page_InventoryProduct {

	@Test(priority = 1, dependsOnGroups = "Pre-Condtion", groups = { "ProductCreation" })
	public void ProductCreated() {
		try {
			test = extent.createTest("Product Created");
			CreateProduct();			
			assertTrue(checkProductStatus());
			screenshot();
			System.out.println("Inventory TestCase Pass");
			test.pass("Inventory TestCase Pass",MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build()); 
			
		} catch (Exception e) {
			
			screenshot();
			System.out.println("Inventory TestCase Failed");
			test.fail("Inventory TestCase Failed",MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build()); 
			
		}	

	}

}
