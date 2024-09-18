package com.ae.pageMethods;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;

import org.testng.asserts.SoftAssert;

import com.ae.base.TestBase;

import com.relevantcodes.extentreports.LogStatus;

public class SignUpPageMethods extends TestBase {



	
	public boolean validateHomePage() {
		boolean flag = elementDisplayed(homePageObject.homePage);
		if (flag) {
			extlog.log(LogStatus.PASS, "Validated site loaded correctly");
			extlog.log(LogStatus.PASS, captureScreenShot("HomeScreen"));
			return true;
		} else {
			extlog.log(LogStatus.FAIL,captureScreenShot("validateLoginPageFail"),
					"Failed to validate site loaded properly");
			return false;
		}
	}
	
	public boolean validateLogin() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(validateHomePage());
		clickElement(homePageObject.signUp);
		boolean flag = elementDisplayed(signUpPageObjects.signUpEmail);
		if (flag) {
			extlog.log(LogStatus.PASS, "Validated sign up page displayed");
			extlog.log(LogStatus.PASS, captureScreenShot("loginScreen"));

			softAssert.assertTrue(addInputValue(signUpPageObjects.loginEmail, signUpPageObjects.emailValue));
			softAssert.assertTrue(addInputValue(signUpPageObjects.loginPassword, signUpPageObjects.passwordValue));
			softAssert.assertAll();
			clickElement(signUpPageObjects.loginBtn);
		} else {
			extlog.log(LogStatus.FAIL, captureScreenShot("validateLoginPageFail"),
					"Failed to validate site loaded properly");
		}
		if(elementDisplayed(By.xpath(homePageObject.loggedInName.replace("VALUE", signUpPageObjects.nameValue)))) {
			highlightAndCapture("Logged in successfully", By.xpath(homePageObject.loggedInName.replace("VALUE", signUpPageObjects.nameValue)), "loginName");
			return true;
		}else {
			extlog.log(LogStatus.FAIL, captureScreenShot("validateLoginFail"),
					"Failed to validate user logged in correctly");
			return false;
		}

		
	}
	
	public boolean validateSignupPageAndFillData() {
		clickElement(homePageObject.signUp);
		boolean flag = elementDisplayed(signUpPageObjects.signUpEmail);
		if (flag) {
			extlog.log(LogStatus.PASS, "Validated sign up page displayed");
			extlog.log(LogStatus.PASS, captureScreenShot("SignUpScreen"));
			
			SoftAssert softAssert = new SoftAssert(); 
			softAssert.assertTrue(addInputValue(signUpPageObjects.name, signUpPageObjects.nameValue));
			softAssert.assertTrue(addInputValue(signUpPageObjects.signUpEmail, signUpPageObjects.emailValue));
			softAssert.assertAll();
			clickElement(signUpPageObjects.signUpBtn);
			assertTrue(elementDisplayed(signUpPageObjects.password));
			extlog.log(LogStatus.PASS, "clicked on sign up button and sign up form displayed");
			extlog.log(LogStatus.PASS, captureScreenShot("signupform"));
			return true;
		} else {
			extlog.log(LogStatus.FAIL,captureScreenShot("validateLoginPageFail"),
					"Failed to navigate to sign up page");
			return false;
		}
	}
	
	public boolean fillFormAndSignup() {
		
		SoftAssert softAssert = new SoftAssert(); 
		softAssert.assertTrue(selectRadioButton( signUpPageObjects.titleValue),"");
		softAssert.assertTrue(addInputValue(signUpPageObjects.password, signUpPageObjects.passwordValue));
		softAssert.assertTrue(selectFromDropdown(signUpPageObjects.day, signUpPageObjects.dayValue));
		softAssert.assertTrue(selectFromDropdown(signUpPageObjects.month, signUpPageObjects.monthValue));
		softAssert.assertTrue(selectFromDropdown(signUpPageObjects.year, signUpPageObjects.yearValue));
		scrollToElement(signUpPageObjects.addressTitle);
		softAssert.assertTrue(addInputValue(signUpPageObjects.firstName, signUpPageObjects.firstNameValue));
		softAssert.assertTrue(addInputValue(signUpPageObjects.lastName, signUpPageObjects.lastNameValue));
		softAssert.assertTrue(addInputValue(signUpPageObjects.address1, signUpPageObjects.addressValue));
		scrollToElement(signUpPageObjects.address1);
		softAssert.assertTrue(selectFromDropdown(signUpPageObjects.country, signUpPageObjects.countryValue));
		softAssert.assertTrue(addInputValue(signUpPageObjects.state, signUpPageObjects.stateValue));
		softAssert.assertTrue(addInputValue(signUpPageObjects.city, signUpPageObjects.cityValue));
		softAssert.assertTrue(addInputValue(signUpPageObjects.zipcode, signUpPageObjects.zipcodeValue));
		softAssert.assertTrue(addInputValue(signUpPageObjects.mobileNumber, signUpPageObjects.mobileNumberValue));
		softAssert.assertAll();
		clickElement(signUpPageObjects.createAccount);
		
		if (elementDisplayed(signUpPageObjects.continueBtn)) {
			assertTrue(elementDisplayed(signUpPageObjects.continueBtn));
			extlog.log(LogStatus.PASS, "sign up successfull");
			extlog.log(LogStatus.PASS, captureScreenShot("signupformsuccess"));
			clickElement(signUpPageObjects.continueBtn);
			sleep(10);
			clickElement(signUpPageObjects.logout);
			return true;
		} else {
			extlog.log(LogStatus.FAIL, captureScreenShot("signUpFailed"),
					"Failed to sign up ");
			return false;
		}
		
		
	}
	
}
