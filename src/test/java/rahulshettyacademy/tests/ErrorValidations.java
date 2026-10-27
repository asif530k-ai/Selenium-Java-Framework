package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponenets.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsheetyacademy.pageobjects.ProductCatalouge;
import rahulsheetyacademy.pageobjects.cartPage;
import rahulsheetyacademy.pageobjects.checkOutPage;
import rahulsheetyacademy.pageobjects.confirmationPage;
import rahulshettyacademy.TestComponenets.BaseTest;


public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorvalidation() throws IOException, InterruptedException{
		
		String productName="ZARA COAT 3";
		Thread.sleep(3000);
		LP.loginApplication("jhon1234@gmail.com","Jhon@@123");
		
		Assert.assertEquals("Incorrect email or password.",LP.getErrorMessage());
		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException{
		
		String productName="ZARA COAT 3";
		Thread.sleep(3000);
		ProductCatalouge PC=LP.loginApplication("jhon1234@gmail.com","Jhon@123");
		List<WebElement> products=PC.getProductList(); 	 	
		PC.addToCart(productName);
		PC.Threadsleepfor5Sec();
		cartPage cartPage=PC.goToCart();
		Boolean match=cartPage.verifyDisplayedProduct("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
