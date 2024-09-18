package com.ae.pageMethods;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.ae.base.TestBase;

import com.relevantcodes.extentreports.LogStatus;

public class ProductsPageMethods extends TestBase {

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
			highlightAndCapture("Correct Product displayed after search-" + productName, element, "SearchProduct_" + productName);
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
			highlightAndCapture("Product Added Confirmation-" + productName, productsPageObject.added, "ProductAddedToCart_" + productName);
			return true;
		} else {
			extlog.log(LogStatus.FAIL,captureScreenShot("ProductAddedToCart_" + productName),
					"Failed to Validate Product Added Confirmation");
			return false;
		}
	}
	
	public boolean addSingleProduct() {
		clickElement(homePageObject.products);
		addProductToCartAndValidate(readJson("orders.singleProductName"));
		clickElement(productsPageObject.viewCart);
		sleep(5);
		return cartAndPaymentPageMethods.verifyProductDisplayed(readJson("orders.singleProductName"));
	}
	
	public boolean addMultipleProduct() {
		List<String> productList = Arrays.asList(readJson("orders.multipleProductName").split(","));
		
		clickElement(homePageObject.products);
		addProductToCartAndValidate(productList.get(0));
		clickElement(productsPageObject.continueShopping);
		
		addProductToCartAndValidate(productList.get(1));
		clickElement(productsPageObject.continueShopping);
		
		addProductToCartAndValidate(productList.get(2));
		clickElement(productsPageObject.viewCart);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(cartAndPaymentPageMethods.verifyProductDisplayed(productList.get(0)));
		softAssert.assertTrue(cartAndPaymentPageMethods.verifyProductDisplayed(productList.get(1)));
		softAssert.assertAll();
		return cartAndPaymentPageMethods.verifyProductDisplayed(productList.get(2));
	}
	
	
	public boolean filter() {
		clickElement(homePageObject.products);
		sleep(5);
		if (elementDisplayed(productsPageObject.menFilter)) {
			clickElement(productsPageObject.menFilter);
			sleep(2);
			highlightAndCapture("Men filter displayed and clicked", productsPageObject.menFilter, "MenFilter");			
			if (elementDisplayed(productsPageObject.jeansfilter)) {
				highlightAndCapture("Men jeans filter displayed and clicked", productsPageObject.jeansfilter, "MenFilter");	
				clickElement(productsPageObject.jeansfilter);
				return true;
			}else
			{
				extlog.log(LogStatus.FAIL,captureScreenShot("MenJeansFilter"),
						"Men filter not displayed");
				return false;
			}
			
		}else
		{
			extlog.log(LogStatus.FAIL,captureScreenShot("MenFilter"),
					"Men filter not displayed");
			return false;
		}
		
		
	}
	public boolean verify() {
		List<WebElement> products = driver.findElements(By.xpath(productsPageObject.productsDisplayed));
		if(products.size()==3) {int i=1;
			for (WebElement obj :products) {
				String xpath= "("+productsPageObject.productsDisplayed+")["+i+"]"; 
				highlightAndCapture("Men jeans product displayed no :"+i, By.xpath(xpath), "Menjeans"+i);	
				i++;
			}
//			clickElement(homePageObject.home);
//			sleep(5);
			return true;
		}else
		{
			extlog.log(LogStatus.FAIL,captureScreenShot("Menjeans"),
					"3 Products are not displayed");
			return false;
		}
		
	}
	
	public boolean verifyAndViewProduct() {
		scrollToElement(By.xpath(productsPageObject.viewProduct.replace("VALUE", readJson("orders.singleProductName"))));
		
		if (elementDisplayed(productsPageObject.product478Highlight)) {
			highlightAndCapture("Product with 478rs price displayed.", productsPageObject.product478Highlight, "product478Highlight");
			clickElement(productsPageObject.product478);
			return elementDisplayed(productsPageObject.product478View);
		}else
		{
			extlog.log(LogStatus.FAIL,captureScreenShot("product478Highlight"),
					"Product with 478rs price not displayed.");
			return false;
		}
	}
	
	public boolean addReviewAndSubmit() {
		if (verifyAndViewProduct()) {
			highlightAndCapture("Product with 478rs price view Product Page displayed.", productsPageObject.product478View, "product478ViewPage");
			scrollToElement(productsPageObject.product478View);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(addInputValue(productsPageObject.reviewName, readJson("user.name")));
			softAssert.assertTrue(addInputValue(productsPageObject.reviewEmail, signUpPageObjects.emailValue));
			softAssert.assertTrue(addInputValue(productsPageObject.reviewInput, readJson("orders.review")));
			softAssert.assertAll();
			clickElement(productsPageObject.reviewSubmit);
			boolean flag =elementDisplayed(productsPageObject.reviewsuccess);
			if (flag) {
				highlightAndCapture("Review submitted successfully for Product with 478rs price.", productsPageObject.reviewsuccess, "product478reviewSuccess");
//				clickElement(homePageObject.home);
//				sleep(5);
				return flag;
			}else{
				extlog.log(LogStatus.FAIL,captureScreenShot("product478ViewPage"),
						"Failed to submit review for Product with 478rs price");
				return false;
			}
				
		}else
		{
			extlog.log(LogStatus.FAIL,captureScreenShot("product478ViewPage"),
					"Product with 478rs price view Product Page not displayed.");
			return false;
		}
	}

	

}
