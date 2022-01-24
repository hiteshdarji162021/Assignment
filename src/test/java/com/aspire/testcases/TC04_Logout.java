package com.aspire.testcases;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.aspire.webpages.Page_Logout;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TC04_Logout extends Page_Logout {

	TC03_Manufacturing objManufacturing = new TC03_Manufacturing();

	//TestCase of Log out
	@Test(priority = 3, dependsOnGroups = { "Pre-Condtion" })
	public void Logout() {
		try {
			test = extent.createTest("Logout");
			logout();
			assertTrue(logoutstatus());
			screenshot();
			System.out.println("Logout Testcase Pass");
			test.pass("Manufactring TestCase Pass",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build());

		} catch (Exception e) {

			screenshot();
			System.out.println("Logout Testcase Failed");
			test.fail("Manufactring TestCase Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build());
		} finally {
			teardown();
		}

	}

}
