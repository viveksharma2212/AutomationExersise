package com.ae.testSuite;

import com.ae.base.TestBase;
import com.ae.utility.PropertyReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class BaseTestClass extends TestBase {

	@BeforeSuite
	public void beforeSuite() {
		reports = new ExtentReports(extentReportDir + "ExecutionReport.html");
		setBrowser(PropertyReader.readVariable("config.properties", "ae.browser.chrome"));
	}


	@AfterSuite
	public void afterSuite() {
		reports.flush();
		driver.close();
		reports.close();
	}
	
	
	@BeforeTest
	public void beforeTest() {
		launch();
	}
	
	@AfterTest
	public void afterTest() {
		 reports.endTest(extlog);
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult results) {
		if (results.getStatus() == ITestResult.FAILURE) {
			extlog.log(LogStatus.FAIL, results.getName() + ": Test failed");
			extlog.log(LogStatus.FAIL, captureScreenShot(results.getName()));
			extlog.log(LogStatus.ERROR, results.getThrowable());
		}
		reports.endTest(extlog);
	}

}
