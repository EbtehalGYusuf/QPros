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

public class BillingDetailsPage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Commons com;

	private static final By tableRowsXpath = By.xpath("//table[contains(@class,'shop')]//tr");

	@FindBy(xpath = "//input[@id='billing_first_name']")
	private WebElement firstName;

	@FindBy(xpath = "//input[@id='billing_last_name']")
	private WebElement lastName;

	@FindBy(xpath = "//input[@id='billing_company']")
	private WebElement companyName;

	@FindBy(xpath = "//input[@id='billing_email']")
	private WebElement email;

	@FindBy(xpath = "//input[@id='billing_phone']")
	private WebElement phone;

	@FindBy(xpath = "//div[@id='s2id_billing_country']//a[@class='select2-choice']")
	private WebElement countryField;

	@FindBy(xpath = "//div[@id='select2-drop']")
	private WebElement Menu;

	@FindBy(xpath = "//input[@id='billing_address_1']")
	private WebElement address;

	@FindBy(xpath = "//input[@id='billing_address_2']")
	private WebElement address2;

	@FindBy(xpath = "//input[@id='billing_postcode']")
	private WebElement postcode;

	@FindBy(xpath = "//input[@id='billing_city']")
	private WebElement city;

	@FindBy(xpath = "//div[@id='s2id_billing_state']//a[@class='select2-choice']']")
	private WebElement stateAsMenu;

	@FindBy(xpath = "//input[@id='billing_state']")
	private WebElement stateAsField;

	@FindBy(xpath = "//table[contains(@class,'shop')]//tr")
	private List<WebElement> pricesTableRows;

	public BillingDetailsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 20);
		com = Commons.initialize(this.driver);
		PageFactory.initElements(driver, this);
	}

	public static BillingDetailsPage initialize(WebDriver driver) {
		return PageFactory.initElements(driver, BillingDetailsPage.class);
	}

//	public WebElement table1 () {
////		return ((WrapsElement) driver.findElement(tableRowsXpath)).getWrappedElement();
//	}
	public String getSelectedOptionCountryMenu() {
		return countryField.findElement(By.xpath(".//span[@class='select2-chosen']")).getText();
	}

	public void openCountryMenu() {
		countryField.click();
	}

	public String getSelectedOptionStateMenu() {
		return stateAsMenu.findElement(By.xpath(".//span[@class='select2-chosen']")).getText();
	}

	public void openStateMenu() {
		stateAsMenu.click();
	}

	public void search(String searchWord) {
		Menu.findElement(By.xpath(".//input[@id='s2id_autogen1_search']")).sendKeys(searchWord);
	}

	public void selectMenuOption(String searchWord) {
		String xpath = String.format("//li//div[@role='option']//span[text()='%s']", searchWord);
		driver.findElement(By.xpath(xpath)).click();
	}

//	public void insertText(String text,WebElement fieldName) {
//		fieldName.clear();
//		fieldName.click();
//		fieldName.sendKeys(text);
//	}

//	public String getFieldText(String text,WebElement fieldName) {
//		return fieldName.getText();
//	}
//	
	public void insertFirstName(String text) {
		firstName.clear();
		firstName.click();
		firstName.sendKeys(text);
	}

	public String getFisrtNameText() {
		return firstName.getAttribute("value");
	}

	public void insertLastName(String text) {
		lastName.clear();
		lastName.click();
		lastName.sendKeys(text);
	}

	public String getLastNameText() {
		return lastName.getAttribute("value");
	}

	public void insertCompany(String text) {
		companyName.clear();
		companyName.click();
		companyName.sendKeys(text);
	}

	public String getCompanyText() {
		return companyName.getAttribute("value");
	}

	public void insertEmail(String text) {
		email.clear();
		email.click();
		email.sendKeys(text);
	}

	public String getEmailText() {
		return email.getAttribute("value");
	}

	public void insertPhone(String text) {
		phone.clear();
		phone.click();
		phone.sendKeys(text);
	}

	public String getPhoneText() {
		return phone.getAttribute("value");
	}

	public void insertAddress(String text) {
		address.clear();
		address.click();
		address.sendKeys(text);
	}

	public String getAddressText() {
		return address.getAttribute("value");
	}

	public void insertCity(String text) {
		city.clear();
		city.click();
		city.sendKeys(text);
	}

	public String getCityText() {
		return city.getAttribute("value");
	}

	public void insertState(String text) {
		stateAsField.clear();
		stateAsField.click();
		stateAsField.sendKeys(text);
	}

	public String getStateText() {
		return stateAsField.getAttribute("value");
	}

	public void insertPostcode(String text) {
		postcode.clear();
		postcode.click();
		postcode.sendKeys(text);
	}

	public String getPostcodeText() {
		return postcode.getAttribute("value");
	}

	public String getTableCellValue(int rowIndex, int columnIndex) {
		WebElement cell = null;
		String cellValue = null;
		com.scrollDown();
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tableRowsXpath));
			cell = pricesTableRows.get(rowIndex - 1).findElements(By.xpath(".//td")).get(columnIndex - 1);
			cellValue = cell.getText();
			return cellValue;
		} catch (NoSuchElementException e) {
			return cellValue;
		}
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
