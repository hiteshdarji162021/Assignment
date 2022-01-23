package com.aspire.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aspire.base.WebPlatformHandler;

public class Page_Manufacturing extends WebPlatformHandler {

	Page_InventoryProduct objInvt;

	private By navDashboard = By.xpath("//a[@title='Applications']");
	private By lnkManuf = By.xpath("//div[text()='Manufacturing']");
	private By btnCreat = By.xpath("//button[contains(text(),'Create')]");
	private By txtProdName = By.xpath("(//*[text()='Product'])[1]/ancestor :: tr//input");
	private By txtQty = By.xpath("((//*[text()='Quantity'])[1]/ancestor :: tr//input)[2]");
	private By lnkline = By.xpath("(//*[text()='Add a line'])[1]");
	private By txtProduct = By.xpath("(//tr[@class='o_data_row o_selected_row']//input)[1]");
	private By btnCreate = By.xpath("//*[text()='Create']");
	private By txtToConsue = By.xpath("//input[@name='product_uom_qty']");
	private By btnSave = By.xpath("//button[contains(text(),'Save')]");
	private By btnConfirm = By.xpath("//*[text()='Confirm']");
	private By btnDone = By.xpath("//*[text()='Mark as Done']");
	private By btnApply = By.xpath("//*[text()='Apply']");

	

	public void Manufactur() throws Exception {

		// *[text()='Test_ProductName2022-01-23-17-17-02']

		objInvt = new Page_InventoryProduct();
		By DropProdName = By.xpath("//li/a[text()='" + objInvt.prodName + "']");
		By DropProdName1 = By.xpath("(//li/a[text()='" + objInvt.prodName + "'])[2]");

		clickOnElementIfClickable(navDashboard, "navigate Dashboard", 5);
		clickOnElementIfClickable(lnkManuf, "navigate Dashboard", 5);
		clickOnElementIfClickable(btnCreat, "navigate Dashboard", 5);
		enterTextInTextboxAfterClearingExistingText(txtProdName, objInvt.prodName, "Product Name", 10);
		clickOnElementIfClickable(DropProdName, "Selected Product", 5);
		enterTextInTextboxAfterClearingExistingText(txtQty, propmanuf.getProperty("ProdQty"), "Product Qty", 10);
		clickOnElementIfClickable(lnkline, "Add product in line", 5);
		enterTextInTextboxAfterClearingExistingText(txtProduct, objInvt.prodName, "Product in line", 10);
		clickOnElementIfClickable(DropProdName1, "Selected Product", 5);

		clickOnElementIfClickable(btnCreate, "Create button", 5);
		enterTextInTextboxAfterClearingExistingText(txtToConsue, propmanuf.getProperty("Consume"), "Consume", 10);
		clickOnElementIfClickable(btnSave, "Save button", 5);
		Thread.sleep(2000);
		clickOnElementIfClickable(btnConfirm, "Confirm button", 5);
		clickOnElementIfClickable(btnDone, "Done button", 5);
		clickOnElementIfClickable(btnApply, "Apply button", 5);
	}

	public boolean chkstatuss() {
		By status1 = By.xpath("//button[contains(text(),'Done') and  @title='Current state']");
		waitmethod(status1, "checkstatus1", 10);
		By status2 = By.xpath("//span[@name='qty_producing']");
		waitmethod(status2, "checkstatus2", 10);
		String actualStatus = driver.findElement(By.xpath("//*[@title='Current state']")).getText();
		String expectedStatus = propmanuf.getProperty("status");
		String expectedQty = propmanuf.getProperty("ProdQty");
		String actualQty = driver.findElement(By.xpath("//span[@name='qty_producing']")).getText();
		if (actualStatus.contains(expectedStatus) && (actualQty.contains(expectedQty))) {
			return true;
		} else {
			return false;
		}
	}




}
