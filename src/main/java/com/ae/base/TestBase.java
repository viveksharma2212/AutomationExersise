package com.ae.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ae.pageObjects.CartPageObjects;
import com.ae.pageObjects.HomePageObjects;
import com.ae.pageObjects.ProductsPageObject;
import com.ae.pageObjects.SignUpPageObjects;
import com.ae.utility.PropertyReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	//private final Logger LOGGER = Logger.getLogger(TestBase.class.getName());
	private static final Logger LOGGER = LogManager.getLogger(TestBase.class);
	
	public static WebDriver driver;
	public static WebDriverWait wait;

	public static ExtentReports reports;
	public static ExtentTest extlog;
	

	public static String workingDir = System.getProperty("user.dir");
	public static String extentReportDir = workingDir + "/ExtentReport/" + currentDate() + "OutputReport/";
	public CartPageObjects cartPageObjects = new CartPageObjects();
	public SignUpPageObjects signUpPageObjects = new SignUpPageObjects();
	public HomePageObjects homePageObject=new HomePageObjects();
	public ProductsPageObject productsPageObject= new ProductsPageObject();

	public static String currentDate() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String currentDate = df.format(date);
		return currentDate;
	}
	
    public Response sendRequest(Method method, String endpoint, Map<String, String> multipartParams) {
   	 RequestSpecification request = RestAssured.given();
   	 RestAssured.baseURI = "https://www.automationexercise.com/api";
   	 request = RestAssured.given();

       if (multipartParams != null) {
           for (Map.Entry<String, String> entry : multipartParams.entrySet()) {
               request.multiPart(entry.getKey(), entry.getValue());
           }
       }

       Response response = request
               .when()
               .request(method, endpoint)
               .then()
               .extract().response();

       return response;
   }
    
	public String readJson(String parameter)  {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
		try {
			jsonNode = mapper.readTree(new File(workingDir +"/src/main/resources/TestData/TestData.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String[] path = parameter.split("\\.");

        JsonNode node = jsonNode;
        for (String p : path) {
            node = node.get(p);
            if (node == null) {
                return "Invalid parameter";
            }
        }

        return node.asText();
    }


	public String[][] readFromExcel(String filePath, String sheetName)  {

		try {
			FileInputStream fs = new FileInputStream(filePath);
			Workbook wb = new XSSFWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);
			int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
			int columnCount = sh.getRow(0).getLastCellNum();

			String[][] excelData = new String[rowCount][columnCount];

			for (int r = 1; r <= rowCount; r++) {
				for (int c = 0; c < columnCount; c++) {
					excelData[r - 1][c] = sh.getRow(r).getCell(c).getStringCellValue();
				}
			}
			wb.close();
			return excelData;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}throw new NullPointerException("Unable to Retrive data from Excel");
			
	}
	
	public String captureScreenShot(String tcName) {

		String path;
		String fileName=currentDate() + tcName + ".png";
		path = extentReportDir + "/screenshots/" + fileName;
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path =extlog.addScreenCapture("screenshots//"+fileName);
		
	}
	
	public void highlightElement(By xpath)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.border='3px solid red'", element(xpath));
	}
	
	public void unhighlightElement(By xpath)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.border='0px solid red'", element(xpath));
		//sleep(1);
	}

	
	public void highlightAndCapture(String desc, By xpath, String screenshotName) {
		extlog.log(LogStatus.PASS, desc);
		highlightElement(xpath);
		extlog.log(LogStatus.INFO, captureScreenShot(screenshotName));
		unhighlightElement(xpath);
	}

	public void setBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-search-engine-choice-screen");
			driver = new ChromeDriver(options);
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			LOGGER.info( "Setting Chrome Browser");
		}else if (browserName.equalsIgnoreCase("firefox")) {
			// To Be implemented
		} 
	}

	public void launch() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		LOGGER.info(
				"Launching URL:-" + PropertyReader.readVariable("config.properties", "ae.gui.url"));
		driver.get(PropertyReader.readVariable("config.properties", "ae.gui.url"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		clickElement(homePageObject.manageOptionsBtn);
		sleep(1);
		clickElement(homePageObject.confirmChoicesBtn);
	}
	
	public void sleep(int seconds)
	{
		long milliseconds =seconds*1000;
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebElement element(By xpath) {
		try {
			LOGGER.info( "finding an element:-" + xpath);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpath));
			return driver.findElement(xpath);
		} catch (NoSuchElementException  e) {
			throw new NoSuchElementException("WebElement is not Present for " + xpath, e);
		}

	}
	public boolean isElementVisible(By xpath) {
	    try { 
	    	return driver.findElement(xpath).isDisplayed(); 
	    	} catch (Exception ignored) 
	    		{ 
	    		return false; 
	    		}
	}

	public boolean elementDisplayed(By xpath) {
		boolean flag = element(xpath).isDisplayed();
		if (flag) {
			LOGGER.info( "Element:-" + xpath + " is Displayed");
			return true;
		} else {
			LOGGER.info( "Element:-" + xpath + " is not Displayed");
			return false;
		}
	}

	public void clickElement(By xpath) {
		element(xpath).click();
		LOGGER.info( "clicked on element:-" + xpath);
	}
	
	public String getValue(By xpath) {
		sleep(1);
		String value =element(xpath).getText();
		if (value.isEmpty()) {
			value =element(xpath).getAttribute("value");
		}
		LOGGER.info( "Fetched value as:"+value+"for element:-" + xpath);
		return value;
	}

	public void clickAndFill(By xpath, String value) {
		WebElement element = element(xpath);
		element.click();
		LOGGER.info( "clicked on element:-" + xpath);
		element.click();
		element.clear();
		element.sendKeys(value);

	}
	public void scrollToElement(By xpath) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element(xpath));
		 
	}
	
	public boolean addInputValue(By xpath, String data) {
		clickAndFill(xpath, data);
		if (getValue(xpath).equalsIgnoreCase(data)) {
			highlightAndCapture("Successfully filled data as:-" + data, xpath, "addData_" + data);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean selectFromDropdown(By xpath, String value) {
		clickElement(xpath);
		String xpathOption = "//option[normalize-space()='VALUE']";
		By option = By.xpath(xpathOption.replace("VALUE", value));
		clickElement(option);
		highlightAndCapture("Successfully selected value as:-" + value, xpath, "selectValue_" + value);
		return true;

	}
	
	public boolean selectRadioButton(String value) {

		String xpathOption = "//input[@value='VALUE']";
		By option = By.xpath(xpathOption.replace("VALUE", value));
		clickElement(option);
		highlightAndCapture("Successfully selected value as:-" + value, option, "selectValue_" + value);
		return true;
	}

}
