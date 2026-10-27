package rahulsheetyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents {
	WebDriver driver;
	
	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css=".btnn.action__submit")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
	WebElement selectCountry;
	
	
	public void selectCountry(String countryName) {
		Actions a= new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		
		selectCountry.click();
	}
	
	public confirmationPage submitOrder() {
		submit.click();
		return new confirmationPage(driver);
	}

}
