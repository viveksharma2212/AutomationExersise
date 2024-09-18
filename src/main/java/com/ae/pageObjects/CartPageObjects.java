package com.ae.pageObjects;

import org.openqa.selenium.By;

public class CartPageObjects {
	//Cart Page 
	public String productName="//a[normalize-space()='VALUE']//ancestor::tr";
	public By proceedToCheckout = By.xpath("//a[normalize-space()='Proceed To Checkout']");
	
	//review Order Page 
	public By reviewOrder = By.xpath("//h2[normalize-space()='Review Your Order']");
	public By placeOrder = By.xpath("//a[normalize-space()='Place Order']");
	
	//Payment Page
	public By payment = By.xpath("//h2[normalize-space()='Payment']");
	public By nameOnCard = By.xpath("//input[@name='name_on_card']");
	public By cardNumber = By.xpath("//input[@name='card_number']");
	public By cvv = By.xpath("//input[@name='cvc']");
	public By expMonth = By.xpath("//input[@placeholder='MM']");
	public By expYear = By.xpath("//input[@placeholder='YYYY']");
	public By payAndConfirm = By.xpath("//button[@id='submit']");
	
	public By orderPaced = By.xpath("//b[normalize-space()='Order Placed!']");
	public By continueBtn = By.xpath("//a[normalize-space()='Continue']");
	
}
