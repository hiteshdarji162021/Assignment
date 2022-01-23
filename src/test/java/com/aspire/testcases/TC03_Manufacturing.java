package com.aspire.testcases;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.aspire.webpages.Page_Manufacturing;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TC03_Manufacturing extends Page_Manufacturing{
	
	TC02_InventoryProduct objInventoryProduct = new TC02_InventoryProduct();
	
	@Test(priority = 2, dependsOnGroups = {"ProductCreation"})
	public void manufacturingOrderCreated() {
		try {
			test = extent.createTest("Order Manufactured");
			Manufactur();
			assertTrue(chkstatuss());
			screenshot();
			System.out.println("Manufactring TestCase Pass");
			test.pass("Manufactring TestCase Pass",MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build()); 
			
		} catch (Exception e) {

			
			screenshot();
			System.out.println("Manufactring TestCase Failed");
			test.fail("Manufactring TestCase Failed",MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build()); 
		}	

	}

}
