package com.ae.pageObjects;

import org.openqa.selenium.By;

public class HomePageObjects {
	
	public By homePage = By.xpath("//img[@alt='Website for automation practice']");
	public By manageOptionsBtn = By.xpath("//p[normalize-space()='Manage options']");
	public By confirmChoicesBtn = By.xpath("//div[@aria-label='Manage your data']//p[@class='fc-button-label'][normalize-space()='Confirm choices']");
	public By signUp = By.xpath("//a[normalize-space()='Signup / Login']");
}
