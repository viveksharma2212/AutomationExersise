package com.ae.testSuite;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestReviewProduct extends BaseTestClass{

	
	@Test(priority = 0)
	public void test00FilterAndVerify() {
		extlog = reports.startTest("Add Review for product with price 478rs.");
		AssertJUnit.assertTrue(signUpPageMethods.validateHomePage());
		AssertJUnit.assertTrue(productsPageMethods.addReviewAndSubmit());
	}
	

}
