package com.ae.testSuite;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;


public class TestPlaceSingleOrder extends BaseTestClass{
	
	@Test(priority = 0)
	public void test00LoginAndVerifyLoggedIn() {
		extlog = reports.startTest("Validate user logged in");
		AssertJUnit.assertTrue(signUpPageMethods.validateLogin());
	}
	
	@Test(priority = 1, dependsOnMethods = { "test00LoginAndVerifyLoggedIn" })
	public void test01ValidateProductAddedToCart() {
		extlog = reports.startTest("Validate Product added to cart");
		AssertJUnit.assertTrue(productsPageMethods.addSingleProduct());
	}
	
	@Test(priority = 2, dependsOnMethods = { "test01ValidateProductAddedToCart" })
	public void test02MakePayment() {
		extlog = reports.startTest("Proceed to Checkout and Make the Payment");
		AssertJUnit.assertTrue(cartAndPaymentPageMethods.makePaymentAndConfirmOrder());
	}
}
