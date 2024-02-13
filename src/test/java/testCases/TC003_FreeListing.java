package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utilityFiles.ScreenShots;
import pageObjects.FreeListingPage;
import pageObjects.GymandSearchPage;

public class TC003_FreeListing extends TC002_SearchandFind{
	
	@Test(priority=5,groups= {"regression","master"})
	public void clickfreelisting() throws InterruptedException, IOException {
		if(!driver.getTitle().equals("AskLaila Bangalore - India's local information service.")) {
			driver.navigate().back();
			Thread.sleep(3000);
		}
		GymandSearchPage gp = new GymandSearchPage(driver);
		gp.clickFreeListing();
		
	}
	
	@Test(priority=6,groups= {"regression","master"})
	public void freeListingLogin() throws InterruptedException, IOException {
		FreeListingPage fp = new FreeListingPage(driver);
		ScreenShots sc = new ScreenShots(driver);
		fp.clickFreeListingLogin();
		fp.enterEmail();
		fp.enterPassword();
		fp.clickLoginButton();
		logger.info("--//Free Listing Done//--");
		String error_msg = fp.freeListingErrorMsg();
		Thread.sleep(3000);
		sc.screenshot("Freelisting");
		System.out.println("FreeListing Error Message : " + error_msg);
		System.out.println("<=========================================================>");
		fp.cross();
		navigateBack();
	}
	
	

}
