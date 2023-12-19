package configurations;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import core.pageobjects.qpros.ExtentReportsManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class configurationTest {
	public static final ThreadLocal<WebDriver>  driver = new ThreadLocal<>();
	static long currentTime = System.currentTimeMillis();

	@BeforeTest
	@Parameters("reportName")
	public static synchronized void beforeTestConfigs(String reportName) {
		ExtentReportsManager.attachReport(reportName + currentTime);
	}

	@AfterTest
	public static synchronized void afterTestConfigs() {
		ExtentReportsManager.flush();
	}
	
	@Parameters("browser")
	public static synchronized void initializeDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions opt = new ChromeOptions();
			opt.addExtensions(new File("./Extensions/AdBlock.crx"));
			driver.set(new ChromeDriver(opt));
		} else if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		}
	}

	public static WebDriver getDriver() {
		return driver.get();
	}
	public static synchronized void openWebSite(String url) {
		 getDriver().get(url);
		 getDriver().manage().window().maximize();
		ArrayList<String> tabs = new ArrayList<String>( getDriver().getWindowHandles());
		 getDriver().switchTo().window(tabs.get(0));
	}

	@AfterTest
	public void closeBrowser() {
		 getDriver().quit();
	}
}
