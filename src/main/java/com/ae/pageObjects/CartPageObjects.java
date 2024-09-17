package com.ae.pageObjects;

import org.openqa.selenium.By;

public class CartPageObjects {
	
	public String productName="//a[normalize-space()='VALUE']//ancestor::tr";
	public By reviewOrder = By.xpath("//h2[normalize-space()='Review Your Order']");
	public By proceedToCheckout = By.xpath("//a[normalize-space()='Proceed To Checkout']");
	public By placeOrder = By.xpath("//a[normalize-space()='Place Order']");
	public By payment = By.xpath("//h2[normalize-space()='Payment']");
	public By nameOnCard = By.xpath("//input[@name='name_on_card']");
	public By cardNumber = By.xpath("//input[@name='card_number']");
	public By cvv = By.xpath("//input[@name='cvc']");
	public By expMonth = By.xpath("//input[@placeholder='MM']");
	public By expYear = By.xpath("//input[@placeholder='YYYY']");
	public By payAndConfirm = By.xpath("//button[@id='submit']");
	
	public By orderPaced = By.xpath("//b[normalize-space()='Order Placed!']");
	public By continueBtn = By.xpath("//a[normalize-space()='Continue']");
	
	public String cardNumberValue="4444 3333 2222 1111";
	public String cvvValue="888";
	public String expMonthValue="12";
	public String expYearValue="2026";
}
