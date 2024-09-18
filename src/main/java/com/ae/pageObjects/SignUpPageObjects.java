package com.ae.pageObjects;

import java.util.Random;

import org.openqa.selenium.By;

public class SignUpPageObjects {
	
	public static int value = new Random().nextInt(99999);
	public String emailValue = "vivek"+value+"@test.com";///"test222212@test.com";///
	
	//Login page
	public By name = By.xpath("//input[@placeholder='Name']");
	public By signUpEmail = By.xpath("//input[@data-qa='signup-email']");
	public By signUpBtn = By.xpath("//button[normalize-space()='Signup']");
	public By loginEmail = By.xpath("//input[@data-qa='login-email']");
	public By loginPassword = By.xpath("//input[@placeholder='Password']");
	public By loginBtn = By.xpath("//button[normalize-space()='Login']");
	
	//User Sign up page
	public By addressTitle = By.xpath("//b[normalize-space()='Address Information']");
	public By password = By.xpath("//input[@id='password']");
	public By day = By.xpath("//select[@id='days']");
	public By month = By.xpath("//select[@id='months']");
	public By year = By.xpath("//select[@id='years']");
	public By firstName = By.xpath("//input[@id='first_name']");
	public By lastName = By.xpath("//input[@id='last_name']");
	public By address1= By.xpath("//input[@id='address1']");
	public By country = By.xpath("//select[@id='country']");
	public By state = By.xpath("//input[@id='state']");
	public By city = By.xpath("//input[@id='city']");
	public By zipcode = By.xpath("//input[@id='zipcode']");
	public By mobileNumber = By.xpath("//input[@id='mobile_number']");
	public By createAccount = By.xpath("//button[normalize-space()='Create Account']");
	public By continueBtn = By.xpath("//a[normalize-space()='Continue']");
	public By logout = By.xpath("//a[normalize-space()='Logout']");
	
	

}
