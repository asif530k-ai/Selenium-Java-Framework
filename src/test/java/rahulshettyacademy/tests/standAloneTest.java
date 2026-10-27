package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsheetyacademy.pageobjects.ProductCatalouge;
import rahulsheetyacademy.pageobjects.cartPage;
import rahulsheetyacademy.pageobjects.checkOutPage;
import rahulsheetyacademy.pageobjects.confirmationPage;
import rahulsheetyacademy.pageobjects.orderPage;
import rahulshettyacademy.TestComponenets.BaseTest;


public class standAloneTest extends BaseTest {
	String productName="ZARA COAT 3";
	@Test(dataProvider="getData", groups= {"purchase"})
	public void standAloneTest(HashMap<String, String> input) throws IOException, InterruptedException{
		
		
		Thread.sleep(3000);
		ProductCatalouge PC=LP.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=PC.getProductList(); 	 	
		PC.addToCart(productName);
		PC.Threadsleepfor5Sec();
		cartPage cartPage=PC.goToCart();
		Boolean match=cartPage.verifyDisplayedProduct(productName);
		Assert.assertTrue(match);
		checkOutPage checkOutPage= cartPage.goToCheckOut();
		checkOutPage.selectCountry("India");
		confirmationPage confirmationPage=checkOutPage.submitOrder();
		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods= {"standAloneTest"})
	public void OrderHistoryTest() {
		ProductCatalouge PC=LP.loginApplication("jhon1234@gmail.com","Jhon@123");
		orderPage orderPage=PC.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map=new HashMap<String, String>();
//		map.put("email", "jhon1234@gmail.com");
//		map.put("password", "Jhon@123");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1=new HashMap<String, String>();
//		map1.put("email", "last1234@gmail.com");
//		map1.put("password", "Last1234");
//		map1.put("product", "ADIDAS ORIGINAL");
//		
//		
//		return new Object[][] {{map},{map1}};
		
		List<HashMap<String,String>> data=getJsonDataTOMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	

}
