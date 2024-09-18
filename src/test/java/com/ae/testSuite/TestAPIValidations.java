package com.ae.testSuite;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.http.Method;
import io.restassured.response.Response;

import org.testng.AssertJUnit;

import java.util.HashMap;
import java.util.Map;

public class TestAPIValidations extends BaseTestClass{

	
	@Test(priority = 0)
	public void test00SearchProductAPI() {
		extlog = reports.startTest("Verify User gets success with Search product API");
		
		Map<String, String> searchParams = new HashMap<>();
		searchParams.put("search_product", "shirt");
		
		Response searchResponse = sendRequest(Method.POST, "/searchProduct", searchParams);
		extlog.log(LogStatus.INFO, "Search Product Response: " + searchResponse.asString());
		
		AssertJUnit.assertTrue(!(searchResponse.asString().isEmpty()));
		AssertJUnit.assertTrue(searchResponse.asString().contains("\"responseCode\": 200, \"products\""));
		extlog.log(LogStatus.PASS, "Tess Passed,Verified User gets success with Search product API");
	}
	
	@Test(priority = 1)
	public void test01DeleteVerifyLoginAPI() {
		extlog = reports.startTest("Verify User gets error while deleting a verify login");
		
		Response deleteResponse = sendRequest(Method.DELETE, "/verifyLogin", null);
		extlog.log(LogStatus.INFO, "Search Product Response: " + deleteResponse.asString());
		
		AssertJUnit.assertTrue(!(deleteResponse.asString().isEmpty()));
		AssertJUnit.assertTrue(deleteResponse.asString().contains("\"responseCode\": 405, \"message\": \"This request method is not supported.\""));
		extlog.log(LogStatus.PASS, "Tess Passed,Verified User gets error while deleting a verify login");
	}
	
	@Test(priority = 2)
	public void test012updateAccountAPI() {
		extlog = reports.startTest("Verify User gets success with updating User Account");
		
		Map<String, String> updateParams = new HashMap<>();
		updateParams.put("email", signUpPageObjects.emailValue);
		updateParams.put("password", readJson("user.password"));
		updateParams.put("name", "Vivek sh updated");
		
		Response updateResponse = sendRequest(Method.PUT, "/updateAccount", updateParams);
		extlog.log(LogStatus.INFO, "Search Product Response: " + updateResponse.asString());
		
		AssertJUnit.assertTrue(!(updateResponse.asString().isEmpty()));
		AssertJUnit.assertTrue(updateResponse.asString().contains("\"responseCode\": 200, \"message\": \"User updated!\""));
		extlog.log(LogStatus.PASS, "Tess Passed,Verified User gets success with updating User Account");
	}
	

}
