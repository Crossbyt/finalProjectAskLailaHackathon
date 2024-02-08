package Hackathon_Rough;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Carwash_Justdial {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.get("https://www.asklaila.com");
		driver.manage().window().maximize();
	
		
		//driver.switchTo().frame("googlefcLoaded");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id=\"pushengage-subscription-overlay-close-button\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button' and text()='Nevermind! I am in a roaming mode.'])[1]")).click();
//		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)","");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class='col-xs-12 col-sm-6 col-md-4 col-lg-4'])[1]")).click();//clicking india
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id=\"pushengage-subscription-overlay-close-button\"]")).click();
		js.executeScript("window.scrollBy(0,300)","");
        driver.findElement(By.xpath("(//div[@class='col-xs-12 col-sm-6 col-md-4 col-lg-4'])[2]")).click();//clicking bangalore
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id=\"pushengage-subscription-overlay-close-button\"]")).click(); //popup notification handle
        driver.findElement(By.xpath("//input[@id='what']")).sendKeys("Car Washing Services");
        driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id=\"pushengage-subscription-overlay-close-button\"]")).click();
        Map<String, String> rate = new HashMap<String, String>();
        List<WebElement> cards = driver.findElements(By.xpath("//span[@class='ratevalue']//ancestor::div[@class='col-xs-12 card']"));
        List<WebElement> names = driver.findElements(By.xpath("//h2[@class='resultTitle']"));
        List<WebElement> ratings = driver.findElements(By.xpath("//span[@class='ratevalue']"));
//        System.out.println("No of cards : " + cards.size());
        Map<String, String> number = new HashMap<String, String>();
        List<Map> maps = new ArrayList<Map>();
        List<WebElement> numberS = driver.findElements(By.xpath("//label[@class='phonedisplay']"));
//        List<WebElement> write_review = driver.findElements(By.xpath("//button[@class=\"hidden-xs btn btn-default cardFooterButtonBlue\"]"));
         String before_write = "(//button[@class='hidden-xs btn btn-default cardFooterButtonBlue'])[";
         String after_write = "]";
         String before_share = "(//button[@title='Share'])[";
         String after_share = "]";
         String before_fb = "(//ul[@role='menu']//li//a[@data-original-title='Share on Facebook'])[";
         String after_fb = "]";
//         String before_twitter = "(//ul[@role='menu']//li//a[@data-original-title='Share on Twitter'])[";
//         String after_twitter = "]";
         String before_report = "(//button[@title='Report'])[";
         String after_report = "]";
         
         int size = ratings.size();
         int randNum = randomNumber(size);
        	for(int i=0; i<cards.size(); i++) 
        	{
        		String name = names.get(i).getText();
        		String rating = ratings.get(i).getText();
        		String number1 = numberS.get(i).getText();
        		rate.put(name,rating);
        		number.put(name,number1);
        	
        	}

//        	maps.add(rate);
//        	maps.add(number);

        	
        		List<String> numReal = new ArrayList<String>();
        		List<String> rateReal = new ArrayList<String>();
        		 numReal = getMaps(number);
        		 rateReal = getMaps(rate);
        	int i =0;	
        	for(String k : number.keySet())
        	{
        		String rate1 = rateReal.get(i);
                if(Double.parseDouble(rate1)>4){
//                	System.out.println(rate1);
                	System.out.println("Name:" +k+","+"Rating: "+rate1+" Phone Number: "+ numReal.get(i));
                	
                }
        		
//        		}
        		i++;
        	}
//        	System.out.println("RateReal size : " + rateReal.size());
//        	for(String p:rateReal) {
//        		System.out.println(p);
//        	}
//        	for(int p=0;p<rateReal.size();p++) {
//        		String val = rateReal.get(p);
//        		if(Double.parseDouble(val)>4) {
////        			System.out.println(val);
//        			driver.findElement(By.xpath(before_write +p +after_write)).click();
//        			Thread.sleep(3000);
//        			driver.findElement(By.xpath("//textarea[@class='commentBox']")).sendKeys("Very nice!!!");
//        			Thread.sleep(2000);
//                	driver.navigate().back();
//                	Thread.sleep(1000);
//                	driver.findElement(By.xpath(before_share +p +after_share)).click();
//                	break;
//        		}
//        		
//        	}
//        	
//        	for(int j=0;j<rateReal.size();j++) {
//        		String rate2 = rateReal.get(j);
//        		if(Double.parseDouble(rate2)>4) {
//        			driver.findElement(By.xpath(before_write +j +after_write)).click();
//        			Thread.sleep(3000);
//        			driver.findElement(By.xpath("//textarea[@class='commentBox']")).sendKeys("Very nice!!!");
//        			Thread.sleep(2000);
//                	driver.navigate().back();
//                	Thread.sleep(1000);
//                	driver.findElement(By.xpath(before_share +j +after_share)).click();
//                	break;
//        		}
//        	}
        	
        	Thread.sleep(2000);
        	//clicking on write review button
        	driver.findElement(By.xpath(before_write +randNum +after_write)).click();
        	Thread.sleep(3000);
        	//writing review
        	driver.findElement(By.xpath("//textarea[@class='commentBox']")).sendKeys("Very nice!!!");
        	Thread.sleep(2000);
        	driver.navigate().back();
        	Thread.sleep(1000);
        	driver.findElement(By.xpath(before_share +randNum +after_share)).click();
        	driver.findElement(By.xpath(before_fb + randNum + after_fb)).click();
        	
        	Set<String> wid = driver.getWindowHandles();
        	List<String> winid = new ArrayList<String>(wid);
        	String parent = winid.get(0);
        	String child = winid.get(1);
        	String title = driver.switchTo().window(child).getTitle();
        		if(title.equals("Error")) {
        			driver.switchTo().window(child);
        			String err_msg = driver.findElement(By.xpath("//div[@class='_li']")).getText();
        			System.out.println("Error msg : " + err_msg);
        			driver.close();
        		}
        	
        	
