package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilityFiles.ScreenShots;
import utilityFiles.ExcelUtils;

public class CarWashPage extends BasePage {

	public CarWashPage(WebDriver driver) {
		super(driver);
	}
    
	
	@FindBy(xpath = "//span[@class='ratevalue']//ancestor::div[@class='col-xs-12 card']") List<WebElement> carCards;
    @FindBy(xpath = "//h2[@class='resultTitle']") List<WebElement> carWashName;
    @FindBy(xpath = "//span[@class='ratevalue']") List<WebElement> carWashRatings;
    @FindBy(xpath = "//label[@class='phonedisplay']") List<WebElement> carWashPhone;
    
    @FindBy(xpath = "//textarea[@class='commentBox']")WebElement reviewTextBox;
    @FindBy(xpath = "//div[@id='modalFixListing']//div[@class='modal-content']//textarea")WebElement reportTextArea;
    @FindBy(xpath = "//div[@id='modalFixListing']//div[@class='modal-content']//button[@class='close']")WebElement reportCloseButton;
    @FindBy(xpath = "//div[@id='pushengage-subscription-overlay-close-button']") WebElement popup;
    @FindBy(xpath = "//input[@id='email']")WebElement FBEmail;
    @FindBy(xpath = "//input[@id='pass']")WebElement FBPass;
    @FindBy(xpath = "//label[@id='loginbutton']")WebElement FBLogin;
    
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
    
    String before_review = "(//button[@class='hidden-xs btn btn-default cardFooterButtonBlue'])[";
    String after_review = "]";
    String before_share = "(//button[@title='Share'])[";
    String after_share = "]";
    String before_fb = "(//ul[@role='menu']//li//a[@data-original-title='Share on Facebook'])[";
    String after_fb = "]";
    String before_tw = "(//ul[@role='menu']//li//a[@data-original-title='Share on Twitter'])[";
    String after_tw = "]";
    String before_report = "(//div[@class='btn-group btn-input clearfix']//button[@title='Report'])[";
    String after_report = "]";
    
    Map<String, String> nameRating = new HashMap<String, String>();
    Map<String, String> nameNumber = new HashMap<String, String>();
//    List<Map> mp = new ArrayList<Map>();
    List<String> numReal = new ArrayList<String>();
	List<String> rateReal = new ArrayList<String>();
	
	
    int size = carWashRatings.size();
    int randNum = (randomNumber(size))+1;
    
    public void displayDetails() throws InterruptedException, IOException {
    	ExcelUtils excel = new ExcelUtils();
		String xlfile = System.getProperty("user.dir") + "\\testData\\testdataexcel.xlsx";
    	ScreenShots ss = new ScreenShots(driver);
    	closePopup();
    	Thread.sleep(2000);
    	ss.screenshot("CarWash");
    	for(int i=0; i<carCards.size(); i++) 
    	{
    		String name = carWashName.get(i).getText();
    		String rating = carWashRatings.get(i).getText();
    		String number1 = carWashPhone.get(i).getText();
    		nameRating.put(name,rating);
    		nameNumber.put(name,number1);
    	}
    	
		 numReal = getMaps(nameNumber);
		 rateReal = getMaps(nameRating);
	int i =0;
	int j=2;
	for(String k : nameNumber.keySet())
	{
		String rate1 = rateReal.get(i);
        if(Double.parseDouble(rate1)>4){
        	String Name= "Name:" +k+","+"Rating: "+rate1+" Phone Number: "+ numReal.get(i);
        	String num = numReal.get(i);
        	System.out.println(Name);
        	System.out.println("<===========================================================================>");
        	excel.setCellData(xlfile, "CarWash", j, 0, k);
        	excel.setCellData(xlfile, "CarWash", j, 1, rate1);
        	excel.setCellData(xlfile, "CarWash", j, 2, num);
        	j++;
        	
        }
		
		i++;
	}
    }
    
