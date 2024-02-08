package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FreeListingPage extends BasePage {

	public FreeListingPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='flowEntry']//button") WebElement freeListingLogin;
	@FindBy(xpath = "//input[@id='loginEmail']") WebElement email;
	@FindBy(xpath = "//input[@id='loginPassword']") WebElement password;
	@FindBy(xpath = "//button[@id='loginRes']") WebElement loginButton;
	@FindBy(xpath = "//div[@id='errdiv']") WebElement freeListingErrormsg;
	@FindBy(xpath = "//div[@class='modal-header loginTitle']//button[@type='button']") WebElement crossButton;
	@FindBy(xpath = "//div[@id='pushengage-subscription-overlay-close-button']") WebElement popup;
	public void closePopup() throws InterruptedException {
		try {
			if(popup.isDisplayed()) {

			explicitWait(popup,10);
			popup.click();

		}
		}
			catch(Exception e) {
				e.getMessage();
			}
	}
	
	public void clickFreeListingLogin() {
		freeListingLogin.click();
	}
	
	public void enterEmail() {
		email.sendKeys("abcxyz");
	}
	
	public void enterPassword() {
		password.sendKeys("12345");
	}
	
	public void clickLoginButton() throws InterruptedException {
		loginButton.click();
		
	}
	
	public String freeListingErrorMsg() {
		explicitWait(freeListingErrormsg,10);
		String msg = freeListingErrormsg.getText();
		return msg;
	}
	
	public void cross() throws InterruptedException {
		crossButton.click();
		Thread.sleep(1000);
		
	}
	
	

}
