package com.ae.pageObjects;

import org.openqa.selenium.By;

public class ProductsPageObject {
	public By searchInput = By.xpath("//input[@id='search_product']");
	public By searchBtn = By.xpath("//button[@id='submit_search']");
	public String addToKart = "(//p[contains(text(),'VALUE')]//ancestor::div[@class='product-image-wrapper']//a[normalize-space()='Add to cart'])[1]";
	public String viewProduct = "//p[contains(text(),'VALUE')]//ancestor::div[@class='product-image-wrapper']//a[normalize-space()='View Product']";
	public String product = "//p[contains(text(),'VALUE')]//ancestor::div[@class='product-image-wrapper']";
	
	public By menFilter = By.xpath("//a[normalize-space()='Men']");
	public By jeansfilter = By.xpath("//a[normalize-space()='Jeans']");
	public By viewCart = By.xpath("//u[normalize-space()='View Cart']");
	public By continueShopping = By.xpath("//button[normalize-space()='Continue Shopping']");
	public By added = By.xpath("//h4[normalize-space()='Added!']//ancestor::div[@class='modal-content']");

	
	public String frozenTopName = "Frozen Tops For Kids";
	public String peacockSaree ="Beautiful Peacock Blue Cotton Linen Saree";
	public String sleevelessDress ="Sleeveless Dress";
	public String pureCottonTShirt ="Pure Cotton V-Neck T-Shirt";
}
