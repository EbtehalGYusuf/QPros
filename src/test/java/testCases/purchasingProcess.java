package testCases;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.qpros.BillingDetails;
import base.qpros.Cart;
import base.qpros.Home;
import configurations.configurationTest;
import core.pageobjects.qpros.Commons;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import core.pageobjects.qpros.ExtentReportsManager;

@Feature("Purchase Book")
@Story("Purchasing Book Story")

public class purchasingProcess extends configurationTest {
	Home homePage;
	Commons com;
	Cart cartPage;
	BillingDetails billPage;

	@BeforeMethod
	@Parameters("browser")
	public void purchasingProcessBeforeMethod(String browser) {
		configurationTest.initializeDriver(browser);
		configurationTest.openWebSite("https://practice.automationtesting.in/");
		homePage = new Home( getDriver());
		cartPage = new Cart( getDriver());
		billPage = new BillingDetails( getDriver());
		homePage.goTo();
	}

	@Test(enabled = true, description = "Home Page BoOk Validation")
	public void case01_mainPageBookValidation() throws MalformedURLException, IOException {
		ExtentReportsManager.createTest("Case01: Home Page Book Validation");
		Assert.assertTrue(homePage.isBookExist("thinking"));
		homePage.clickAddToBasketButton("thinking");
		Assert.assertEquals(homePage.getBookSalePrice("thinking"), "₹400.00");
		Assert.assertEquals(homePage.getBookMainPrice("thinking"), "₹450.00");
		Assert.assertEquals(homePage.getNoItems(), 1);
		ExtentReportsManager.pass("Book is properly displayed in Home Page");
	}

	@Test(enabled = true, description = "Purchasing Single Book")
	public void case02_purchasingSingleBook() throws MalformedURLException, IOException {
		ExtentReportsManager.createTest("Case02: Adding Single Book in Cart");
		Assert.assertTrue(homePage.isBookExist("thinking"));
		homePage.clickAddToBasketButton("thinking");
		Assert.assertEquals(homePage.getNoItems(), 1);
		cartPage.goTo();
		Assert.assertEquals(cartPage.getTableCellValue(1, 3), "Thinking in HTML");
		Assert.assertTrue(cartPage.isBookExistinCart("Thinking in HTML"));
		ExtentReportsManager.pass("Book is added to Cart");
	}

	@Test(enabled = true, description = "Purchasing Same Book Multiple Times")
	public void case03_purchasingSameBookMultipleTimes() throws MalformedURLException, IOException {
		ExtentReportsManager.createTest("Case03: Adding Single Book multiple times in Cart");
		Assert.assertTrue(homePage.isBookExist("thinking"));
		homePage.clickAddToBasketButton("thinking");
		homePage.clickAddToBasketButton("thinking");
		Assert.assertEquals(homePage.getNoItems(), 2);
		cartPage.goTo();
		Assert.assertEquals(cartPage.getTableCellValue(1, 3), "Thinking in HTML");
		Assert.assertTrue(cartPage.isBookExistinCart("Thinking in HTML"));
		Assert.assertEquals(cartPage.getTableCellValue(1, 5), "2");
		ExtentReportsManager.pass("Book is added to Cart page with the required quantity");
	}

	@Test(enabled = true, description = "Purchasing Different Books")
	public void case04_purchasingDifferentBooks() throws MalformedURLException, IOException {
		ExtentReportsManager.createTest("Case04: Adding different books in Cart");
		Assert.assertTrue(homePage.isBookExist("thinking"));
		Assert.assertTrue(homePage.isBookExist("mastering"));
		homePage.clickAddToBasketButton("mastering");
		homePage.clickAddToBasketButton("thinking");
		Assert.assertEquals(homePage.getNoItems(), 2);
		cartPage.goTo();
		Assert.assertEquals(cartPage.getTableCellValue(1, 3), "Mastering JavaScript");
		Assert.assertEquals(cartPage.getTableCellValue(2, 3), "Thinking in HTML");
		Assert.assertEquals(cartPage.getTableCellValue(1, 4), "₹350.00");
		Assert.assertEquals(cartPage.getTableCellValue(2, 4), "₹400.00");
		Assert.assertEquals(cartPage.getTableCellValue(1, 5), "1");
		Assert.assertEquals(cartPage.getTableCellValue(2, 5), "1");
		Assert.assertTrue(cartPage.isBookExistinCart("Mastering JavaScript"));
		Assert.assertTrue(cartPage.isBookExistinCart("Thinking in HTML"));
		ExtentReportsManager.pass("Different books appear in Cart Page properly");
	}

	@Test(enabled = true, description = "Purchasing Same Book Multiple Times with Proceeding To Checkout")
	public void case05_purchasingDifferentBooks() throws MalformedURLException, IOException {
		ExtentReportsManager.createTest("Case05: Proceeding to checkout after adding single book multiple times");
		Assert.assertTrue(homePage.isBookExist("thinking"));
		homePage.clickAddToBasketButton("thinking");
		homePage.clickAddToBasketButton("thinking");
		Assert.assertEquals(homePage.getNoItems(), 2);
		cartPage.goTo();
		cartPage.clickProceedToCheckOut();
		billPage.insertFirstName("Johny");
		billPage.insertLastName("Johny");
		billPage.insertCompany("Johny");
		billPage.insertEmail("Johny@jihny.com");
		billPage.insertPhone("010032323");
		billPage.insertAddress("Johny");
		billPage.insertCity("Johny");
		billPage.insertPostcode("121234");
		billPage.selectCountryMenuOption("Egypt");
		billPage.insertState("Johny");
		Assert.assertEquals(billPage.getFirstNameText(), "Johny");
		Assert.assertEquals(billPage.getLastNameText(), "Johny");
		Assert.assertEquals(billPage.getCompanyText(), "Johny");
		Assert.assertEquals(billPage.getEmailText(), "Johny@jihny.com");
		Assert.assertEquals(billPage.getPhoneText(), "010032323");
		Assert.assertEquals(billPage.getAddressText(), "Johny");
		Assert.assertEquals(billPage.getCityText(), "Johny");
		Assert.assertEquals(billPage.getPostcodeText(), "121234");
		Assert.assertEquals(billPage.getSelectedOptionCountryMenu(), "Egypt");
		Assert.assertEquals(billPage.getStateText(), "Johny");
		ExtentReportsManager.pass("Billing Details page contains the required data");
	}

	@AfterMethod
	public void purchasingProcessAfterMethod() {
		 getDriver().quit();
	}

	@AfterClass
	public void purchasingProcessAfterClass() {
	}
}