//        	driver.findElement(By.xpath(before_twitter+randNum+after_twitter)).click();
//        	Set<String> wid1 = driver.getWindowHandles();
//        	for(String id1:wid1) {
//        		String title = driver.switchTo().window(id1).getTitle();
//        		if(title.equals("Twitter")) {
//        			driver.switchTo().window(id1);
//        			//**Take a ss
//        			driver.close();
//        			break;
//        		}
//        	}
        	driver.switchTo().window(parent);
//        	
        	Thread.sleep(2000);
        	driver.findElement(By.xpath(before_report + randNum + after_report)).click();
        	Thread.sleep(2000);
        	driver.findElement(By.xpath("//div[@id='modalFixListing']//div[@class='modal-content']//textarea")).sendKeys("Report");
        	Thread.sleep(1000);
        	driver.findElement(By.xpath("//div[@id='modalFixListing']//div[@class='modal-content']//button[@class='close']")).click();
        	
        	Thread.sleep(2000);
        	driver.navigate().back();
        	
        	driver.findElement(By.xpath("//div[@class='btn-group']//a[@class='btn cardFooterButtonBlue']")).click(); //clicking on free listing button
        	
        	driver.findElement(By.xpath("//div[@id='flowEntry']//button")).click(); //clicking on freelisting login
        	
        	driver.findElement(By.xpath("//input[@id='loginEmail']")).sendKeys("abcxyz");
        	driver.findElement(By.xpath("//input[@id='loginPassword']")).sendKeys("12345");
        	driver.findElement(By.xpath("//button[@id='loginRes']")).click(); //clicking on login button
        	Thread.sleep(2000);
        	String errormsg = driver.findElement(By.xpath("//div[@id='errdiv']")).getText();
        	System.out.println("Free listing error msg : " + errormsg);
        	
        	driver.findElement(By.xpath("//div[@class='modal-header loginTitle']//button[@type='button']")).click();//clicking on cross button
        	Thread.sleep(1000);
        	driver.navigate().back();
        	js.executeScript("window.scrollBy(0,1800)","");
        	driver.findElement(By.xpath("//div[@class='col-xs-6 col-sm-6 col-md-3 col-lg-3 pad0'][34]")).click();
        	
        	driver.findElement(By.xpath("//div[@id='searchSubCategory']//button")).click(); //clicking on sub-category
        	List<WebElement> gyms = driver.findElements(By.xpath("//div[@id='searchSubCategory']//ul[@class='dropdown-menu pad0']//li"));
        	for(WebElement g:gyms) {
        		System.out.println(g.getText());
        	}
        	
        	driver.quit();
	}
        public static List<String>  getMaps(Map<String, String> m )	{
        Map<String, String> map=m;	
        
        List<String> val1 = new ArrayList<String>();
		for(String k : map.keySet())
        {
//        	System.out.println(k+","+number.get(k));
        	val1.add(map.get(k));
        	
        }
        	return val1;
        }
        
		//driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
//		Alert alert = driver.switchTo().alert();
//		alert.dismiss();
		
		//clicking on sort by
		//driver.findElement(By.xpath("//div[@id='topBand']//li[@role='menuitem'][1]")).click();
		//div[contains(text(),"Ratings")]
		//span[contains(text(),"4.0+")]
		
		
		//selecting location
//		driver.findElement(By.xpath("//input[@id='city-auto-sug']")).click();
//		driver.findElement(By.xpath("(//ul[@role='listbox']//li//a)[1]")).click();
//		//selecting car wash services
//		driver.findElement(By.xpath("//input[@id='main-auto']")).sendKeys("Car wash services");
//		WebElement searchbutton = driver.findElement(By.xpath("//div[@class='search_button']//div[@class='jdicon search_white_icon']"));
//		searchbutton.click();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", searchbutton);
//		List<WebElement> titles = driver.findElements(By.xpath("//div[contains(@class,'resultbox_title_anchor')]")); //title
//		//ratings and reviews 
//		List<WebElement> ratings = driver.findElements(By.xpath("//div[contains(@class,'resultbox_totalrate')]"));
//		List<WebElement> reviews = driver.findElements(By.xpath("//div[contains(@class,'resultbox_countrate')]"));
//		List<WebElement> phonenumber = driver.findElements(By.xpath("//div[contains(@class,'greenfill_animate callbutton')]"));
//		int count = 0;
//		for(int i=0; i<titles.size();i++) {
//			String rev = reviews.get(i).getText();
//			String arr[]= rev.split(" ");
//			if((Integer.parseInt(ratings.get(i).getText())>4 && (Integer.parseInt(arr[0]))>20)){
//				count++;
//				System.out.println("Name : " + titles.get(i).getText());
//				System.out.println("Phone number: " + phonenumber.get(i).getText());
//			}
//		}
     public static int randomNumber(int num) {
        Random random = new Random();
		int randomNo = (random.nextInt(num))+1;
		return randomNo;

     }
	}

	


