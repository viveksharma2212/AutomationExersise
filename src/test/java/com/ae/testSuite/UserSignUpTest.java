package com.ae.testSuite;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ae.pageMethods.SignUpPageMethods;

public class UserSignUpTest extends BaseTestClass {
	SignUpPageMethods signUpPageMethods=new SignUpPageMethods();
	
	@Test(priority = 0)
	public void test00ValidateHomePageDisplayedAndNaviageteToSignUpPage() {
		extlog = reports.startTest("Validate home Page Displayed And Naviagete To Sign Up Page");
		Assert.assertTrue(signUpPageMethods.validateHomePage());
	}
	
	@Test(priority = 1, dependsOnMethods = { "test00ValidateHomePageDisplayedAndNaviageteToSignUpPage" })
	public void test01ValidateSignUpPageAndSignUpWithCorrectData() {
		extlog = reports.startTest("Validate Sign-UP Page Displayed And enter correct data");
		Assert.assertTrue(signUpPageMethods.validateSignupPageAndFillData());
	}
	
	@Test(priority = 2, dependsOnMethods = { "test01ValidateSignUpPageAndSignUpWithCorrectData" })
	public void test02FillTheDataAndClickSignUp() {
		extlog = reports.startTest("Validate Sign-UP form Displayed And enter correct data and click sign up");
		Assert.assertTrue(signUpPageMethods.fillFormAndSignup());
	}
 

	

}
