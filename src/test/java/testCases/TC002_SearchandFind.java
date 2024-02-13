package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import utilityFiles.ScreenShots;
import pageObjects.CarWashPage;
import pageObjects.GymandSearchPage;

public class TC002_SearchandFind extends TC001_Location{
	
	@Test(priority=3,groups= {"regression","master"})
	public void searchCarWash() throws InterruptedException, IOException {
		GymandSearchPage gs = new GymandSearchPage(driver);
		ScreenShots sc = new ScreenShots(driver);
		Thread.sleep(3000);
		sc.screenshot("SearchPage");
		gs.searchText();
	}
	
	@Test(priority=4,groups= {"regression","master"})
	public void carWashDetails() throws InterruptedException, IOException {
		CarWashPage cp = new CarWashPage(driver);
		ScreenShots sc = new ScreenShots(driver);
		Thread.sleep(3000);
		cp.displayDetails();
		logger.info("--//Top 5 car wash services displyed//--");
		cp.writeReview();
		Thread.sleep(3000);
		logger.info("--//Review given//--");
		sc.screenshot("ReviewPage");
		navigateBack();
		Thread.sleep(2000);
		cp.shareFB();
		cp.shareTwitter();
		cp.reportButton();
		logger.info("--//Shared and Reported//--");
		driver.navigate().back();
		Thread.sleep(3000);
	}
	


}
