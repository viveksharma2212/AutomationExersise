package com.ae.pageObjects;

import org.openqa.selenium.By;

public class HomePageObjects {
	
	
	//Navigation bar
	public By homePage = By.xpath("//img[@alt='Website for automation practice']");
	public By signUp = By.xpath("//a[normalize-space()='Signup / Login']");
	public By home = By.xpath("//a[normalize-space()='Home']");
	public By products = By.xpath("//a[@href='/products']");
	public String loggedInName = "//b[normalize-space()='VALUE']";
	
	//consent Pop up
	public By manageOptionsBtn = By.xpath("//p[normalize-space()='Manage options']");
	public By confirmChoicesBtn = By.xpath("//div[@aria-label='Manage your data']//p[@class='fc-button-label'][normalize-space()='Confirm choices']");
	
}
