package base.qpros;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.pageobjects.qpros.CartPage;
import core.pageobjects.qpros.Commons;
import core.pageobjects.qpros.HomePage;
import io.qameta.allure.Step;
import core.pageobjects.qpros.ExtentReportsManager;

public class Cart {
	HomePage homePage;
	Commons com;
	WebDriver driver;
	CartPage cartPage;
	protected WebDriverWait wait;

	public Cart(WebDriver driver) {
		this.driver = driver;
		homePage = new HomePage(driver);
		com = new Commons(driver);
		cartPage = new CartPage(driver);
	}

	public void goTo() {
		cartPage.load();
		cartPage.isLoaded();
		cartPage.waitForPageLoading();
		ExtentReportsManager.info("Navigating to Cart Page");
	}

	public boolean isBookExistinCart(String bookName) {
		return cartPage.isBookExistinCart(bookName);
	}

	public void clickOnBookLink(int rowIndex) {
		cartPage.clickOnBookLink(rowIndex);
		ExtentReportsManager.info("Clicking on Book Link in Cart");
	}

	public String getTableCellValue(int rowIndex, int columnIndex) {
		return cartPage.getTableCellValue(rowIndex, columnIndex);
	}

	public void clickDeleteBook(int rowIndex) {
		cartPage.deleteBook(rowIndex);
		ExtentReportsManager.info("Clicking on Delete Book Icon");

	}

	@Step("Proceeding to checkout")
	public void clickProceedToCheckOut() {
		cartPage.clickProceedToCheckOut();
		ExtentReportsManager.info("Clicking on Proceed To Checkout");

	}
}
