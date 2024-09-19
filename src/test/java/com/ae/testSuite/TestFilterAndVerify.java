package com.ae.testSuite;

import org.testng.annotations.Test;
import org.testng.Assert;


public class TestFilterAndVerify extends BaseTestClass{

	
	@Test(priority = 0)
	public void test00FilterAndVerify() {
		extlog = reports.startTest("Filter jeans for men and verify 3 products displayed");
		Assert.assertTrue(signUpPageMethods.validateHomePage());
		Assert.assertTrue(productsPageMethods.filter());
		Assert.assertTrue(productsPageMethods.verify());
	}
	

}
