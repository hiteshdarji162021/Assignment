package com.aspire.base;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebPlatformHandler {

	protected static WebDriver driver;
	public static Properties prop;	
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;

	// For load Properties
	public static void loadPropertiyFile(String fileName) throws Exception {

		File file = new File("./src/test/resources/com/aspire/config/" + fileName + ".properties");
		FileInputStream fis = new FileInputStream(file);
		prop = new Properties();

		try {
			prop.load(fis);
			System.out.println("prop file loaded successfully");
		} catch (IOException e) {
			System.out.println("exception occured during load prop file " + e);
		}

	}

	
	// For Create Driver
	public static void createDriver(String browserName) {

		try {
			String getOsName = prop.getProperty("OSName");

			if (getOsName.toLowerCase().contains(prop.getProperty("OSName").toLowerCase())) {

				if (browserName.toLowerCase().contains("chrome".toLowerCase())) {

					WebDriverManager.chromedriver().setup();

					driver = new ChromeDriver();

				} else if (browserName.toLowerCase().contains("Firefox".toLowerCase())) {

					WebDriverManager.firefoxdriver().setup();

					driver = new FirefoxDriver();

				} else {
					
					WebDriverManager.edgedriver().setup();

					driver = new EdgeDriver();

					

				}

			} else if (browserName.toLowerCase().contains("chrome".toLowerCase())) {

				WebDriverManager.chromedriver().setup();

				driver = new ChromeDriver();

			} else if (browserName.toLowerCase().contains("Firefox".toLowerCase())) {

				WebDriverManager.firefoxdriver().setup();

				driver = new FirefoxDriver();

			} else {

				driver = new SafariDriver();

			}
		} catch (Exception e) {
			System.out.println("driver is not created may be browser is crashed or driver value is null");
		}
	}

	// For Open URL
	public static void openURL(String browserurl) {

		try {
			driver.manage().window().maximize();
			driver.get(browserurl);

		} catch (Exception e) {
			System.out.println("unable to fetch url");
		}
	}

	// For verify Page Title
	protected String verifypage() {

		String actualPageTitle = null;

		try {

			actualPageTitle = driver.getTitle();

			System.out.println("Actual title is: " + actualPageTitle);

		} catch (Exception e) {
			System.out.println("Unable to fetch page title");

		}
		return actualPageTitle;
	}

	protected boolean clickOnElementIfClickable(By locator, String elementName, long waitTimeInSecond) {

		boolean clickOnElement = false;

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSecond));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
			System.out.println("Successfully clicked on: " + elementName);
			clickOnElement = true;

		} catch (Exception e) {
			System.out.println("Unable to click on: " + elementName);
		}
		return clickOnElement;
	}

	protected boolean clearTextFromTextbox(By locator, String elementName, long waitTimeInSecond) {

		boolean isTextCleared = false;

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSecond));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element.clear();

			System.out.println("Successfully cleared text from: " + elementName);
			isTextCleared = true;

		} catch (Exception e) {

			System.out.println("Unable to locate element: " + elementName);

		}
		return isTextCleared;

	}

	protected WebElement addTextInTextbox(By locator, String getText, String elementName, long waitTimeInSecond) {

		WebElement eleGetText = null;

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSecond));
			eleGetText = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			eleGetText.sendKeys(getText);
			System.out.println("Enter text: " + getText + "in textbox.");
			return eleGetText;

		} catch (Exception e) {

			System.out.println("Unable to enter text: " + getText + "in" + elementName + "textbox");

		}
		return eleGetText;
	}

	// For Enter text(Merger of all 3 above methods
	protected boolean enterTextInTextboxAfterClearingExistingText(By locator, String getText, String elementName,
			long waitTimeInSecond) {
		boolean isEnteredSearchText = false;

		try {
			clickOnElementIfClickable(locator, elementName, waitTimeInSecond);
			clearTextFromTextbox(locator, elementName, waitTimeInSecond);
			addTextInTextbox(locator, getText, elementName, waitTimeInSecond);
			isEnteredSearchText = true;

		} catch (Exception e) {
			System.out.println("Unable to enter text: " + getText + "in" + elementName + "textbox");

		}
		return isEnteredSearchText;

	}

	public boolean waitmethod(By Element, String ElementName, long seconds) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Element));
		System.out.println(ElementName + "is visible");
		return true;

	}

	// For Take Screenshot
	public String screenshot() {
		String test=null;
		
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File srcPath = ts.getScreenshotAs(OutputType.FILE);
			String ScreenshotName = timestamp();
			test="./screenshots/" + ScreenshotName + ".png";
			String DestPath = "./Report/screenshots/" + ScreenshotName + ".png";
			File destPath = new File(DestPath);
			FileUtils.copyFile(srcPath, destPath);
			System.out.println("Screenshot taken");

		} catch (Exception e) {
			System.out.println("Unable to takeScreenshot");
		}
		return test;
	}

	// For get timestamp
	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
	}

	public void teardown() {

		driver.quit();
		extent.flush();
	}

}
