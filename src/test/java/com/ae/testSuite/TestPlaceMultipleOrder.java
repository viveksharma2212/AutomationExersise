package com.ae.testSuite;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ae.pageMethods.CartAndPaymentPageMethods;
import com.ae.pageMethods.ProductsPageMethods;
import com.ae.pageMethods.SignUpPageMethods;

public class TestPlaceMultipleOrder extends BaseTestClass{
SignUpPageMethods signUpPageMethods=new SignUpPageMethods();
ProductsPageMethods productsPageMethods=new ProductsPageMethods();
CartAndPaymentPageMethods cartAndPaymentPageMethods= new CartAndPaymentPageMethods();
	
	@Test(priority = 0)
	public void test00LoginAndVerifyLoggedIn() {
		extlog = reports.startTest("Validate user logged in");
		Assert.assertTrue(signUpPageMethods.validateLogin());
	}
	
	@Test(priority = 1, dependsOnMethods = { "test00LoginAndVerifyLoggedIn" })
	public void test01ValidateProductAddedToCart() {
		extlog = reports.startTest("Validate Product added to cart");
		Assert.assertTrue(productsPageMethods.addMultipleProduct());
	}
	
	@Test(priority = 2, dependsOnMethods = { "test01ValidateProductAddedToCart" })
	public void test02MakePayment() {
		extlog = reports.startTest("Proceed to Checkout and Make the Payment");
		Assert.assertTrue(cartAndPaymentPageMethods.makePaymentAndConfirmOrder());
	}
}
