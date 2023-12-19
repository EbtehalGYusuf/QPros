package core.pageobjects.qpros;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Commons {

	private final WebDriver driver;
	private final WebDriverWait wait;

	@FindBy(xpath = "//a[@title='View your shopping cart']")
	protected WebElement viewCartMenuButton;

	@FindBy(xpath = "//a[text()='My Account']")
	private WebElement MyAccountButton;

	@FindBy(xpath = "//div[@id='site-logo']")
	private WebElement PageLogo;

	@FindBy(xpath = "//div[@aria-label='Close ad']")
	private WebElement ad;

	public Commons(WebDriver webdriver) {
		driver = webdriver;
		wait = new WebDriverWait(this.driver, 20);
	}

	public static Commons initialize(WebDriver driver) {
		return PageFactory.initElements(driver, Commons.class);
	}

	public int getItemsNoInCart() {
		int cartItemsCount = Integer.parseInt(viewCartMenuButton.findElement(By.xpath("//span[@class='cartcontents']"))
				.getText().replaceAll("[^0-9]", ""));
		return cartItemsCount;
	}

	public String getTextInCart() {
		return viewCartMenuButton.findElement(By.xpath("//span[@class='cartcontents']")).getText();
	}

	public void navigateHomePage() {
		wait.until(ExpectedConditions.visibilityOf(PageLogo));
		PageLogo.click();
	}

	public void navigateCartPage() {
		wait.until(ExpectedConditions.visibilityOf(viewCartMenuButton));
		viewCartMenuButton.click();
		wait.until(ExpectedConditions.urlContains("basket"));
	}

	// General
	public boolean isElementDisplayed(WebElement element) {
		boolean result = false;
		try {
			if (element.isDisplayed()) {
				result = true;
			}
			return result;
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return result;
		}
	}

	public void scrollToBottom() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollDown() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,500)");
	}

}
