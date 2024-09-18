package com.ae.testSuite;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;


public class TestFilterAndVerify extends BaseTestClass{

	
	@Test(priority = 0)
	public void test00FilterAndVerify() {
		extlog = reports.startTest("Filter jeans for men and verify 3 products displayed");
		AssertJUnit.assertTrue(signUpPageMethods.validateHomePage());
		AssertJUnit.assertTrue(productsPageMethods.filter());
		AssertJUnit.assertTrue(productsPageMethods.verify());
	}
	

}
