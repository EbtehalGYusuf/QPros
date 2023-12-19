package core.pageobjects.qpros;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Commons com;

	private static final By tableRowsXpath = By
			.xpath("//form[contains(@action,'basket')]//table[contains(@class,'shop_table')]//tbody/tr");

	@FindBy(xpath = "//table[contains(@class,'shop')][1]")
	private WebElement table;

	@FindBy(xpath = "//form[contains(@action,'basket')]//table[contains(@class,'shop_table')]//tbody/tr")
	private List<WebElement> tableRows;

	@FindBy(xpath = "//a[contains(text(),'Proceed to Checkout')]")
	private WebElement proceedToCheckOut1;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 20);
		com = Commons.initialize(this.driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isBookExistinCart(String bookName) {
		boolean bookExist = false;
		String actualBookName = null;
		int rowsCount = tableRows.size();
		for (int i = 0; i < rowsCount - 1; i++) {
			List<WebElement> cells = tableRows.get(i).findElements(By.tagName("td"));
			if (!cells.isEmpty()) {
				actualBookName = cells.get(2).getText();
				System.out.println("ebtehal  " + actualBookName);
				System.out.println("all columns  " + cells.size());

				if (actualBookName.equals(bookName)) {
					System.out.println(actualBookName + bookName);
					bookExist = true;
					break;
				} else {
					bookExist = false;
				}
			}
		}
		return bookExist;
	}

	public void clickOnBookLink(int rowIndex) {
		WebElement cell = tableRows.get(rowIndex - 1).findElements(By.xpath(".//td")).get(3);
		cell.click();
	}

	public int getNoItems() {
		return com.getItemsNoInCart();
	}

	public String getTableCellValue(int rowIndex, int columnIndex) {
		WebElement cell = null;
		String cellValue = null;
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tableRowsXpath));
			cell = tableRows.get(rowIndex - 1).findElements(By.xpath(".//td")).get(columnIndex - 1);
			cellValue = cell.getText();
			System.out.println("cell value" + cellValue);
			if (cellValue.equals("")) {
				cellValue = cell.findElement(By.xpath("//input[@type='number']")).getAttribute("value");
			}
			return cellValue;
		} catch (NoSuchElementException e) {
			return cellValue;
		}
	}

	public void deleteBook(int rowIndex) {
		WebElement cell = null;
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tableRowsXpath));
		cell = tableRows.get(rowIndex - 1).findElements(By.xpath(".//td")).get(0);
		cell.click();
	}

	public void clickProceedToCheckOut() {
		try {
			proceedToCheckOut1.click();
			wait.until(ExpectedConditions.urlContains("checkout"));
		} catch (NoSuchElementException e) {
			System.out.println("No items added to Cart");
		}
	}

	public void load() {
		com.navigateCartPage();
	}

	public void isLoaded() throws Error {
		Assert.assertTrue(driver.getCurrentUrl().contains("basket"));
	}

	public void waitForPageLoading() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Site Logo']")));
	}
}
