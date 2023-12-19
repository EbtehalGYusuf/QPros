package base.qpros;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import core.pageobjects.qpros.BillingDetailsPage;
import core.pageobjects.qpros.CartPage;
import core.pageobjects.qpros.Commons;
import core.pageobjects.qpros.ExtentReportsManager;
import core.pageobjects.qpros.HomePage;

public class BillingDetails {
	HomePage homePage;
	Commons com;
	WebDriver driver;
	CartPage cartPage;
	BillingDetailsPage bill;
	protected WebDriverWait wait;

	public BillingDetails(WebDriver driver) {
		this.driver = driver;
		bill = new BillingDetailsPage(driver);
		com = new Commons(driver);
		cartPage = new CartPage(driver);
	}

	public String getSelectedOptionCountryMenu() {
		return bill.getSelectedOptionCountryMenu();
	}
	
	public String getSelectedOptionStateMenu() {
		return bill.getSelectedOptionStateMenu();
	}

	public void openCountryMenu() {
		bill.openCountryMenu();
	}

	public void openStateMenu() {
		bill.openStateMenu();
		ExtentReportsManager.info("Opening State Menu");
	}

	public void selectCountryMenuOption(String searchWord) {
		bill.openCountryMenu();
		bill.search(searchWord);
		bill.selectMenuOption(searchWord);
		ExtentReportsManager.info("Select " + searchWord + " from Country menu");
	}
	
	public void selectStateMenuOption(String searchWord) {
		bill.openStateMenu();
		bill.search(searchWord);
		bill.selectMenuOption(searchWord);
		ExtentReportsManager.info("Select " + searchWord + " from State menu");
	}

	public void insertFirstName (String text) {
		bill.insertFirstName(text);
		ExtentReportsManager.info("Inserting " + text + "  in First Name Field");
	}

	public String getFirstNameText() {
		return bill.getFisrtNameText();
	}
	
	public void insertLastName (String text) {
		bill.insertLastName(text);
		ExtentReportsManager.info("Inserting " + text + "  in Last Name Field");
	}

	public String getLastNameText() {
		return bill.getFisrtNameText();
	}
	
	public void insertCompany (String text) {
		bill.insertCompany(text);
		ExtentReportsManager.info("Inserting " + text + "  in Company Field");
	}

	public String getCompanyText() {
		return bill.getCompanyText();
	}
	
	public void insertEmail (String text) {
		bill.insertEmail(text);
		ExtentReportsManager.info("Inserting " + text + "  in Email Field");
	}

	public String getEmailText() {
		return bill.getEmailText();
	}
	
	public void insertPhone (String text) {
		bill.insertPhone(text);
		ExtentReportsManager.info("Inserting " + text + " in Phone Field");
	}

	public String getPhoneText() {
		return bill.getPhoneText();
	}
	
	public void insertAddress (String text) {
		bill.insertAddress(text);
		ExtentReportsManager.info("Inserting " + text + " in Address Field");
	}

	public String getAddressText() {
		return bill.getAddressText();
	}
	
	public void insertCity (String text) {
		bill.insertCity(text);
		ExtentReportsManager.info("Inserting " + text + " in City Field");
	}

	public String getCityText() {
		return bill.getCityText();
	}
	
	public void insertPostcode (String text) {
		bill.insertPostcode(text);
		ExtentReportsManager.info("Inserting " + text + " in PostCode Field");
	}

	public String getPostcodeText() {
		return bill.getPostcodeText();
	}
	
	public void insertState(String text) {
		bill.insertState(text);
		ExtentReportsManager.info("Inserting " + text + " in State Field");
	}

	public String getStateText() {
		return bill.getStateText();
	}
	
	public String getTableCellValue(int rowIndex, int columnIndex) {
		return bill.getTableCellValue(rowIndex, columnIndex);
	}
//
//	public void load() {
//		com.navigateCartPage();
//	}

	public void isLoaded() throws Error {
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
	}

	public void waitForPageLoading() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Site Logo']")));
	}
}
