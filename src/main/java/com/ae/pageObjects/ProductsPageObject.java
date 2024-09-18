package com.ae.pageObjects;

import org.openqa.selenium.By;

public class ProductsPageObject {
	public By searchInput = By.xpath("//input[@id='search_product']");
	public By searchBtn = By.xpath("//button[@id='submit_search']");
	public String addToKart = "(//p[contains(text(),'VALUE')]//ancestor::div[@class='product-image-wrapper']//a[normalize-space()='Add to cart'])[1]";
	public String viewProduct = "//p[contains(text(),'VALUE')]//ancestor::div[@class='product-image-wrapper']//a[normalize-space()='View Product']";
	public String product = "//p[contains(text(),'VALUE')]//ancestor::div[@class='product-image-wrapper']";
	public String productsDisplayed ="//div[@class='product-image-wrapper']//div[@class='single-products']";
	
	public By menFilter = By.xpath("//a[normalize-space()='Men']");
	public By jeansfilter = By.xpath("//a[normalize-space()='Jeans']");
	public By viewCart = By.xpath("//u[normalize-space()='View Cart']");
	public By continueShopping = By.xpath("//button[normalize-space()='Continue Shopping']");
	public By added = By.xpath("//h4[normalize-space()='Added!']//ancestor::div[@class='modal-content']");

	
	public String frozenTopName = "Frozen Tops For Kids";
	public String peacockSaree ="Beautiful Peacock Blue Cotton Linen Saree";
	public String sleevelessDress ="Sleeveless Dress";
	public String pureCottonTShirt ="Pure Cotton V-Neck T-Shirt";
	
	public By product478Highlight = By.xpath("//h2[contains(text(),'Rs. 478')]//ancestor::div[@class='single-products']");
	public By product478 = By.xpath("//h2[contains(text(),'Rs. 478')]//ancestor::div[@class='product-image-wrapper']//a[normalize-space()='View Product']");
	public By product478View = By.xpath("//span[normalize-space()='Rs. 478']");
	public By reviewName = By.xpath("//input[@id='name']");
	public By reviewEmail = By.xpath("//input[@id='email']");
	public By reviewInput = By.xpath("//textarea[@id='review']");
	public By reviewSubmit = By.xpath("//button[@id='button-review']");
	public By reviewsuccess = By.xpath("//span[normalize-space()='Thank you for your review.']");
	public String review ="This is a review about the product which cost 478rs";
}
