package rahulsheetyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents{
	
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement>  productTitles;

	public cartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	public boolean verifyDisplayedProduct(String productName) {
//		Boolean match=productTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
//				return match;
//	}
//	

public boolean verifyDisplayedProduct(String productName) {

    System.out.println("Expected product: [" + productName + "]");
    System.out.println("Number of products found: " + productTitles.size());

    for (WebElement product : productTitles) {
        System.out.println("Actual product: [" + product.getText() + "]");
    }

    boolean match = productTitles.stream()
        .anyMatch(product ->
            product.getText().trim().equalsIgnoreCase(productName.trim()));

    System.out.println("Product matched: " + match);

    return match;
}

	
	
	
	public checkOutPage goToCheckOut() {
		checkOutEle.click();
		return new checkOutPage(driver);
	}

}
