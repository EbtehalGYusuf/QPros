package core.pageobjects.qpros;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportsManager {
	private static ExtentReports extent;
	private static ExtentHtmlReporter reporter;
	private static ExtentTest Logger1;

	public static void attachReport(String reportName) {
		reporter = new ExtentHtmlReporter("./target/extent-report/" + reportName + ".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	public static synchronized void flush() {
		extent.flush();
	}

	public static synchronized void createTest(String testName) {
		Logger1 = extent.createTest(testName);
	}

	public static synchronized void info(String info) {
		Logger1.log(Status.INFO, info);
	}

	public static synchronized void pass(String info) {
//		 logger.get().pass(info);

		Logger1.log(Status.PASS, info);
	}

	public static synchronized void fail(String info) {
		Logger1.log(Status.FAIL, info);
	}
}