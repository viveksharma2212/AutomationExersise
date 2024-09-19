package com.ae.testSuite;

import org.testng.annotations.Test;
import org.testng.Assert;

public class TestReviewProduct extends BaseTestClass{

	
	@Test(priority = 0)
	public void test00FilterAndVerify() {
		extlog = reports.startTest("Add Review for product with price 478rs.");
		Assert.assertTrue(signUpPageMethods.validateHomePage());
		Assert.assertTrue(productsPageMethods.addReviewAndSubmit());
	}
	

}
