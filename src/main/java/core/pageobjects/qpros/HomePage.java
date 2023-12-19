package core.pageobjects.qpros;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class HomePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Commons com;

	private static final By cartText = By.xpath("//a[@title='View your shopping cart']//span[@class='cartcontents']");

	@FindBy(xpath = "//img[@alt='Shop Selenium Books']")
	private WebElement mainImage;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 20);
		com = Commons.initialize(this.driver);
	}

	public static HomePage initialize(WebDriver driver) {
		return PageFactory.initElements(driver, HomePage.class);
	}

	public void addToBasketButton(String bookName) {
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[contains(@href,'" + bookName + "')]//following-sibling::a[text()='Add to basket']")));
		String textInCart = null;
		com.scrollDown();
		try {
			textInCart = com.getTextInCart();
		} catch (NoSuchElementException e) {
			textInCart = null;
		}
		driver.findElement(
				By.xpath("//a[contains(@href,'" + bookName + "')]//following-sibling::a[text()='Add to basket']"))
				.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//a[contains(@href,'" + bookName + "')]//following-sibling::a[@title='View Basket']")));
		wait.until(ExpectedConditions.invisibilityOfElementWithText(cartText, textInCart));
	}

	public void viewBasketButton(String bookName) {
		com.scrollToBottom();
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[contains(@href,'" + bookName + "')]//following-sibling::a[@title='View Basket']")));
		driver.findElement(
				By.xpath("//a[contains(@href,'" + bookName + "')]//following-sibling::a[@title='View Basket']"))
				.click();
	}

	public String getBookTitle(String bookName) {
		return driver.findElement(By.xpath("//a[contains(@href,'" + bookName + "')]/h3")).getText();
	}

	public String getBookSalePrice(String bookName) {
		return driver
				.findElement(By.xpath(
						"//a[contains(@href,'" + bookName + "')]//ins/span[@class='woocommerce-Price-amount amount']"))
				.getText();
	}

	public String getBookMainPrice(String bookName) {
		String price = null;
		try {
			price = driver.findElement(By.xpath(
					"//a[contains(@href,'" + bookName + "')]//del/span[@class='woocommerce-Price-amount amount']"))
					.getText();
		} catch (NoSuchElementException e) {
			price = driver.findElement(By.xpath(
					"//a[contains(@href,'" + bookName + "')]//ins/span[@class='woocommerce-Price-amount amount']"))
					.getText();
		}
		return price;
	}

	public boolean isBookOnSale(String bookName) {
		boolean bookDiscounted = false;
		try {
			driver.findElement(By.xpath("//a[contains(@href,'" + bookName + "')]/span[@class='onsale']"));
			bookDiscounted = true;
		} catch (NoSuchElementException e) {
			bookDiscounted = false;
		}
		return bookDiscounted;
	}

	public boolean isBookExist(String bookName) {
		boolean bookExist = false;
		try {
			driver.findElement(By.xpath("//a[contains(@href,'" + bookName + "')]/h3"));
			bookExist = true;
		} catch (NoSuchElementException e) {
			bookExist = false;
		}
		return bookExist;
	}

	public boolean compareBookImage(String bookName, String expected) throws MalformedURLException, IOException {
		BufferedImage expectedImage = ImageIO.read(new File("./src/test/resources/images/" + expected));
		WebElement imageElement = driver.findElement(By.xpath("//a[contains(@href,'" + bookName + "')]//img"));
		String source = imageElement.getAttribute("src");
		BufferedImage bufferedImage = ImageIO.read(new URL(source));
		File outputfile = new File("saved.png");
		ImageIO.write(bufferedImage, "JPG", outputfile);
		ImageDiffer imgDif = new ImageDiffer();
		ImageDiff diff = imgDif.makeDiff(expectedImage, bufferedImage);
		Assert.assertFalse(diff.hasDiff(), "Images are same");
		return false;
	}

	public int getNoItems() {
		return com.getItemsNoInCart();
	}

	public void load() {
		com.navigateHomePage();
	}

	public void isLoaded() throws Error {
		Assert.assertTrue(driver.getCurrentUrl().contains("practice.automationtesting.in"));
	}

	public void waitForPageLoading() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Shop Selenium Books']")));
	}
}
