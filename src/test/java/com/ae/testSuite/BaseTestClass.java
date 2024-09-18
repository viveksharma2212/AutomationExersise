package com.ae.testSuite;

import com.ae.base.TestBase;
import com.ae.pageMethods.CartAndPaymentPageMethods;
import com.ae.pageMethods.ProductsPageMethods;
import com.ae.pageMethods.SignUpPageMethods;
import com.ae.utility.PropertyReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;


public class BaseTestClass extends TestBase {
	SignUpPageMethods signUpPageMethods=new SignUpPageMethods();
	ProductsPageMethods productsPageMethods=new ProductsPageMethods();
	CartAndPaymentPageMethods cartAndPaymentPageMethods= new CartAndPaymentPageMethods();

	@BeforeSuite
	public void beforeSuite() {
		reports = new ExtentReports(extentReportDir + "ExecutionReport.html");
		//setBrowser(PropertyReader.readVariable("config.properties", "ae.browser.chrome"));
	}


	@AfterSuite
	public void afterSuite() {
		reports.flush();
		driver.quit();
		reports.close();
	}
	
	@BeforeClass
    public void beforeClass() {
		setBrowser(PropertyReader.readVariable("config.properties", "ae.browser.chrome"));
		launch();
    }
	
	@AfterClass
    public void afterClass() {
		driver.close();
    }
	
	
	@BeforeTest
	public void beforeTest() {
		//launch();
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
			clickElement(signUpPageObjects.logout);
			sleep(5);
			clickElement(homePageObject.home);
		}
		reports.endTest(extlog);
	}

}
