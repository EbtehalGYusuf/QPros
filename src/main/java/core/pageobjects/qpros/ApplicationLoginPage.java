//package core.pageobjects.qpros;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class ApplicationLoginPage {
//
//	private WebDriverWait wait;
//
//	@FindBy(css = "[placeholder*='Username']")
//	private WebElement username;
//	@FindBy(xpath = "//span[text()='Please enter a valid username ']")
//	private WebElement usernameWarningMsg;
//	@FindBy(css = "[placeholder*='Password']")
//	private WebElement password;
//	@FindBy(xpath = "//span[text()='Please enter a valid password ']")
//	private WebElement passwordWarningMsg;
//	@FindBy(xpath = "//button[contains(@class,'submitForm-btn')]")
//	private WebElement loginButton;
//	@FindBy(css = "label")
//	private WebElement loginPageTitle;
//	@FindBy(css = ".center-block")
//	private WebElement logoImg;
//	@FindBy(css = ".alert-danger")
//	private WebElement loginErrorMsg;
//	@FindBy(xpath = "//div[@class = 'sidebar-wrapper']")
//	private WebElement sideBar;
//	@FindBy(xpath = "//h5[contains(@class,'welcome-text')]")
//	private WebElement welcomeText;
//	
//	public ApplicationLoginPage(WebDriver driver) {
//		wait = new WebDriverWait(driver, 10);
//	}
//	
//	public static ApplicationLoginPage initialize(WebDriver driver){
//		return PageFactory.initElements(driver, ApplicationLoginPage.class);	
//	}
//
//	public stat
//}
