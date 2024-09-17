package com.ae.pageMethods;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import com.ae.base.TestBase;
import com.ae.pageObjects.HomePageObjects;
import com.ae.pageObjects.ProductsPageObject;
import com.relevantcodes.extentreports.LogStatus;

public class ProductsPageMethods extends TestBase {
	HomePageObjects homePageObject=new HomePageObjects();
	ProductsPageObject productsPageObject= new ProductsPageObject();
	CartAndPaymentPageMethods cartAndPaymentPageMethods=new CartAndPaymentPageMethods();
	
	public void addProductToCartAndValidate(String productName) {
		addInputValue(productsPageObject.searchInput, productName);
		clickElement(productsPageObject.searchBtn);
		sleep(5);
		scrollToElement(productsPageObject.searchBtn);
		SoftAssert softAssert = new SoftAssert(); 
		softAssert.assertTrue(verifyProductDisplayed(productName));
		softAssert.assertTrue(addProductToCart(productName));
		softAssert.assertAll();
	}
	
	public boolean verifyProductDisplayed(String productName) {
		By element=By.xpath(productsPageObject.product.replace("VALUE", productName));
		if (elementDisplayed(element)) {
			extlog.log(LogStatus.PASS, "Correct Product displayed after search-" + productName);
			highlightElement(element);
			extlog.log(LogStatus.INFO, captureScreenShot("SearchProduct_" + productName));
			unhighlightElement(element);
			return true;
		} else {
			extlog.log(LogStatus.FAIL,captureScreenShot("SearchProduct_" + productName),
					"Incorrect Product displayed after search");
			return false;
		}
	}
	public boolean addProductToCart(String productName) {
		By element=By.xpath(productsPageObject.addToKart.replace("VALUE", productName));
		sleep(3);
		clickElement(element);
		sleep(2);
		if (elementDisplayed(productsPageObject.added)) {
			extlog.log(LogStatus.PASS, "Product Added Confirmation-" + productName);
			highlightElement(productsPageObject.added);
			extlog.log(LogStatus.INFO, captureScreenShot("ProductAddedToCart_" + productName));
			unhighlightElement(productsPageObject.added);
			return true;
		} else {
			extlog.log(LogStatus.FAIL,captureScreenShot("ProductAddedToCart_" + productName),
					"Failed to Validate Product Added Confirmation");
			return false;
		}
	}
	
	public boolean addSingleProduct() {
		clickElement(homePageObject.products);
		addProductToCartAndValidate(productsPageObject.frozenTopName);
		clickElement(productsPageObject.viewCart);
		sleep(5);
		return cartAndPaymentPageMethods.verifyProductDisplayed(productsPageObject.frozenTopName);
	}
	
	public boolean addMultipleProduct() {
		clickElement(homePageObject.products);
		addProductToCartAndValidate(productsPageObject.peacockSaree);
		clickElement(productsPageObject.continueShopping);
		
		addProductToCartAndValidate(productsPageObject.sleevelessDress);
		clickElement(productsPageObject.continueShopping);
		
		addProductToCartAndValidate(productsPageObject.pureCottonTShirt);
		clickElement(productsPageObject.viewCart);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(cartAndPaymentPageMethods.verifyProductDisplayed(productsPageObject.peacockSaree));
		softAssert.assertTrue(cartAndPaymentPageMethods.verifyProductDisplayed(productsPageObject.sleevelessDress));
		softAssert.assertTrue(cartAndPaymentPageMethods.verifyProductDisplayed(productsPageObject.pureCottonTShirt));
		return cartAndPaymentPageMethods.verifyProductDisplayed(productsPageObject.peacockSaree);
	}
	
	

}
