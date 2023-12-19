package core.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverManager {

	public static final ThreadLocal<EventFiringWebDriver>  eventDriver = new ThreadLocal<EventFiringWebDriver>();

	public static synchronized WebDriver getDriverInstance() {
		return eventDriver.get();
		}
}
