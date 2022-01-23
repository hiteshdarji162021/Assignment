package com.aspire.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aspire.base.WebPlatformHandler;

public class Page_InventoryProduct extends WebPlatformHandler {

	private By lnkInventory = By.xpath("//*[text()='Inventory']");
	private By mnuProd = By.xpath("//a[contains(text(),'Products')]");
	private By mnuoptProd = By.xpath("//*[contains(text(),'Packages')]//preceding :: span[text()='Products']");
	private By btncrtprod = By.xpath("//*[contains(text(),'Create')]");
	private By txtProdName = By.xpath("//input[@placeholder='Product Name']");
	private By txtSalesPrice = By.xpath("//label[text()='Cost']//ancestor :: tr//input");
	private By txtecost = By.xpath("//label[text()='Sales Price']//ancestor :: tr//input");
	private By txtunit = By.xpath("//label[text()='Unit of Measure']//ancestor :: tr//input");
	private By tabInventory = By.xpath("//a[text()='Inventory' and @role='tab']");
	private By chklblRoutes = By.xpath("//*[text()='WH/MO/01243']//preceding-sibling :: input");
	private By txtWeight = By.xpath("//label[text()='Weight']//ancestor :: tr//input");
	private By txtVolume = By.xpath("//label[text()='Volume']//ancestor :: tr//input");
	private By txtManuleadTime = By.xpath("//label[text()='Manufacturing Lead Time']//ancestor :: tr//input");
	private By txtCustleadTime = By.xpath("//label[text()='Customer Lead Time']//ancestor :: tr//input");
	private By lnkaddpackage = By.xpath("(//a[text()='Add a line'])[2]");
	private By txtPackName = By.xpath("//label[text()='Packaging']//ancestor :: div//h1//input");
	private By txtqty = By.xpath("//label[text()='Contained quantity']//ancestor :: tr//td//input");
	private By txtbarcode = By.xpath("(//label[text()='Barcode']//ancestor :: tr//td//input)[2]");
	private By btnSaveClose = By.xpath("//span[text()='Save & Close']");
	private By btnSave = By.xpath("//button[contains(text(),'Save')]");
	public static String prodName;

	public void CreateProduct() {

		clickOnElementIfClickable(lnkInventory, "Inventory Link", 5);
		clickOnElementIfClickable(mnuProd, "Menu", 5);
		clickOnElementIfClickable(mnuoptProd, "Menu option", 5);
		clickOnElementIfClickable(btncrtprod, "Create Product Button", 5);
		prodName = propproduct.getProperty("ProductName") + timestamp();
		enterTextInTextboxAfterClearingExistingText(txtProdName, prodName, "Product Name", 10);

		enterTextInTextboxAfterClearingExistingText(txtSalesPrice, propproduct.getProperty("Price"), "Product Price",
				10);
		enterTextInTextboxAfterClearingExistingText(txtecost, propproduct.getProperty("Cost"), "Product Cost", 10);
		enterTextInTextboxAfterClearingExistingText(txtunit, propproduct.getProperty("UnitofMeasure"), "Unit of Masure",
				10);
		clickOnElementIfClickable(tabInventory, "Inventory Tab", 5);
		clickOnElementIfClickable(chklblRoutes, "Route Checkbox", 5);
		enterTextInTextboxAfterClearingExistingText(txtWeight, propproduct.getProperty("Weight"), "Weight", 10);
		enterTextInTextboxAfterClearingExistingText(txtVolume, propproduct.getProperty("Volume"), "Volume", 10);
		enterTextInTextboxAfterClearingExistingText(txtManuleadTime, propproduct.getProperty("ManuleadTime"),
				"ManuleadTime", 10);
		enterTextInTextboxAfterClearingExistingText(txtCustleadTime, propproduct.getProperty("CustleadTime"),
				"CustleadTime", 10);
		clickOnElementIfClickable(lnkaddpackage, "Create Package", 5);
		enterTextInTextboxAfterClearingExistingText(txtPackName, propproduct.getProperty("PackageName") + timestamp(),
				"Package Name", 10);
		enterTextInTextboxAfterClearingExistingText(txtqty, propproduct.getProperty("prodQty"), "Product Qty", 10);
		enterTextInTextboxAfterClearingExistingText(txtbarcode, propproduct.getProperty("ProdBar"), "Product Barcode",
				10);
		clickOnElementIfClickable(btnSaveClose, "Save and close Package Detail", 5);
		clickOnElementIfClickable(btnSave, "Save Product", 5);

	}

	public boolean checkProductStatus() {

		By status = By.xpath("//button[@class='btn btn-primary o_form_button_edit']");
		waitmethod(status, "checkstatus", 10);
		String actualStatus = driver.findElement(By.xpath("//button[@class='btn btn-primary o_form_button_edit']"))
				.getText();
		String expectedStatus = propproduct.getProperty("status");
		if (actualStatus.contains(expectedStatus)) {
			return true;

		} else {
			return false;
		}
	}

}
