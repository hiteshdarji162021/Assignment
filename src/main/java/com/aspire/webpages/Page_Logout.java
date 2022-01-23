package com.aspire.webpages;

import org.openqa.selenium.By;

import com.aspire.base.WebPlatformHandler;

public class Page_Logout extends WebPlatformHandler {

	By menuLogout = By.xpath("//img[@alt='User']");
	By drpoption = By.xpath("//*[text()='Log out']");

	public void logout() {

		clickOnElementIfClickable(menuLogout, "Menu of logout", 5);
		clickOnElementIfClickable(drpoption, "Logout link click", 5);
	}

	public boolean logoutstatus() {
		String lblText = driver.findElement(By.xpath("//*[@for='login']")).getText();
		if (lblText.contains("Email")) {
			return true;
		} else {
			return false;
		}
	}

}
