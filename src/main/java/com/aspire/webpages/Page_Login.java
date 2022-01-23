package com.aspire.webpages;



import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;

import com.aspire.base.WebPlatformHandler;

public class Page_Login extends WebPlatformHandler {

	// Login page WebElement

	private By txtEmail = By.xpath("//input[@id='login']");
	private By txtPassword = By.xpath("//input[@id='password']");
	private By btnLogin = By.cssSelector("button.btn ");
	private By lnkInventory = By.xpath("//*[text()='Inventory']");

	//Enter userName
	public void setEmail() {

		enterTextInTextboxAfterClearingExistingText(txtEmail, propconfig.getProperty("Account"), "Email iD", 10);
		System.out.println("Email id entered successfully");

	}

	//Enter Password
	public void setPassword() {

		enterTextInTextboxAfterClearingExistingText(txtPassword, propconfig.getProperty("Password"), "Email iD", 10);
		System.out.println("Password entered successfully");

	}

	//Click on Login button
	public void clickLogin() {

		clickOnElementIfClickable(btnLogin, "Login button", 10);
	}
	
	//Wait for Assertion of Inventory link.
	public boolean waitForDashboard() {

			return waitmethod(lnkInventory, "Inventroylink",10);
			
		
	}

}
