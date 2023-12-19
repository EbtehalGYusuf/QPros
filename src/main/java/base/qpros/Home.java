package base.qpros;

import java.io.IOException;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.pageobjects.qpros.Commons;
import core.pageobjects.qpros.ExtentReportsManager;
import core.pageobjects.qpros.HomePage;
import io.qameta.allure.Step;

public class Home {
	HomePage homePage;
	Commons com;
	WebDriver driver;
	protected WebDriverWait wait;

	public Home(WebDriver driver) {
		homePage = new HomePage(driver);
		com = new Commons(driver);
	}

	@Step("Go To Home Page")
	public void goTo() {
		homePage.load();
		homePage.isLoaded();
	}

	@Step("Add book {0} to Basket")
	public void clickAddToBasketButton(String bookName) {
		homePage.addToBasketButton(bookName);
		ExtentReportsManager.info("Adding " + bookName + " in cart");
	}

	public String getBookTitle(String bookName) {
		return homePage.getBookTitle(bookName);
	}

	public String getBookSalePrice(String bookName) {
		return homePage.getBookSalePrice(bookName);
	}

	public String getBookMainPrice(String bookName) {
		return homePage.getBookMainPrice(bookName);
	}

	public int getNoItems() {
		return homePage.getNoItems();
	}

	public boolean isBookOnSale(String bookName) {
		return homePage.isBookOnSale(bookName);
	}

	public boolean isBookExist(String bookName) {
		homePage.waitForPageLoading();
		return homePage.isBookExist(bookName);
	}

	public boolean compareBookImage(String bookName, String expected) throws MalformedURLException, IOException {
		return homePage.compareBookImage(bookName, expected);
	}
}