    public void writeReview() throws InterruptedException {
    	WebElement reviewButton= driver.findElement(By.xpath(before_review +randNum +after_review));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	highlightElement(reviewButton);
    	js.executeScript("arguments[0].click();", reviewButton);
//    	reviewButton.click();
//    	Thread.sleep(2000);
    	explicitWait(reviewTextBox,10);
    	reviewTextBox.sendKeys("Very nice!!!");
    }
    
    public void shareFB() throws IOException, InterruptedException {
    	ScreenShots sc = new ScreenShots(driver);
    	WebElement shareButton = driver.findElement(By.xpath(before_share +randNum +after_share));
    	highlightElement(shareButton);
    	shareButton.click();
    	Thread.sleep(3000);
    	
    	WebElement fbShareButton = driver.findElement(By.xpath(before_fb + randNum + after_fb));
    	highlightElement(fbShareButton);
    	fbShareButton.click();
    	
    	

    	
    	Set<String> wid = driver.getWindowHandles();
    	List<String> winid = new ArrayList<String>(wid);
    	String parent = winid.get(0);
    	String child = winid.get(1);
    	String title = driver.switchTo().window(child).getTitle();
    	Thread.sleep(3000);

    		if(title.equals("Facebook")) {
    			driver.switchTo().window(child);
    			Thread.sleep(3000);
    			sc.screenshot("FB");
    			Thread.sleep(2000);
    			FBEmail.sendKeys("abc@xyz");
    			FBPass.sendKeys("acbsdh1234");
    			FBLogin.click();
    			Thread.sleep(2000);
    			sc.screenshot("FBError");
    			String err_msg = driver.findElement(By.xpath("//div[@class='_li']//div[contains(@class,'pam login_error_box')]")).getText();
    			System.out.println("Facebook Error msg : " + err_msg);
    			System.out.println("<==============================================================>");
    			driver.close();
    		}
    		driver.switchTo().window(parent);
    }
    
    public void shareTwitter() throws InterruptedException, IOException {
    	ScreenShots sc = new ScreenShots(driver);
    	WebElement shareButton = driver.findElement(By.xpath(before_share +randNum +after_share));
    	highlightElement(shareButton);
    	shareButton.click();
    	Thread.sleep(2000);
    	WebElement twShareButton = driver.findElement(By.xpath(before_tw + randNum + after_tw));
    	highlightElement(twShareButton);
    	twShareButton.click();
    	
    	Set<String> wid = driver.getWindowHandles();
    	List<String> winid = new ArrayList<String>(wid);
    	String parent = winid.get(0);
    	String child = winid.get(1);
    	Thread.sleep(3000);
    	driver.switchTo().window(child);
    	Thread.sleep(3000);
    	sc.screenshot("TW");
    	Thread.sleep(2000);
    	driver.close();
    	driver.switchTo().window(parent);
    	
    }
    
    public void reportButton() throws InterruptedException, IOException {
    	ScreenShots sc = new ScreenShots(driver);
    	driver.findElement(By.xpath(before_report + randNum + after_report)).click();
    	Thread.sleep(2000);
    	reportTextArea.sendKeys("Service not satisfactory!");
    	Thread.sleep(3000);
    	sc.screenshot("Report");
    	Thread.sleep(2000);
    	highlightElement(reportCloseButton);
    	reportCloseButton.click();
    }
    

public static List<String>  getMaps(Map<String, String> m )	{
    Map<String, String> map=m;	
    
    List<String> val1 = new ArrayList<String>();
	for(String k : map.keySet())
    {
//    	System.out.println(k+","+number.get(k));
    	val1.add(map.get(k));
    	
    }
    	return val1;
    }

public static int randomNumber(int num) {
    Random random = new Random();
	int randomNo = (random.nextInt(num))+1;
	return randomNo;

 }
public void clickBack() {
	driver.navigate().back();
}


}
