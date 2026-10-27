package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulsheetyacademy.pageobjects.cartPage;
import rahulsheetyacademy.pageobjects.orderPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementTOAppear(By FindBy) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));	
	}
	
	
	public void waitForWebElementTOAppear(WebElement FindBy) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindBy));	
	}
	
	
	public void waitElementToDisapper(WebElement ele) throws InterruptedException {
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(1000);
		
	}
	
	public void Threadsleepfor5Sec() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	public cartPage goToCart() {
		cart.click();
		cartPage cartPage=new cartPage(driver);
		return cartPage;
	}
	
	public orderPage goToOrdersPage() {
		orderHeader.click();
		orderPage orderPage=new orderPage(driver);
		return orderPage;
	}
	
	
}
