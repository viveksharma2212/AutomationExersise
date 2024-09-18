package com.ae.pageMethods;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import com.ae.base.TestBase;
import com.ae.pageObjects.CartPageObjects;
import com.ae.pageObjects.SignUpPageObjects;
import com.relevantcodes.extentreports.LogStatus;

public class CartAndPaymentPageMethods extends TestBase {
	CartPageObjects cartPageObjects = new CartPageObjects();
	SignUpPageObjects signUpPageObjects = new SignUpPageObjects();

	public boolean verifyProductDisplayed(String productName) {

		if (elementDisplayed(cartPageObjects.proceedToCheckout)) {
			By xpath = By.xpath(cartPageObjects.productName.replace("VALUE", productName));
			elementDisplayed(xpath);
			highlightAndCapture("Correct Product added to cart -" + productName, xpath, "cartProduct_" + productName);
			return true;
		} else {
			extlog.log(LogStatus.FAIL, captureScreenShot("cartProduct_" + productName),
					"Failed to Validate Correct Product added to cart");
			return false;
		}
	}

	public boolean makePaymentAndConfirmOrder() {
		sleep(10);
		clickElement(cartPageObjects.proceedToCheckout);
		if (elementDisplayed(cartPageObjects.reviewOrder)) {
			extlog.log(LogStatus.PASS, "Cart Checkout Page Displayed");
			extlog.log(LogStatus.INFO, captureScreenShot("ProductCheckOut"));
			scrollToElement(cartPageObjects.placeOrder);
			clickElement(cartPageObjects.placeOrder);
			return fillPaymentDetailsAndPay();
		} else {
			extlog.log(LogStatus.FAIL, captureScreenShot("ProductCheckOut"), "Cart Checkout Page not displayed");
			return false;
		}
	}

	public boolean fillPaymentDetailsAndPay() {
		if (elementDisplayed(cartPageObjects.payment)) {
			extlog.log(LogStatus.PASS, "Payment Page Displayed");
			extlog.log(LogStatus.INFO, captureScreenShot("payment"));

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(addInputValue(cartPageObjects.nameOnCard, signUpPageObjects.nameValue));
			softAssert.assertTrue(addInputValue(cartPageObjects.cardNumber, cartPageObjects.cardNumberValue));
			softAssert.assertTrue(addInputValue(cartPageObjects.cvv, cartPageObjects.cvvValue));
			softAssert.assertTrue(addInputValue(cartPageObjects.expMonth, cartPageObjects.expMonthValue));
			softAssert.assertTrue(addInputValue(cartPageObjects.expYear, cartPageObjects.expYearValue));
			softAssert.assertAll();
			clickElement(cartPageObjects.payAndConfirm);
			if (elementDisplayed(cartPageObjects.orderPaced)) {
				extlog.log(LogStatus.PASS, "Order Placed successfully");
				extlog.log(LogStatus.INFO, captureScreenShot("orderConfirmed"));
				clickElement(signUpPageObjects.logout);
				sleep(5);
				clickElement(homePageObject.home);
				return true;
			} else {
				extlog.log(LogStatus.FAIL, captureScreenShot("ProductCheckOut"), "failed to place Order successfully");
				return false;
			}

		} else {
			extlog.log(LogStatus.FAIL, captureScreenShot("payment"), "Payment Page not Displayed");
			return false;
		}
	}

}
