package com.aspire.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aspire.base.WebPlatformHandler;
import com.aspire.webpages.Page_Login;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TC01_Login extends Page_Login {

	@BeforeSuite
	public void setUp() {

		try {

			extent = new ExtentReports();
			spark = new ExtentSparkReporter("./Report/AspireReport.html");
			extent.attachReporter(spark);
			WebPlatformHandler.loadPropertiyFileconfig("config");
			WebPlatformHandler.createDriver(propconfig.getProperty("Browser"));
			WebPlatformHandler.openURL(propconfig.getProperty("URL"));
			WebPlatformHandler.loadPropertiyFileproductDetail("ProductDetails");
			WebPlatformHandler.loadPropertiyFileManufacturing("Manufacturingdetail");

		} catch (Exception e) {

			System.out.println("Unable to setup Browser factory");
		}

	}

	@Test(priority = 0, groups = { "Pre-Condtion" })
	public void aspireLogin() {

		try {
			test = extent.createTest("Login Aspire");

			setEmail();
			setPassword();
			clickLogin();			
			assertTrue(waitForDashboard());
			screenshot();
			System.out.println("Login successfully");
			test.pass("Login Successfully", MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build()); 
		} catch (Exception e) {
			screenshot();
			System.out.println("Login TestCase Failed");
			test.fail("Unable to login",MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build()); 
			assertTrue(false);

		}

	}

}
